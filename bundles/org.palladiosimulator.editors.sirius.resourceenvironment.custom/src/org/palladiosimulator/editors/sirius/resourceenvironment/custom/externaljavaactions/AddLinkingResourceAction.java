package org.palladiosimulator.editors.sirius.resourceenvironment.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.commons.dialogs.stoex.StochasticExpressionEditDialog;
import org.palladiosimulator.editors.sirius.custom.util.SiriusCustomUtil;
import org.palladiosimulator.pcm.core.CoreFactory;
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.reliability.FailureType;
import org.palladiosimulator.pcm.reliability.SoftwareInducedFailureType;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.resourceenvironment.CommunicationLinkResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.pcm.resourcetype.CommunicationLinkResourceType;
import org.palladiosimulator.pcm.resourcetype.ResourceRepository;

import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

/**
 * UI-based action to create linking resources. Opens dialogs to request properties of linking
 * resources (for setting latency and throughput).
 * 
 * As this action implements <link>IExternalJavaAction</link>, Sirius editors can invoke this
 * action.
 * 
 * @author Sebastian Lehrig, Edith Kegel
 */
public class AddLinkingResourceAction implements IExternalJavaAction {

    /**
     * The latency display title.
     */
    private static final String LATENCY_DISPLAY_TITLE = "Set Latency";

    /**
     * The throughput display title.
     */
    private static final String THROUGHPUT_DISPLAY_TITLE = "Set Throughput";

    /**
     * Parameter name for the newly created communication linking resource. This name is used as a
     * key in the parameter key-value map.
     */
    private static final String NEW_COMMUNICATION_LINK_RESOURCE_SPECIFICATION = "newCommunicationLinkResourceSpecification";

    public AddLinkingResourceAction() {
        super();
    }

    @Override
    public boolean canExecute(final Collection<? extends EObject> selections) {
        for (final EObject object : selections) {
            if (object instanceof ResourceEnvironment)
                return true;
        }
        return false;
    }

    @Override
    public void execute(final Collection<? extends EObject> selections, final Map<String, Object> parameters) {
        final Object parameter = parameters.get(NEW_COMMUNICATION_LINK_RESOURCE_SPECIFICATION);
        if (parameter == null || !(parameter instanceof CommunicationLinkResourceSpecification)) {
            return;
        }
        final CommunicationLinkResourceSpecification communicationLinkResourceSpecification = (CommunicationLinkResourceSpecification) parameter;

        // latency
        final PCMRandomVariable latency = getRandomVariableFromStoExDialog(LATENCY_DISPLAY_TITLE);
        if (latency == null) {
            return;
        }
        communicationLinkResourceSpecification.setLatency_CommunicationLinkResourceSpecification(latency);

        // throughput
        final PCMRandomVariable throughput = getRandomVariableFromStoExDialog(THROUGHPUT_DISPLAY_TITLE);
        if (throughput == null) {
            return;
        }
        communicationLinkResourceSpecification.setThroughput_CommunicationLinkResourceSpecification(throughput);
        
        //Communication Link
        
        Session session = SessionManager.INSTANCE.getSession(communicationLinkResourceSpecification);
        URI uri = URI.createURI("pathmap://PCM_MODELS/Palladio.resourcetype");
        Resource palladioResources = SiriusCustomUtil.getResourceByURI(uri, session);
        
        if (palladioResources != null) {
			ResourceRepository rep = (ResourceRepository) palladioResources.getContents().iterator().next();
			for (EObject o : rep.eContents()) {
				if ((o instanceof CommunicationLinkResourceType)
						&& ((CommunicationLinkResourceType) o ).getEntityName().equals("LAN")) {
					communicationLinkResourceSpecification.setCommunicationLinkResourceType_CommunicationLinkResourceSpecification((CommunicationLinkResourceType) o);
					break;
				}
			}
		}
        
    }

	/**
     * Opens a StoxEx dialog and returns the resulting {@link PCMRandomVariable}.
     * 
     * @param displayTitle
     *            Title of the StoEx dialog
     * @return PCMRandomVariable if user entered valid data and confirmed. Will return null if user
     *         canceled dialog
     */
    private PCMRandomVariable getRandomVariableFromStoExDialog(final String displayTitle) {
        final PCMRandomVariable randomVariable = CoreFactory.eINSTANCE.createPCMRandomVariable();
        randomVariable.setSpecification("");

        final StochasticExpressionEditDialog dialog = new StochasticExpressionEditDialog(
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), TypeEnum.DOUBLE, randomVariable);
        dialog.setDisplayTitle(displayTitle);
        dialog.open();

        if (dialog.getReturnCode() == Dialog.CANCEL) {
            return null;
        }

        randomVariable.setSpecification(dialog.getResultText());
        return randomVariable;
    }
    
}
