package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.commons.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.EventGroup;
import org.palladiosimulator.pcm.repository.EventType;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RequiredRole;
import org.palladiosimulator.pcm.repository.SourceRole;
import org.palladiosimulator.pcm.seff.EmitEventAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;

public class AddEmitEventAction implements IExternalJavaAction {

	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		EmitEventAction emitEventAction = (EmitEventAction) parameters.get("instance");
		EventType eventType = getEventType(emitEventAction);
		emitEventAction.setEventType__EmitEventAction(eventType);

	}

	private EventType getEventType(EmitEventAction emitEventAction) {

		Collection<Object> filter = new ArrayList<Object>();

		filter.add(Repository.class);
		filter.add(EventGroup.class);
		filter.add(EventType.class);

		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();

		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				emitEventAction.eResource().getResourceSet());

		dialog.setProvidedService(EventType.class);

		//Only show EventTypes from EventGroups from SourceRoles of the parent BasicComponent
		for (Object o : dialog.getTreeViewer().getExpandedElements()) {
			if (!(o instanceof EventGroup))
				continue;
			ServiceEffectSpecification seff = (ServiceEffectSpecification) emitEventAction
					.getResourceDemandingBehaviour_AbstractAction();
			BasicComponent parent = seff.getBasicComponent_ServiceEffectSpecification();
			
			boolean found = false;
			for (RequiredRole r : parent.getRequiredRoles_InterfaceRequiringEntity()) {
				if (!(r instanceof SourceRole))
					continue;
				SourceRole sourceRole = (SourceRole) r;
				if (sourceRole.getEventGroup__SourceRole().equals(o)) {
					found = true;
					emitEventAction.setSourceRole__EmitEventAction(sourceRole);
				}
			}
			if (!found)
				dialog.getTreeViewer().remove(o);
		}
		
		dialog.open();
		return (EventType) dialog.getResult();
	}


	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		// TODO Auto-generated method stub
		return true;
	}

}
