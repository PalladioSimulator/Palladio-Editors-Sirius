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
import org.palladiosimulator.pcm.repository.EventType;
import org.palladiosimulator.pcm.repository.InfrastructureInterface;
import org.palladiosimulator.pcm.repository.InfrastructureRequiredRole;
import org.palladiosimulator.pcm.repository.InfrastructureSignature;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RequiredRole;
import org.palladiosimulator.pcm.seff.EmitEventAction;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import org.palladiosimulator.pcm.seff.seff_performance.InfrastructureCall;

public class SetInfrastructureSignature implements IExternalJavaAction {


	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		InfrastructureCall call = (InfrastructureCall) parameters.get("instance");
		InfrastructureSignature sig = getSignature(call);
		call.setSignature__InfrastructureCall(sig);

	}

	private InfrastructureSignature getSignature(InfrastructureCall call) {
		Collection<Object> filter = new ArrayList<Object>();
		
		filter.add(Repository.class);
		filter.add(InfrastructureInterface.class);
		filter.add(InfrastructureSignature.class);

		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();
		
		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				call.eResource().getResourceSet());
		

		dialog.setProvidedService(InfrastructureSignature.class);
		
		// only take required InfrastructureInterfaces
		for (Object o : dialog.getTreeViewer().getExpandedElements()) {
			if (!(o instanceof InfrastructureInterface))
				continue;
			ServiceEffectSpecification seff = (ServiceEffectSpecification) call.getAction__InfrastructureCall().getResourceDemandingBehaviour_AbstractAction();
			BasicComponent parent = seff.getBasicComponent_ServiceEffectSpecification();
			
			boolean found = false;
			for (RequiredRole r : parent.getRequiredRoles_InterfaceRequiringEntity()) {
				if (!(r instanceof InfrastructureRequiredRole))
					continue;
				InfrastructureRequiredRole ir = (InfrastructureRequiredRole) r;
				if (ir.getRequiredInterface__InfrastructureRequiredRole().equals(o)) {
					found = true;
					call.setRequiredRole__InfrastructureCall(ir);
				}
			}
			if (!found)
				dialog.getTreeViewer().remove(o);
		}
		
		dialog.open();

		return (InfrastructureSignature) dialog.getResult();
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
