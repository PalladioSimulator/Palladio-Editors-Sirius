package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.commons.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.PassiveResource;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.seff.ReleaseAction;

public class AddReleaseAction implements IExternalJavaAction {


	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		ReleaseAction releaseAction = (ReleaseAction) parameters.get("instance");		
		PassiveResource passiveResource = getPassiveResource(releaseAction);
		releaseAction.setPassiveResource_ReleaseAction(passiveResource);

	}
	private PassiveResource getPassiveResource(ReleaseAction releaseAction) {
		
		Collection<Object> filter = new ArrayList<Object>();
		filter.add(Repository.class);
		filter.add(BasicComponent.class);
		filter.add(PassiveResource.class);

		// Additional Child References
		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();

		// Creating the dialog
		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				releaseAction.eResource().getResourceSet());

		// Setting the needed object type
		dialog.setProvidedService(PassiveResource.class);
		dialog.setTitle("Test Title for Debugging");
		dialog.open();

		return (PassiveResource) dialog.getResult();
	}
	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
