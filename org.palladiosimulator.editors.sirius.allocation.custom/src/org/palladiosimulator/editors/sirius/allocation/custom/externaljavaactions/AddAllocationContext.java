package org.palladiosimulator.editors.sirius.allocation.custom.externaljavaactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.commons.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.pcm.allocation.AllocationContext;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.EventChannel;

public class AddAllocationContext implements IExternalJavaAction {


	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

	
    @Override
    public void execute(final Collection<? extends EObject> selections, final Map<String, Object> parameters) {
    	AllocationContext allocationContext = (AllocationContext) parameters.get("instance");
    	EObject obj = getObject(allocationContext);
    	if (obj == null)
    		return;
    	if(obj instanceof AssemblyContext) {
    		AssemblyContext assemblyContext = (AssemblyContext) obj;
    		allocationContext.setAssemblyContext_AllocationContext(assemblyContext);
    	}
    	else if(obj instanceof EventChannel) {
    		EventChannel eventChannel = (EventChannel) obj;
    		allocationContext.setEventChannel__AllocationContext(eventChannel);
    	}
    }
   
	private EObject getObject(AllocationContext allocationContext) {
		Collection<Object> filter = new ArrayList<Object>();
		filter.add(org.palladiosimulator.pcm.system.System.class);
		filter.add(AssemblyContext.class);
		filter.add(EventChannel.class);
		

		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();
		
		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				allocationContext.eResource().getResourceSet());

		dialog.setProvidedService(EObject.class);
		dialog.open();
		return dialog.getResult();
	}

	@Override
    public boolean canExecute(final Collection<? extends EObject> selections) {
        return true;
    }




}
