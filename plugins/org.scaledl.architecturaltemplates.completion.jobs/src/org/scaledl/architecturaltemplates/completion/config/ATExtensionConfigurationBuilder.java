package org.scaledl.architecturaltemplates.completion.config;

import java.util.Map;

import de.uka.ipd.sdq.workflow.extension.AbstractExtensionJobConfiguration;
import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionConfigurationBuilder;

/**
 * This class provides functionality to configure Architectural Template-enabled runs.
 * 
 * @author Sebastian Lehrig
 */
public class ATExtensionConfigurationBuilder extends AbstractWorkflowExtensionConfigurationBuilder {

    @Override
    public final AbstractExtensionJobConfiguration buildConfiguration(final Map<String, Object> attributes) {
        final ATExtensionJobConfiguration configuration = new ATExtensionJobConfiguration();
        /*
         * if (attributes.containsKey(ATExtensionTab.SIMULATE_FAILURES)) { Boolean simulateFailures
         * = (Boolean) attributes.get(ATExtensionTab.SIMULATE_FAILURES);
         * configuration.setArchitecturalTemplate(null); }
         */
        return configuration;
    }

}
