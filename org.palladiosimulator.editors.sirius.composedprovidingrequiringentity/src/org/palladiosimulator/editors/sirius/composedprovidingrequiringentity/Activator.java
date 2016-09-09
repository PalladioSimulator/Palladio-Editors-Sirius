package org.palladiosimulator.editors.sirius.composedprovidingrequiringentity;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {
	// The plug-in ID
	public static final String PLUGIN_ID = "org.palladiosimulator.editors.sirius.composedprovidingrequiringentity";

	// The shared instance
	private static Activator plugin;

	private static Set<Viewpoint> viewpoints;

	private Viewpoint viewpoint;
    private RepresentationDescription representationDescription;
    
    public static final String VIEWPOINT_NAME = "System";
    public static final String REPRESENTATION_NAME= "ComposedProvidingRequiringEntity Diagram";

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
      super.start(context);
	  plugin = this;
	  viewpoints = new HashSet<Viewpoint>();
	  viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID + "/description/composedprovidingrequiringentity.odesign")); 
	  
	  
      // Set viewpoint constants
      for (final Viewpoint v : viewpoints) {
          if (v.getName().equals(VIEWPOINT_NAME)) {
              this.viewpoint = v;
              break;
          }
      }

      // Set diagram description constants
      for (final RepresentationDescription representationDescription : this.viewpoint.getOwnedRepresentations()) {
          if (representationDescription.getName().equals(REPRESENTATION_NAME)) {                                              
              this.representationDescription = representationDescription;
              break;
          }
      }

  }

  public Viewpoint getViewpoint() {
		return viewpoint;
	}

	public RepresentationDescription getRepresentationDescription() {
		return representationDescription;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.
	 * BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		if (viewpoints != null) {
			for (final Viewpoint viewpoint : viewpoints) {
				ViewpointRegistry.getInstance().disposeFromPlugin(viewpoint);
			}
			viewpoints.clear();
			viewpoints = null;
		}
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
}
