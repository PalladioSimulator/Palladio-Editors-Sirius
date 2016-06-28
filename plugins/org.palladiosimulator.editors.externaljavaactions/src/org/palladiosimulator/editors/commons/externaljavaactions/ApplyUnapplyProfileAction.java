package org.palladiosimulator.editors.commons.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.PlatformUI;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.provider.EMFProfileItemProviderAdapterFactory;
import org.palladiosimulator.mdsdprofiles.api.ProfileAPI;

/**
 * A sirius action for selecting and applying a {@link Profile} to the {@link Resource} of the
 * selected {@link EObject}. selected object.
 * 
 * @author Max Schettler
 *
 */
public class ApplyUnapplyProfileAction implements IExternalJavaAction {

    private static final ILabelProvider LABEL_PROVIDER = getLabelProvider();
    private static final String SELECT_APPLIED_PROFILES_MESSAGE = "Select applied profiles";

    @Override
    public boolean canExecute(final Collection<? extends EObject> selections) {
        return selections.size() == 1;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void execute(final Collection<? extends EObject> selections, final Map<String, Object> parameters) {
        final EObject target = ((DSemanticDecorator) selections.iterator().next()).getTarget();
        final Resource targetResource = target.eResource();

        final FeatureEditorDialog profileSelectionDialog = new FeatureEditorDialog(
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), LABEL_PROVIDER, target,
                target.eClass().getEAllStructuralFeatures().get(0).getEType(),
                ProfileAPI.getAppliedProfiles(targetResource), SELECT_APPLIED_PROFILES_MESSAGE,
                ProfileAPI.getApplicableProfiles(targetResource), false, true, true);

        if (profileSelectionDialog.open() == Dialog.OK) {
            ProfileAPI.updateProfileApplications(targetResource, (EList<Profile>) profileSelectionDialog.getResult());
        }

    }

    private static ILabelProvider getLabelProvider() {
        final EMFProfileItemProviderAdapterFactory adapterFactory = new EMFProfileItemProviderAdapterFactory();
        return new AdapterFactoryLabelProvider(adapterFactory);
    }

}
