package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.commons.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RequiredRole;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;

public class AddExternalCallAction implements IExternalJavaAction {
	

	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	
	
	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		ExternalCallAction extCall = (ExternalCallAction) parameters.get("instance");
		HashMap<OperationInterface, OperationRequiredRole> requiredRolesMap = new HashMap<OperationInterface, OperationRequiredRole>();
		OperationSignature os = getOperationSignature(extCall, requiredRolesMap);
		if (os != null) {
			extCall.setCalledService_ExternalService(os);			
			extCall.setRole_ExternalService(requiredRolesMap.get(os.getInterface__OperationSignature()));
		}

	}

	private OperationSignature getOperationSignature(ExternalCallAction extCall, HashMap<OperationInterface, OperationRequiredRole> requiredRolesMap) {

		Collection<Object> filter = new ArrayList<Object>();
		
		filter.add(Repository.class);
		filter.add(OperationInterface.class);
		filter.add(OperationSignature.class);

		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();
		
		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				extCall.eResource().getResourceSet());
		

		dialog.setProvidedService(OperationSignature.class);
		
		for (Object o : dialog.getTreeViewer().getExpandedElements()) {
			if (!(o instanceof OperationInterface)) //if the current object is not an OperationInterface, skip.
				continue;
			
			ResourceDemandingBehaviour rd = extCall.getResourceDemandingBehaviour_AbstractAction();
			
			ServiceEffectSpecification seff = SEFFUtil.getEnclosingSEFF(rd);
			BasicComponent parent = seff.getBasicComponent_ServiceEffectSpecification();
			
			//if o is not referenced by any OperationRequiredRole, remove it from the tree viewer
			OperationRequiredRole requiredRole = getOperationRequiredRole(parent.getRequiredRoles_InterfaceRequiringEntity(), (OperationInterface) o);
		    if (requiredRole != null)
		        requiredRolesMap.put((OperationInterface) o, requiredRole);
		    else
		        dialog.getTreeViewer().remove(o);
			

		}
		
		dialog.open();

		return (OperationSignature) dialog.getResult();
	}
	
	private OperationRequiredRole getOperationRequiredRole(Collection<RequiredRole> requiredRoles, OperationInterface operationInterface) {
	    for (RequiredRole r : requiredRoles) {
	        if (!(r instanceof OperationRequiredRole))
	            continue;
	        
	        if (((OperationRequiredRole) r).getRequiredInterface__OperationRequiredRole() == operationInterface)
	            return (OperationRequiredRole) r;
	    }
	    return null;
	}
	

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
