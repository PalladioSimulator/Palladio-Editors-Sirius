package org.palladiosimulator.editors.allocation.externaljavaactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.pcm.allocation.AllocationContext;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.EventChannel;

public class AddAllocationContext implements IExternalJavaAction {

    private static final Collection<EReference> ADDITIONAL_REFERENCES = new ArrayList<EReference>();
    private static final Collection<Object> FILTER_LIST = new ArrayList<Object>();

    static {
        FILTER_LIST.add(org.palladiosimulator.pcm.system.System.class);
        FILTER_LIST.add(AssemblyContext.class);
        FILTER_LIST.add(EventChannel.class);
    }

    private static final String NEW_ALLOCATION_CONTEXT = "newAllocationContext";

    public AddAllocationContext() {
        super();
    }

    @Override
    public boolean canExecute(final Collection<? extends EObject> selections) {
        return true;
    }

    @Override
    public void execute(final Collection<? extends EObject> selections, final Map<String, Object> parameters) {

        final Object parameter = parameters.get(NEW_ALLOCATION_CONTEXT);

        if (parameter == null || !(parameter instanceof AllocationContext)) {
            return;
        }

        final AllocationContext allocationContext = (AllocationContext) parameter;
        final org.palladiosimulator.pcm.system.System system = allocationContext.getAllocation_AllocationContext()
                .getSystem_Allocation();
        // getAllocation_AllocationContext ist noch null >.< System muss im odesign gesetzt
        // werden!!!!
        // TODO

        final EObject dialogResult = getDialogResult(system);

        if (dialogResult instanceof AssemblyContext) {
            allocationContext.setAssemblyContext_AllocationContext((AssemblyContext) dialogResult);
            allocationContext.setEntityName("Allocation_" + allocationContext.getEntityName() + " <" + allocationContext
                    .getAssemblyContext_AllocationContext().getEncapsulatedComponent__AssemblyContext().getEntityName()
                    + ">");
        }

        else if (dialogResult instanceof EventChannel) {
            allocationContext.setEventChannel__AllocationContext((EventChannel) dialogResult);
            allocationContext.setEntityName("Allocation_" + allocationContext.getEntityName() + " <"
                    + allocationContext.getEventChannel__AllocationContext().getEntityName() + ">");
        }

    }

    final EObject getDialogResult(final org.palladiosimulator.pcm.system.System system) {

        final PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), FILTER_LIST, ADDITIONAL_REFERENCES,
                system.eResource().getResourceSet());

        // dialog.setProvidedService(org.palladiosimulator.pcm.system.System.class);
        dialog.open();
        return dialog.getResult();
    }

}
