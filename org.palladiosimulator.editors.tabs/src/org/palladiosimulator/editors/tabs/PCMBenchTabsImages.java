package org.palladiosimulator.editors.tabs;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;

/**
 * @author roman Compilation of the pictures used in
 *         org.palladiosimulator.editors.tabs-Plugin
 */
public class PCMBenchTabsImages {
	/**
	 * Names of images used to represent actions in ToolBar
	 */
	public static final String ADD_SIGN 	= "add_sign";
	public static final String DELETE_SIGN  = "delete_sign";
	
	// For the toolbar images
	public static ImageRegistry imageRegistry = new ImageRegistry();
	
	/**
	 * Note: An image registry owns all of the image objects registered with it,
	 * and automatically disposes of them the SWT Display is disposed.
	 */ 

	static {
		String iconPath = "icons/";
		
		imageRegistry.put(ADD_SIGN,
				 getImageDescriptor(iconPath + ADD_SIGN + ".gif")
		);
		
		imageRegistry.put(DELETE_SIGN,
				 getImageDescriptor(iconPath + DELETE_SIGN + ".gif")
				);
	}
	
	/**
	 *@param imageFilePath the relative to the root of the plug-in; the path must be legal
     *@return an image descriptor, or null if no image could be found
	 */
	public static ImageDescriptor getImageDescriptor(String imageFilePath) {
		return PCMBenchTabsActivator.imageDescriptorFromPlugin(PCMBenchTabsActivator.PLUGIN_ID, imageFilePath);
	}


}
