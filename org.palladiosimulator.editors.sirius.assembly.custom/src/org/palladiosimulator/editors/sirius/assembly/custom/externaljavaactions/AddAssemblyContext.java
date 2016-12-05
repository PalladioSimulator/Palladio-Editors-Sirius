package org.palladiosimulator.editors.sirius.assembly.custom.externaljavaactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.commons.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryComponent;

public class AddAssemblyContext implements IExternalJavaAction {

    private static final String NEW_ASSEMBLY_CONTEXT = "newAssemblyContext";

    private static final Collection<EReference> ADDITIONAL_REFERENCES = new ArrayList<EReference>();
    private static final Collection<Object> FILTER_LIST = new ArrayList<Object>();

    static {
        FILTER_LIST.add(Repository.class);
        FILTER_LIST.add(RepositoryComponent.class);
    }

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

        final RepositoryComponent repositoryComponent = getRepositoryComponent(assemblyContext);
        if (repositoryComponent != null) {
            assemblyContext.setEncapsulatedComponent__AssemblyContext(repositoryComponent);
            assemblyContext.setEntityName("Assembly_" + repositoryComponent.getEntityName());
        }
    }

    private RepositoryComponent getRepositoryComponent(final AssemblyContext assemblyContext) {

        final PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), FILTER_LIST, ADDITIONAL_REFERENCES,
                assemblyContext.eResource().getResourceSet());
        dialog.setProvidedService(RepositoryComponent.class);
        dialog.open();
        if (dialog.getResult() == null) {
            return null;
        }

        if (!(dialog.getResult() instanceof RepositoryComponent)) {

            return null;
        }

        return (RepositoryComponent) dialog.getResult();
    }

}
