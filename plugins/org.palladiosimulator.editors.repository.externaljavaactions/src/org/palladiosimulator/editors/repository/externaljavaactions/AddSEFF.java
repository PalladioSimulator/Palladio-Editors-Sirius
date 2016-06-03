package org.palladiosimulator.editors.repository.externaljavaactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.pcm.repository.Interface;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.Signature;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.SeffPackage;

public class AddSEFF implements IExternalJavaAction {

	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		ResourceDemandingSEFF seff = (ResourceDemandingSEFF) parameters.get("instance");
		Signature signature = getSignature(seff);
		seff.setDescribedService__SEFF(signature);
	}

	private Signature getSignature(ResourceDemandingSEFF seff) {
		// Filter list (Repository > Interface > Signature)
		Collection<Object> filter = new ArrayList<Object>();
		filter.add(Repository.class);
		filter.add(Interface.class);
		filter.add(Signature.class);

		// Additional Child References
		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();
		additionalChildReferences.add(SeffPackage.Literals.SERVICE_EFFECT_SPECIFICATION__DESCRIBED_SERVICE_SEFF);

		// Creating the dialog
		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				seff.eResource().getResourceSet());

		// Setting the needed object type
		dialog.setProvidedService(Signature.class);

		dialog.open();

		return (Signature) dialog.getResult();
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
