package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

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
import org.palladiosimulator.pcm.repository.CollectionDataType;
import org.palladiosimulator.pcm.repository.InfrastructureSignature;
import org.palladiosimulator.pcm.repository.Interface;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.Signature;
import org.palladiosimulator.pcm.seff.CollectionIteratorAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;

public class AddCollectionIteratorAction implements IExternalJavaAction {
	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		CollectionIteratorAction action = (CollectionIteratorAction) parameters.get("instance");
		ResourceDemandingBehaviour resourceDemandingBehaviour = (ResourceDemandingBehaviour) parameters.get("resourceDemandingBehaviour");
		ServiceEffectSpecification seff = SEFFUtil.getEnclosingSEFF(resourceDemandingBehaviour);
		Parameter parameter = getParameter(action, seff);
		action.setParameter_CollectionIteratorAction(parameter);
		
	}

	private Parameter getParameter(CollectionIteratorAction action, ServiceEffectSpecification seff) {
		Collection<Object> filter = new ArrayList<Object>();
		filter.add(Repository.class);
		filter.add(Interface.class);
		filter.add(Signature.class);
		filter.add(Parameter.class);
		

		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();

		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				action.eResource().getResourceSet());
		

		dialog.setProvidedService(Parameter.class);

		for (Object o : dialog.getTreeViewer().getExpandedElements()) {
			if (o instanceof Signature) {
				if (!o.equals(seff.getDescribedService__SEFF())) {
					dialog.getTreeViewer().remove(o);
					continue;
				}
				EList<Parameter> parameters = null;
				
				if (o instanceof OperationSignature)
					parameters = ((OperationSignature) o).getParameters__OperationSignature();
				else if(o instanceof InfrastructureSignature) {
					parameters = ((InfrastructureSignature) o).getParameters__InfrastructureSignature();
				}
				
				for (Parameter p : parameters) {
					if (!(p.getDataType__Parameter() instanceof CollectionDataType))
						dialog.getTreeViewer().remove(p);
				}
				
			}
		}
		

		dialog.open();
		return (Parameter) dialog.getResult();
	}


	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
