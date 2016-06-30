package org.palladiosimulator.editors.tabs;

import org.eclipse.ui.plugin.AbstractUIPlugin;

public class PCMBenchTabsActivator extends AbstractUIPlugin {
	
	public final static String PLUGIN_ID = "org.palladiosimulator.editors.tabs";
	
	private static PCMBenchTabsActivator plugin;

    public PCMBenchTabsActivator() {
       plugin = this;
    }

    public static PCMBenchTabsActivator getDefault() {
       return plugin;
    }
}
