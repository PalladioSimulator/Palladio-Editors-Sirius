package org.palladiosimulator.editors.sirius.repository.custom.externaljavaactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.commons.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.InfrastructureProvidedRole;
import org.palladiosimulator.pcm.repository.Interface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.Signature;
import org.palladiosimulator.pcm.repository.SinkRole;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;

public class AddSEFF implements IExternalJavaAction {

	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		ResourceDemandingSEFF seff = (ResourceDemandingSEFF) parameters.get("instance");
		Signature signature = getSignature(seff);
		for (ServiceEffectSpecification s : seff.getBasicComponent_ServiceEffectSpecification().getServiceEffectSpecifications__BasicComponent()) {
			if (s.getDescribedService__SEFF() != null && s.getDescribedService__SEFF().equals(signature))
				return; // do nothing if the chosen signature already has a corresponding SEFF
		}
		seff.setDescribedService__SEFF(signature);
	}

	private Signature getSignature(ResourceDemandingSEFF seff) {
		// Filter list (Repository > Interface > Signature)
		Collection<Object> filter = new ArrayList<Object>();
		filter.add(Repository.class);
		filter.add(Interface.class);
		filter.add(Signature.class);

		// Additional Child References
		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();

		// Creating the dialog
		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				seff.eResource().getResourceSet());

		// Setting the needed object type
		dialog.setProvidedService(Signature.class);
		
		//  Filter: Show only provided interfaces
		for (Object o : dialog.getTreeViewer().getExpandedElements()) {
			BasicComponent parent = seff.getBasicComponent_ServiceEffectSpecification();
			EList<ProvidedRole> providedRoles = parent.getProvidedRoles_InterfaceProvidingEntity();
			if (o instanceof Interface) {
				boolean found = false;
				for (ProvidedRole p : providedRoles) {
					if (p instanceof OperationProvidedRole) {
						OperationProvidedRole role = (OperationProvidedRole) p;
						if (role.getProvidedInterface__OperationProvidedRole().equals(o))
							found = true;
					} else if (p instanceof InfrastructureProvidedRole) {
						InfrastructureProvidedRole role = (InfrastructureProvidedRole) p;
						if (role.getProvidedInterface__InfrastructureProvidedRole().equals(o))
							found = true;
					} else if (p instanceof SinkRole) {
						SinkRole role = (SinkRole) p;
						if (role.getEventGroup__SinkRole().equals(o))
							found = true;
					}
				}
				if (!found)
					dialog.getTreeViewer().remove(o);
				
			}

		}
		
		
		dialog.open();

		return (Signature) dialog.getResult();
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
