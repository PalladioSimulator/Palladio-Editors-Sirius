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
import org.palladiosimulator.pcm.repository.EventGroup;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.SourceRole;

public class AddSourceRole implements IExternalJavaAction {

	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

	@Override
	public boolean canExecute(Collection<? extends EObject> arg0) {
		return true;
	}

	@Override
	public void execute(Collection<? extends EObject> selection, Map<String, Object> parameters) {
		SourceRole role = (SourceRole) parameters.get("instance");		
		EventGroup eventGroup = getEventGroup(role);
		role.setEventGroup__SourceRole(eventGroup);
		
	}

	private EventGroup getEventGroup(SourceRole role) {

		Collection<Object> filter = new ArrayList<Object>();
		filter.add(Repository.class);
		filter.add(EventGroup.class);

		// Additional Child References
		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();

		// Creating the dialog
		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				role.eResource().getResourceSet());

		// Setting the needed object type
		dialog.setProvidedService(EventGroup.class);

		dialog.open();

		return (EventGroup) dialog.getResult();
	}

}
