package org.palladiosimulator.editors.sirius.assembly.custom.externaljavaactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.commons.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.Repository;

public class AddOperationRequiredRole implements IExternalJavaAction {
	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	private static final String NAME_POST_EXTENSION = "RequiredRole";

	@Override
	public boolean canExecute(Collection<? extends EObject> arg0) {
		return true;
	}

	@Override
	public void execute(Collection<? extends EObject> selection, Map<String, Object> parameters) {
		OperationRequiredRole role = (OperationRequiredRole) parameters.get("instance");		
		OperationInterface oInterface = getOperationInterface(role);
		//get system model
        final var optSystem = selection.stream().filter(org.palladiosimulator.pcm.system.System.class::isInstance)
                .map(org.palladiosimulator.pcm.system.System.class::cast).findAny();
        if (optSystem.isPresent()) {
            role.setEntityName(this.findNameOperationRequiredRole(optSystem.get(), oInterface));
        } else {
            role.setEntityName(oInterface.getEntityName());
        }
		role.setRequiredInterface__OperationRequiredRole(oInterface);

	}
    private String findNameOperationRequiredRole(final org.palladiosimulator.pcm.system.System system,
            final OperationInterface oInterface) {
        final var interfaceName = oInterface.getEntityName() + NAME_POST_EXTENSION;
        var roleName = interfaceName;
        for (int i = 1; this.checkExistingNames(system, roleName); i++) {
            roleName= interfaceName + i;
            if(i == Integer.MAX_VALUE) {
                throw new IllegalStateException("Operation Name Extension too big");
            }
        }
        return roleName;
    }
    private boolean checkExistingNames(final org.palladiosimulator.pcm.system.System system,
            final String interfaceName) {
        return system.getRequiredRoles_InterfaceRequiringEntity().stream()
                .anyMatch(e -> interfaceName.equals(e.getEntityName()));
    }
	
	private OperationInterface getOperationInterface(OperationRequiredRole role) {

		Collection<Object> filter = new ArrayList<Object>();
		filter.add(Repository.class);
		filter.add(OperationInterface.class);

		// Additional Child References
		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();

		// Creating the dialog
		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				role.eResource().getResourceSet());

		// Setting the needed object type
		dialog.setProvidedService(OperationInterface.class);

		dialog.open();

		return (OperationInterface) dialog.getResult();
	}

}
