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
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofile.provider.EMFProfileItemProviderAdapterFactory;
import org.palladiosimulator.mdsdprofiles.api.ProfileAPI;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.entity.ComposedProvidingRequiringEntity;

/**
 * A sirius action for selecting and applying a {@link Stereotype} to the
 * selected object.
 * 
 * @author Max Schettler
 *
 */
public class ApplyUnapplyStereotypeAction implements IExternalJavaAction {

	private static final ILabelProvider LABEL_PROVIDER = getLabelProvider();
	private static final String SELECT_APPLIED_STEREOTYPES_MESSAGE = "Select applied stereotypes";

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		if (selections.size() != 1)
			return false;
		final EObject selection = ((DDiagramElement) selections.iterator().next()).getTarget();
		return selection instanceof AssemblyContext || selection instanceof ComposedProvidingRequiringEntity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		final EObject target = ((DDiagramElement) selections.iterator().next()).getTarget();
		final boolean targetHasProfileApplication = ProfileAPI.hasProfileApplication(target.eResource());
		
		final FeatureEditorDialog stereotypeSelectionDialog = new FeatureEditorDialog(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), LABEL_PROVIDER,
				target, target.eClass().getEAllStructuralFeatures().get(0).getEType(),
				targetHasProfileApplication ? StereotypeAPI.getAppliedStereotypes(target) : Collections.EMPTY_LIST,
				SELECT_APPLIED_STEREOTYPES_MESSAGE,
				targetHasProfileApplication ? StereotypeAPI.getApplicableStereotypes(target) : Collections.EMPTY_LIST,
				true, false, false);

		if (stereotypeSelectionDialog.open() == Dialog.OK) {
			StereotypeAPI.updateStereotypeApplications(target, (EList<Stereotype>) stereotypeSelectionDialog.getResult());
		}
		
	}

	private static ILabelProvider getLabelProvider() {
		final EMFProfileItemProviderAdapterFactory adapterFactory = new EMFProfileItemProviderAdapterFactory();
		return new AdapterFactoryLabelProvider(adapterFactory);
	}

}
