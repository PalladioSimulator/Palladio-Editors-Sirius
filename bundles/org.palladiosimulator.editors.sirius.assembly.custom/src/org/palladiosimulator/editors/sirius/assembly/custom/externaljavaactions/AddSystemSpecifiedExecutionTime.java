package org.palladiosimulator.editors.sirius.assembly.custom.externaljavaactions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.commons.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.pcm.qosannotations.SpecifiedQoSAnnotation;
import org.palladiosimulator.pcm.qosannotations.qos_performance.SystemSpecifiedExecutionTime;
import org.palladiosimulator.pcm.repository.Interface;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.Signature;

public class AddSystemSpecifiedExecutionTime implements IExternalJavaAction {
	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		SystemSpecifiedExecutionTime sset = (SystemSpecifiedExecutionTime) parameters.get("instance");
		Signature sig = getSignature(sset);
		for (SpecifiedQoSAnnotation s : sset.getQosAnnotations_SpecifiedQoSAnnotation().getSpecifiedQoSAnnotations_QoSAnnotations()) {
			if ((s instanceof SystemSpecifiedExecutionTime) && 
					(s.getSignature_SpecifiedQoSAnnation() != null) && 
					(s.getRole_SpecifiedQoSAnnotation().equals(sset.getRole_SpecifiedQoSAnnotation())) && 
					(s.getSignature_SpecifiedQoSAnnation().equals(sig)))
				return;
		}
		sset.setSignature_SpecifiedQoSAnnation(sig);
			
	}

	private Signature getSignature(SystemSpecifiedExecutionTime sset) {
		Collection<Object> filter = new ArrayList<Object>();
		filter.add(Repository.class);
		filter.add(Interface.class);
		filter.add(Signature.class);
		
		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();
		
		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				sset.eResource().getResourceSet());
		
		dialog.setProvidedService(Signature.class);
		
		dialog.open();
		
		return (Signature) dialog.getResult();
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
