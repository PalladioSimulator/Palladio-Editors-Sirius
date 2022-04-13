package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.commons.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.editors.sirius.custom.util.PCMQueryUtils;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;

public class AddExternalCallAction implements IExternalJavaAction {

    public interface SignatureSelector {
        OperationSignature selectOperationSignature(ExternalCallAction extCall,
                Collection<OperationInterface> requiredInterfaces);
    }

    private final SignatureSelector signatureSelector;
    
    public AddExternalCallAction() {
        this(AddExternalCallAction::selectOperationSignature);
    }
    
    public AddExternalCallAction(SignatureSelector signatureSelector) {
        this.signatureSelector = signatureSelector;
    }

    @Override
    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
        ExternalCallAction extCall = (ExternalCallAction) parameters.get("instance");
        if (extCall == null) {
            return;
        }
        Map<OperationInterface, Collection<OperationRequiredRole>> requiredRolesMap = createRequiredRolesMap(extCall);
        OperationSignature os = signatureSelector.selectOperationSignature(extCall, requiredRolesMap.keySet());
        if (os != null) {
            extCall.setCalledService_ExternalService(os);
            Collection<OperationRequiredRole> potentialRoles = requiredRolesMap
                .getOrDefault(os.getInterface__OperationSignature(), Collections.emptyList());
            // TODO add selection logic for potentialRoles.size() > 1 as part of EDITORS-215
            OperationRequiredRole firstrole = potentialRoles.isEmpty() ? null
                    : potentialRoles.iterator()
                        .next();
            extCall.setRole_ExternalService(firstrole);
        }

    }

    protected Map<OperationInterface, Collection<OperationRequiredRole>> createRequiredRolesMap(
            ExternalCallAction eca) {
        Map<OperationInterface, Collection<OperationRequiredRole>> result = new HashMap<OperationInterface, Collection<OperationRequiredRole>>();

        ResourceDemandingBehaviour rd = eca.getResourceDemandingBehaviour_AbstractAction();
        ServiceEffectSpecification seff = SEFFUtil.getEnclosingSEFF(rd);
        BasicComponent parent = seff.getBasicComponent_ServiceEffectSpecification();
        Collection<OperationRequiredRole> operationRequiredRoles = parent.getRequiredRoles_InterfaceRequiringEntity()
            .stream()
            .filter(OperationRequiredRole.class::isInstance)
            .map(OperationRequiredRole.class::cast)
            .collect(Collectors.toList());

        // add required role and required interfaces to map
        for (OperationRequiredRole operationRequiredRole : operationRequiredRoles) {
            OperationInterface operationInterface = operationRequiredRole.getRequiredInterface__OperationRequiredRole();
            Collection<OperationInterface> operationInterfaces = PCMQueryUtils.getTransitiveParentInterfaceClosureIncludingSelf(
                    operationInterface);
            for (OperationInterface i : operationInterfaces) {
                Collection<OperationRequiredRole> requiredRoles = result.computeIfAbsent(i,
                        key -> new LinkedHashSet<OperationRequiredRole>());
                requiredRoles.add(operationRequiredRole);
            }
        }

        return result;
    }

    protected static OperationSignature selectOperationSignature(ExternalCallAction extCall,
            Collection<OperationInterface> requiredInterfaces) {
        Collection<Object> filter = new ArrayList<Object>();
        filter.add(Repository.class);
        filter.add(OperationInterface.class);
        filter.add(OperationSignature.class);

        Collection<EReference> additionalChildReferences = new ArrayList<EReference>();
        Shell shell = PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow()
                .getShell();
        PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(shell, filter, additionalChildReferences,
                extCall.eResource()
                    .getResourceSet());

        dialog.setProvidedService(OperationSignature.class);

        for (Object o : dialog.getTreeViewer()
            .getExpandedElements()) {
            if (!(o instanceof OperationInterface)) { // if the current object is not an
                                                      // OperationInterface, skip.
                continue;
            }
            if (!requiredInterfaces.contains(o)) {
                dialog.getTreeViewer()
                    .remove(o);
            }
        }

        dialog.open();

        return (OperationSignature) dialog.getResult();
    }

    @Override
    public boolean canExecute(Collection<? extends EObject> selections) {
        return true;
    }

}
