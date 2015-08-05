package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.commands.AddLatencyAndThroughputRecordingCommand;
import org.palladiosimulator.pcm.resourceenvironment.CommunicationLinkResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;

public class AddLinkingResourceAction implements IExternalJavaAction {

    private static final String SELF_KEY = "self";
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
        final Object selfParameter = parameters.get(SELF_KEY);
        final Object linkParameter = parameters.get(NEW_COMMUNICATION_LINK_RESOURCE_SPECIFICATION);

        if (selfParameter == null || !(selfParameter instanceof EObject) || linkParameter == null
                || !(linkParameter instanceof CommunicationLinkResourceSpecification)) {
            return;
        }

        final TransactionalEditingDomain domain = SessionManager.INSTANCE.getSession((EObject) selfParameter)
                .getTransactionalEditingDomain();
        final AddLatencyAndThroughputRecordingCommand acommand = new AddLatencyAndThroughputRecordingCommand(domain,
                (CommunicationLinkResourceSpecification) linkParameter);

        domain.getCommandStack().execute(acommand);
    }

}
