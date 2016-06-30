/**
 */
package org.palladiosimulator.pcmmeasuringpoint.provider;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.palladiosimulator.edp2.models.ExperimentData.provider.EDP2EditPlugin;
import org.palladiosimulator.metricspec.provider.MetricSpecEditPlugin;
import org.palladiosimulator.pcm.core.provider.PalladioComponentModelEditPlugin;

import de.uka.ipd.sdq.identifier.provider.IdentifierEditPlugin;
import de.uka.ipd.sdq.probfunction.provider.ProbabilityFunctionEditPlugin;
import de.uka.ipd.sdq.stoex.provider.StoexEditPlugin;
import de.uka.ipd.sdq.units.provider.UnitsEditPlugin;

/**
 * This is the central singleton for the Pcmmeasuringpoint edit plugin. <!-- begin-user-doc --> <!--
 * end-user-doc -->
 * 
 * @generated
 */
public final class PcmmeasuringpointEditPlugin extends EMFPlugin {

    /**
     * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final PcmmeasuringpointEditPlugin INSTANCE = new PcmmeasuringpointEditPlugin();

    /**
     * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static Implementation plugin;

    /**
     * Create the instance. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PcmmeasuringpointEditPlugin() {
        super(new ResourceLocator[] { EDP2EditPlugin.INSTANCE, IdentifierEditPlugin.INSTANCE,
                MetricSpecEditPlugin.INSTANCE, PalladioComponentModelEditPlugin.INSTANCE,
                ProbabilityFunctionEditPlugin.INSTANCE, StoexEditPlugin.INSTANCE, UnitsEditPlugin.INSTANCE, });
    }

    /**
     * Returns the singleton instance of the Eclipse plugin. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the singleton instance.
     * @generated
     */
    @Override
    public ResourceLocator getPluginResourceLocator() {
        return plugin;
    }

    /**
     * Returns the singleton instance of the Eclipse plugin. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the singleton instance.
     * @generated
     */
    public static Implementation getPlugin() {
        return plugin;
    }

    /**
     * The actual implementation of the Eclipse <b>Plugin</b>. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static class Implementation extends EclipsePlugin {

        /**
         * Creates an instance. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        public Implementation() {
            super();

            // Remember the static instance.
            //
            plugin = this;
        }
    }

}
