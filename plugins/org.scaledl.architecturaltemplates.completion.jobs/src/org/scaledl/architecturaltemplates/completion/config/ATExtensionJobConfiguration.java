package org.scaledl.architecturaltemplates.completion.config;

import org.scaledl.architecturaltemplates.type.AT;

import de.uka.ipd.sdq.workflow.extension.AbstractExtensionJobConfiguration;

/**
 * Configurations specific to architectural templates.
 * 
 * @author Sebastian Lehrig
 */
public class ATExtensionJobConfiguration extends AbstractExtensionJobConfiguration {

    private AT architecturalTemplate;

    @Override
    public String getErrorMessage() {
        // must be null; otherwise a non-empty error message will result in
        // a workflow config being considered invalid
        return null;
    }

    @Override
    public void setDefaults() {
        throw new RuntimeException("Not implemented. No defaults defined.");
    }

    public void setArchitecturalTemplate(final AT architecturalTemplate) {
        this.architecturalTemplate = architecturalTemplate;
    }

    public AT getArchitecturalTemplate() {
        return this.architecturalTemplate;
    }
}
