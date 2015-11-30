package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryComponent;

public class AddAssemblyContext implements IExternalJavaAction {

    private static final String NEW_ASSEMBLY_CONTEXT = "newAssemblyContext";

    public AddAssemblyContext() {
        super();
    }

    @Override
    public boolean canExecute(final Collection<? extends EObject> selections) {
        return true;
    }

    @Override
    public void execute(final Collection<? extends EObject> selections, final Map<String, Object> parameters) {

        final Object parameter = parameters.get(NEW_ASSEMBLY_CONTEXT);

        if (parameter == null || !(parameter instanceof AssemblyContext)) {
            return;
        }

        final AssemblyContext assemblyContext = (AssemblyContext) parameter;

        assemblyContext.setEncapsulatedComponent__AssemblyContext(getRepositoryComponent(assemblyContext));

    }

    private RepositoryComponent getRepositoryComponent(final AssemblyContext assemblyContext) {

        final ArrayList<Object> filterList = new ArrayList<Object>();
        filterList.add(Repository.class);
        // filterList.add(ProvidesComponentType.class); /necessary?
        filterList.add(RepositoryComponent.class);

        final ArrayList<EReference> additionalReferences = new ArrayList<EReference>();
        final PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), filterList, additionalReferences,
                assemblyContext.eResource().getResourceSet());
        dialog.setProvidedService(RepositoryComponent.class);
        dialog.open();
        return (RepositoryComponent) dialog.getResult();
    }

}
