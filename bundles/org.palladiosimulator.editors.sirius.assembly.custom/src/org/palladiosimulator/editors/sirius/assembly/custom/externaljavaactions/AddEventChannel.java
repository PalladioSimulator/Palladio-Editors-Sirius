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
import org.palladiosimulator.pcm.core.composition.EventChannel;
import org.palladiosimulator.pcm.repository.EventGroup;
import org.palladiosimulator.pcm.repository.Repository;

public class AddEventChannel implements IExternalJavaAction {
    
    @Override
    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
        EventChannel eventChannel = (EventChannel) parameters.get("instance");
        EventGroup eventGroup = getEventGroup(eventChannel);
        eventChannel.setEventGroup__EventChannel(eventGroup);
    }

    private EventGroup getEventGroup(final EventChannel eventChannel) {
        //Repository > EventGroup
        Collection<Object> filter = new ArrayList<Object>();
        filter.add(Repository.class);
        filter.add(EventGroup.class);
        

        // Additional Child References
        Collection<EReference> additionalChildReferences = new ArrayList<EReference>();
        
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(shell, filter, additionalChildReferences,
                eventChannel.eResource().getResourceSet());
        
        dialog.setProvidedService(EventGroup.class);
        
        dialog.open();
        
        return (EventGroup) dialog.getResult();
    }

    @Override
    public boolean canExecute(Collection<? extends EObject> selections) {
        return true;
    }

}
