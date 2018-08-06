package org.palladiosimulator.editors.sirius.repository.custom.externaljavaactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.commons.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.pcm.core.entity.ResourceRequiredRole;
import org.palladiosimulator.pcm.resourcetype.ResourceInterface;
import org.palladiosimulator.pcm.resourcetype.ResourceRepository;

public class SetResourceInterface implements IExternalJavaAction {

	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		ResourceRequiredRole resourceRequiredRole = (ResourceRequiredRole) parameters.get("instance");
		ResourceInterface resourceInterface = getResourceInterface(resourceRequiredRole);
		if (resourceInterface != null)
			resourceRequiredRole.setRequiredResourceInterface__ResourceRequiredRole(resourceInterface);

	}

	private ResourceInterface getResourceInterface(ResourceRequiredRole resourceRequiredRole) {
		Collection<Object> filter = new ArrayList<Object>();
		filter.add(ResourceRepository.class);
		filter.add(ResourceInterface.class);
		
		// Additional Child References
		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();

		// Creating the dialog
		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				resourceRequiredRole.eResource().getResourceSet());

		// Setting the needed object type
		dialog.setProvidedService(ResourceInterface.class);

		dialog.open();

		return (ResourceInterface) dialog.getResult();
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
