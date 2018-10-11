package org.palladiosimulator.editors.sirius.resourceenvironment.custom.externaljavaactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.commons.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.editors.commons.dialogs.stoex.StochasticExpressionEditDialog;
import org.palladiosimulator.pcm.core.CoreFactory;
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.resourceenvironment.HDDProcessingResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.ProcessingResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentPackage;
import org.palladiosimulator.pcm.resourcetype.ProcessingResourceType;
import org.palladiosimulator.pcm.resourcetype.ResourceRepository;
import org.palladiosimulator.pcm.resourcetype.SchedulingPolicy;

import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

/**
 * @author tzwickl
 */
public class AddHDDProcessingResourceSpecification implements IExternalJavaAction {

	private static final String SET_READ_PROCESSING_RATE = "Set Read Processing Rate";
	private static final String SET_WRITE_PROCESSING_RATE = "Set Write Processing Rate";

    /**
     * Parameter name for the newly created communication linking resource. This name is used as a
     * key in the parameter key-value map.
     */
	private static final String NEW_HDD_PROCESSING_RESOURCE_SPECIFICATION = "newHDDProcessingResourceSpecification";

    public AddHDDProcessingResourceSpecification() {
        super();
    }

    @Override
    public boolean canExecute(final Collection<? extends EObject> selections) {
        return true;
    }

    @Override
    public void execute(final Collection<? extends EObject> selections, final Map<String, Object> parameters) {

		final Object parameter = parameters.get(NEW_HDD_PROCESSING_RESOURCE_SPECIFICATION);

		if (parameter == null || !(parameter instanceof HDDProcessingResourceSpecification)) {
            return;
        }

		final HDDProcessingResourceSpecification hddProcessingResourceSpecification = (HDDProcessingResourceSpecification) parameter;

		// 1. dialog
		final ProcessingResourceType prt = getResourceType(hddProcessingResourceSpecification);
		if (prt != null) {
			hddProcessingResourceSpecification.setActiveResourceType_ActiveResourceSpecification(prt);
		} else {
			return;
		}

		// set processing rate always to 1
		final PCMRandomVariable processingRate = CoreFactory.eINSTANCE.createPCMRandomVariable();
		processingRate.setSpecification("1");
		hddProcessingResourceSpecification.setProcessingRate_ProcessingResourceSpecification(processingRate);

		//2. dialog
		final PCMRandomVariable readProcessingRate = getReadProcessingRate();
		if (readProcessingRate != null) {
			hddProcessingResourceSpecification.setReadProcessingRate(readProcessingRate);
		} else {
			return;
		}

		//3. dialog
		final PCMRandomVariable writeProcessingRate = getWriteProcessingRate();
		if (writeProcessingRate != null) {
			hddProcessingResourceSpecification.setWriteProcessingRate(writeProcessingRate);
		} else {
			return;
		}

		//4. dialog
		final SchedulingPolicy sp = getSchedulingPolicy(hddProcessingResourceSpecification);
        if (sp != null) {
			hddProcessingResourceSpecification.setSchedulingPolicy(sp);
		} else {
			return;
		}
    }

	private ProcessingResourceType getResourceType(
			final ProcessingResourceSpecification processingResourceSpecification) {

		final ArrayList<Object> filterList = new ArrayList<Object>(); // positive filter
		// Set types to show and their super types
		filterList.add(ProcessingResourceType.class);
		filterList.add(ResourceRepository.class);
		final ArrayList<EReference> additionalReferences = new ArrayList<EReference>();
		// set EReference that should be set (in this case: active resource type)
		additionalReferences.add(ResourceenvironmentPackage.eINSTANCE
				.getProcessingResourceSpecification_ActiveResourceType_ActiveResourceSpecification());
		final PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), filterList, additionalReferences,
				processingResourceSpecification.getResourceContainer_ProcessingResourceSpecification().eResource()
						.getResourceSet());

		dialog.setProvidedService(ProcessingResourceType.class);
		dialog.open();
		if (dialog.getResult() == null || !(dialog.getResult() instanceof ProcessingResourceType)) {
			return null;
		}
		return (ProcessingResourceType) dialog.getResult();
	}

    private SchedulingPolicy getSchedulingPolicy(
            final ProcessingResourceSpecification processingResourceSpecification) {
        final ResourceSet set = (processingResourceSpecification.getResourceContainer_ProcessingResourceSpecification())
                .eResource().getResourceSet();
        final ArrayList<Object> filterList = new ArrayList<Object>(); // positive filter
        // Set types to show and their super types
        filterList.add(SchedulingPolicy.class);
        filterList.add(ResourceRepository.class);
        final ArrayList<EReference> additionalReferences = new ArrayList<EReference>();
        // set EReference that should be set (in this case: SchedulingPolicy)
        additionalReferences
                .add(ResourceenvironmentPackage.eINSTANCE.getProcessingResourceSpecification_SchedulingPolicy());
        final PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), filterList, additionalReferences, set);
        dialog.setProvidedService(SchedulingPolicy.class);
        dialog.open();
        if (dialog.getResult() == null || !(dialog.getResult() instanceof SchedulingPolicy)) {
            return null;
        }
        return (SchedulingPolicy) dialog.getResult();
    }

	private PCMRandomVariable getReadProcessingRate() {
        final PCMRandomVariable pcmRandomVariable = CoreFactory.eINSTANCE.createPCMRandomVariable();
        pcmRandomVariable.setSpecification("");

        final StochasticExpressionEditDialog dialog = new StochasticExpressionEditDialog(
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), TypeEnum.DOUBLE, pcmRandomVariable);
		dialog.setDisplayTitle(SET_READ_PROCESSING_RATE);
        dialog.open();
        if (dialog.getResult() == null) {
            return null;
        }

        pcmRandomVariable.setSpecification(dialog.getResultText());

        return pcmRandomVariable;
    }

	private PCMRandomVariable getWriteProcessingRate() {
		final PCMRandomVariable pcmRandomVariable = CoreFactory.eINSTANCE.createPCMRandomVariable();
		pcmRandomVariable.setSpecification("");

		final StochasticExpressionEditDialog dialog = new StochasticExpressionEditDialog(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), TypeEnum.DOUBLE, pcmRandomVariable);
		dialog.setDisplayTitle(SET_WRITE_PROCESSING_RATE);
		dialog.open();
		if (dialog.getResult() == null) {
			return null;
		}

		pcmRandomVariable.setSpecification(dialog.getResultText());

		return pcmRandomVariable;
	}

}
