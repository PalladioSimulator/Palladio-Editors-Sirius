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
import org.palladiosimulator.pcm.seff.AcquireAction;

public class AddAcquireAction implements IExternalJavaAction {

	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		
		AcquireAction acquireAction = (AcquireAction) parameters.get("instance");		
		PassiveResource passiveResource = getPassiveResource(acquireAction);
		acquireAction.setPassiveresource_AcquireAction(passiveResource);
	}

	private PassiveResource getPassiveResource(AcquireAction acquireAction) {
	
		Collection<Object> filter = new ArrayList<Object>();
		filter.add(Repository.class);
		filter.add(BasicComponent.class);
		filter.add(PassiveResource.class);

		// Additional Child References
		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();
		//additionalChildReferences.add(RepositoryPackage.Literals.PASSIVE_RESOURCE__BASIC_COMPONENT_PASSIVE_RESOURCE);

		// Creating the dialog
		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				acquireAction.eResource().getResourceSet());

		// Setting the needed object type
		dialog.setProvidedService(PassiveResource.class);

		dialog.open();

		return (PassiveResource) dialog.getResult();
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
