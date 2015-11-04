package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.PlatformUI;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofile.provider.EMFProfileItemProviderAdapterFactory;
import org.palladiosimulator.mdsdprofiles.api.ProfileAPI;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;

/**
 * A sirius action for selecting and applying a {@link Stereotype} to the selected object.
 * 
 * @author Max Schettler
 *
 */
public class ApplyUnapplyStereotypeAction implements IExternalJavaAction {

    private static final ILabelProvider LABEL_PROVIDER = getLabelProvider();
    private static final String SELECT_APPLIED_STEREOTYPES_MESSAGE = "Select applied stereotypes";

    @Override
    public boolean canExecute(final Collection<? extends EObject> selections) {
        return selections.size() == 1;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void execute(final Collection<? extends EObject> selections, final Map<String, Object> parameters) {
        final EObject target = ((DSemanticDecorator) selections.iterator().next()).getTarget();
        final boolean targetHasProfileApplication = ProfileAPI.hasProfileApplication(target.eResource());

        final FeatureEditorDialog stereotypeSelectionDialog = new FeatureEditorDialog(
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), LABEL_PROVIDER, target,
                target.eClass().getEAllStructuralFeatures().get(0).getEType(),
                targetHasProfileApplication ? StereotypeAPI.getAppliedStereotypes(target) : Collections.EMPTY_LIST,
                SELECT_APPLIED_STEREOTYPES_MESSAGE,
                targetHasProfileApplication ? StereotypeAPI.getApplicableStereotypes(target) : Collections.EMPTY_LIST,
                true, false, false);

        if (stereotypeSelectionDialog.open() == Dialog.OK) {
            StereotypeAPI.updateStereotypeApplications(target,
                    (EList<Stereotype>) stereotypeSelectionDialog.getResult());
        }

    }

    private static ILabelProvider getLabelProvider() {
        final EMFProfileItemProviderAdapterFactory adapterFactory = new EMFProfileItemProviderAdapterFactory();
        return new AdapterFactoryLabelProvider(adapterFactory);
    }

}
