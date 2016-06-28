package org.palladiosimulator.editors.dialogs;

import org.eclipse.ui.plugin.AbstractUIPlugin;

// TODO: Auto-generated Javadoc
/**
 * The Class PCMDialogPluginActivator.
 */
public class PCMDialogPluginActivator extends AbstractUIPlugin {

    /** The Constant PLUGIN_ID. */
    public static final String PLUGIN_ID = "org.palladiosimulator.editors.dialogs";

    /** The plugin. */
    private static PCMDialogPluginActivator plugin;

    /**
     * Instantiates a new pCM dialog plugin activator.
     */
    public PCMDialogPluginActivator() {
        plugin = this;
    }

    /**
     * Gets the default.
     * 
     * @return the default
     */
    public static PCMDialogPluginActivator getDefault() {
        return plugin;
    }

}
