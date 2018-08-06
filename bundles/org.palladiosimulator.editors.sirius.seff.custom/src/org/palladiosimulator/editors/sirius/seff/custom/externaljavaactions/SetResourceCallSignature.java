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
import org.palladiosimulator.pcm.resourcetype.ResourceInterface;
import org.palladiosimulator.pcm.resourcetype.ResourceRepository;
import org.palladiosimulator.pcm.resourcetype.ResourceSignature;
import org.palladiosimulator.pcm.seff.seff_performance.ResourceCall;

/**
 * 
 * @author tzwickl
 */
public class SetResourceCallSignature implements IExternalJavaAction {


	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	@Override
	public void execute(final Collection<? extends EObject> selections, final Map<String, Object> parameters) {
		ResourceCall call = (ResourceCall) parameters.get("instance");
		ResourceSignature sig = getSignature(call);
		call.setSignature__ResourceCall(sig);
	}

	private ResourceSignature getSignature(final ResourceCall call) {
		Collection<Object> filter = new ArrayList<Object>();
		
		filter.add(ResourceRepository.class);
		filter.add(ResourceSignature.class);
		filter.add(ResourceInterface.class);

		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();
		
		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				call.eResource().getResourceSet());
		

		dialog.setProvidedService(ResourceSignature.class);
		
		// only take required InfrastructureInterfaces
		for (Object o : dialog.getTreeViewer().getExpandedElements()) {
			if (!(o instanceof ResourceInterface)) {
				continue;
			}

			ResourceRequiredRole resourceRequiredRole = call.getResourceRequiredRole__ResourceCall();

			if (!resourceRequiredRole.getRequiredResourceInterface__ResourceRequiredRole().equals(o)) {
				dialog.getTreeViewer().remove(o);
			}
		}

		dialog.open();

		return (ResourceSignature) dialog.getResult();
	}

	@Override
	public boolean canExecute(final Collection<? extends EObject> selections) {
		return true;
	}

}
