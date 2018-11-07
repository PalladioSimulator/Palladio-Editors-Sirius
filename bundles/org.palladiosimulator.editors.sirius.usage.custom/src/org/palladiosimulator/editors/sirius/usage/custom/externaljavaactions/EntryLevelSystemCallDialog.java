package org.palladiosimulator.editors.sirius.usage.custom.externaljavaactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.commons.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.usagemodel.EntryLevelSystemCall;
import org.palladiosimulator.pcm.system.System;

public class EntryLevelSystemCallDialog implements IExternalJavaAction {
	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	
	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		EntryLevelSystemCall element = (EntryLevelSystemCall) parameters.get("instance");
		OperationProvidedRole oldRole = element.getProvidedRole_EntryLevelSystemCall();
		
		OperationProvidedRole role = getOperationProvidedRole(element);
		if (role != null) {
			element.setProvidedRole_EntryLevelSystemCall(role);
			
			OperationSignature signature = getOperationSignature(element);
			if (signature != null) {
				element.setOperationSignature__EntryLevelSystemCall(signature);
			}
			else {
				element.setProvidedRole_EntryLevelSystemCall(oldRole);
			}
		}
		
	}
	
    private OperationSignature getOperationSignature(EntryLevelSystemCall element) {
    	Collection<Object> filter = new ArrayList<Object>();
		filter.add(Repository.class);
		filter.add(OperationInterface.class);
		filter.add(OperationSignature.class);
		

		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();
		
		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				element.eResource().getResourceSet());
		
		dialog.setProvidedService(OperationSignature.class);
		

		for (Object o : dialog.getTreeViewer().getExpandedElements()) {
			if (!(o instanceof OperationInterface))
				continue;
			OperationInterface oi = (OperationInterface) o;
			if (!element.getProvidedRole_EntryLevelSystemCall().getProvidedInterface__OperationProvidedRole().equals(oi))
				dialog.getTreeViewer().remove(o);

		}
		
		dialog.open();
		
		return (OperationSignature) dialog.getResult();
	}

	private OperationProvidedRole getOperationProvidedRole(EntryLevelSystemCall element) {
		Collection<Object> filter = new ArrayList<Object>();
		filter.add(System.class);
		filter.add(OperationProvidedRole.class);
		

		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();
		
		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				element.eResource().getResourceSet());
		
		dialog.setProvidedService(OperationProvidedRole.class);
		
		dialog.open();
		
		return (OperationProvidedRole) dialog.getResult();
	}
	

	@Override
    public boolean canExecute(final Collection<? extends EObject> arg0) {
        return true;
    }
}
