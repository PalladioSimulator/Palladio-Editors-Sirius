package org.palladiosimulator.editors.sirius.allocation.custom.wizard;

import org.eclipse.sirius.business.api.session.Session;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;

/**
 * This wizard page uses the regular resource selector page, but checks the type of the resource
 * selected by the user to be a ResourceEnvironment.
 */
class ResourceEnvironmentSelectorPage extends ResourceSelectorPage {


    /**
     * Creates the resource environment selector page.
     * 
     * @param selectedResourceEnvironment
     *            the resource environment
     */
    protected ResourceEnvironmentSelectorPage() {
        super("Select resource environment");
        setTitle("Diagram resource environment");
        setDescription("Select the resource environment to be used for the diagram.");
    }

    /**
     * Gets the resource environment.
     * 
     * @return the resource environment
     */
    protected ResourceEnvironment getSelectedResourceEnvironment(Session session) {
        return (ResourceEnvironment) getResult(session);
    }

    @Override
    protected boolean validatePage() {
        if (getResolvedObject() == null) {
            setErrorMessage("No resource environment selected");
            return false;
        }

        if (!(getResolvedObject() instanceof ResourceEnvironment)) {
            setErrorMessage("Wrong resource type selected");
            return false;
        }

        setErrorMessage(null);
        return super.validatePage();
    }
}