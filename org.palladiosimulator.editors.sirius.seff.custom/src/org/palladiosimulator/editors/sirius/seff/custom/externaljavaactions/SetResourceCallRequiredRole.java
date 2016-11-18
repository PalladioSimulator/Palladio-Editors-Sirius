package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.commons.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.pcm.core.entity.ResourceRequiredRole;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.resourcetype.ResourceInterface;
import org.palladiosimulator.pcm.seff.seff_performance.ResourceCall;

/**
 * @author tzwickl
 */
public class SetResourceCallRequiredRole implements IExternalJavaAction {


	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	@Override
	public void execute(final Collection<? extends EObject> selections, final Map<String, Object> parameters) {
		ResourceCall call = (ResourceCall) parameters.get("instance");
		ResourceRequiredRole requiredRole = getSignature(call);
		call.setResourceRequiredRole__ResourceCall(requiredRole);

	}

	private ResourceRequiredRole getSignature(final ResourceCall call) {
		Collection<Object> filter = new ArrayList<Object>();
		
		filter.add(Repository.class);
		filter.add(ResourceRequiredRole.class);
		filter.add(BasicComponent.class);
		filter.add(ResourceInterface.class);

		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();
		
		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				call.eResource().getResourceSet());
		

		dialog.setProvidedService(ResourceRequiredRole.class);
		
		dialog.open();

		return (ResourceRequiredRole) dialog.getResult();
	}

	@Override
	public boolean canExecute(final Collection<? extends EObject> selections) {
		return true;
	}

}
