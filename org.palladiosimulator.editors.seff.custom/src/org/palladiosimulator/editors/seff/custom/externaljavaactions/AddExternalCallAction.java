package org.palladiosimulator.editors.seff.custom.externaljavaactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RequiredRole;
import org.palladiosimulator.pcm.repository.Signature;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;

public class AddExternalCallAction implements IExternalJavaAction {
	

	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		ExternalCallAction extCall = (ExternalCallAction) parameters.get("instance");
		OperationSignature os = getOperationSignature(extCall);
		extCall.setCalledService_ExternalService(os);

	}

	private OperationSignature getOperationSignature(ExternalCallAction extCall) {

		Collection<Object> filter = new ArrayList<Object>();
		
		filter.add(Repository.class);
		//filter.add(BasicComponent.class);
		//filter.add(OperationRequiredRole.class);  //Interfaces werden nicht wie im GMF Editor unter RequiredRole angezeigt
		filter.add(OperationInterface.class);
		filter.add(OperationSignature.class);

		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();
		
		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				extCall.eResource().getResourceSet());
		

		dialog.setProvidedService(OperationSignature.class);
		
		// only take required OperationInterface
		for (Object o : dialog.getTreeViewer().getExpandedElements()) {
			if (!(o instanceof OperationInterface))
				continue;
			ServiceEffectSpecification seff = (ServiceEffectSpecification) extCall.getResourceDemandingBehaviour_AbstractAction();
			BasicComponent parent = seff.getBasicComponent_ServiceEffectSpecification();
			
			boolean found = false;
			for (RequiredRole r : parent.getRequiredRoles_InterfaceRequiringEntity()) {
				if (!(r instanceof OperationRequiredRole))
					continue;
				OperationRequiredRole or = (OperationRequiredRole) r;
				if (or.getRequiredInterface__OperationRequiredRole().equals(o)) {

					found = true;
					extCall.setRole_ExternalService(or);
				}
			}
			if (!found)
				dialog.getTreeViewer().remove(o);
		}
		
		dialog.open();

		return (OperationSignature) dialog.getResult();
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		// TODO Auto-generated method stub
		return true;
	}

}
