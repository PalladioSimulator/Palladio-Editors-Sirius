package org.palladiosimulator.editors.sirius.resourceenvironment;

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
    public static final String PLUGIN_ID = "org.palladiosimulator.editors.sirius.resourceenvironment";

    // The shared instance
    private static Activator plugin;

    private static Set<Viewpoint> viewpoints;

    private Viewpoint viewpoint;
    private RepresentationDescription representation;
    
    public static final String VIEWPOINT_NAME = "ResourceEnvironment";
    public static final String REPRESENTATION_NAME= "ResourceEnvironment Diagram";

    /**
     * The constructor
     */
    public Activator() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        viewpoints = new HashSet<Viewpoint>();
        viewpoints.addAll(ViewpointRegistry.getInstance()
                .registerFromPlugin(PLUGIN_ID + "/description/resourceenvironment.odesign"));


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
                this.representation = representationDescription;
                break;
            }
        }

    }

    public Viewpoint getViewpoint() {
  		return viewpoint;
  	}

  	public RepresentationDescription getRepresentation() {
  		return representation;
  	}

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
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
