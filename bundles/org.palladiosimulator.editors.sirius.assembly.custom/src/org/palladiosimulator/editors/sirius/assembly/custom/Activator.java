package org.palladiosimulator.editors.sirius.assembly.custom;

import java.util.Set;

import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;

	// The shared instance
	private static Activator plugin;
	
	private Viewpoint viewpoint;
    private RepresentationDescription representationDescription;
    
    public static final String VIEWPOINT_NAME = "Assembly";
    public static final String REPRESENTATION_NAME= "Assembly Diagram";

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		Activator.plugin = this;
		
		Set<Viewpoint> viewpoints = ViewpointRegistry.getInstance().getViewpoints();
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
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
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
