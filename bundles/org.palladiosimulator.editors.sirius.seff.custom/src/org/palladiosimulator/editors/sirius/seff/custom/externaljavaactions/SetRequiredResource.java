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
import org.palladiosimulator.pcm.resourcetype.ProcessingResourceType;
import org.palladiosimulator.pcm.resourcetype.ResourceRepository;
import org.palladiosimulator.pcm.seff.seff_performance.ParametricResourceDemand;

public class SetRequiredResource implements IExternalJavaAction {


	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		ParametricResourceDemand rd = (ParametricResourceDemand) parameters.get("instance");
		ProcessingResourceType rt = getProcessingResourceType(rd);
		rd.setRequiredResource_ParametricResourceDemand(rt);

	}

	private ProcessingResourceType getProcessingResourceType(ParametricResourceDemand rd) {
		Collection<Object> filter = new ArrayList<Object>();
		filter.add(ResourceRepository.class);
		filter.add(ProcessingResourceType.class);
		
		// Additional Child References
		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();

		// Creating the dialog
		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				rd.eResource().getResourceSet());

		// Setting the needed object type
		dialog.setProvidedService(ProcessingResourceType.class);

		dialog.open();

		return (ProcessingResourceType) dialog.getResult();
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		// TODO Auto-generated method stub
		return true;
	}

}
