package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.commons.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;

public class AddExternalCallAction implements IExternalJavaAction {
	

	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	
	
	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		ExternalCallAction extCall = (ExternalCallAction) parameters.get("instance");
		HashMap<OperationInterface, OperationRequiredRole> requiredRolesMap = new HashMap<OperationInterface, OperationRequiredRole>();
		OperationSignature os = getOperationSignature(extCall, requiredRolesMap);
		if (os != null) {
			extCall.setCalledService_ExternalService(os);			
			extCall.setRole_ExternalService(requiredRolesMap.get(os.getInterface__OperationSignature()));
		}

	}

	private OperationSignature getOperationSignature(ExternalCallAction extCall, HashMap<OperationInterface, OperationRequiredRole> requiredRolesMap) {

		Collection<Object> filter = new ArrayList<Object>();
		
		filter.add(Repository.class);
		filter.add(OperationInterface.class);
		filter.add(OperationSignature.class);

		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();
		
		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				extCall.eResource().getResourceSet());
		

		dialog.setProvidedService(OperationSignature.class);
		
		for (Object o : dialog.getTreeViewer().getExpandedElements()) {
			if (!(o instanceof OperationInterface)) //if the current object is not an OperationInterface, skip.
				continue;
			
			//o is an OperationInterface
			ServiceEffectSpecification seff = (ServiceEffectSpecification) extCall.getResourceDemandingBehaviour_AbstractAction();
			BasicComponent parent = seff.getBasicComponent_ServiceEffectSpecification();
			
			//Get the OperationRequiredRoles of the BasicComponents
			Collection<OperationRequiredRole> operationRequiredRoles = parent.getRequiredRoles_InterfaceRequiringEntity().stream().filter(x -> x instanceof OperationRequiredRole).map(x -> (OperationRequiredRole) x).collect(Collectors.toList());
			
			//if o is not referenced by any OperationRequiredRole, remove it from the tree viewer
			
		    Optional<OperationRequiredRole> operationRequiredRole = operationRequiredRoles.stream().filter(x -> x.getRequiredInterface__OperationRequiredRole() == o).findAny();
		    if (operationRequiredRole.isPresent())
		        requiredRolesMap.put((OperationInterface) o, operationRequiredRole.get());
		    else
		        dialog.getTreeViewer().remove(o);
			

		}
		
		dialog.open();

		return (OperationSignature) dialog.getResult();
	}
	

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
