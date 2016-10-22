package org.palladiosimulator.editors.sirius.composedprovidingrequiringentity.custom.externaljavaactions;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.core.composition.AssemblyConnector;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.AssemblyEventConnector;
import org.palladiosimulator.pcm.core.composition.AssemblyInfrastructureConnector;
import org.palladiosimulator.pcm.core.composition.Connector;
import org.palladiosimulator.pcm.core.composition.EventChannelSinkConnector;
import org.palladiosimulator.pcm.core.composition.EventChannelSourceConnector;
import org.palladiosimulator.pcm.core.composition.ProvidedDelegationConnector;
import org.palladiosimulator.pcm.core.composition.ProvidedInfrastructureDelegationConnector;
import org.palladiosimulator.pcm.core.composition.RequiredDelegationConnector;
import org.palladiosimulator.pcm.core.composition.RequiredInfrastructureDelegationConnector;
import org.palladiosimulator.pcm.core.composition.SinkDelegationConnector;
import org.palladiosimulator.pcm.core.composition.SourceDelegationConnector;

public class DeleteAsemblyContext implements IExternalJavaAction {

    @Override
    public boolean canExecute(Collection<? extends EObject> arg0) {
        return true;
    }

    @Override
    public void execute(Collection<? extends EObject> selection, Map<String, Object> parameters) {
        AssemblyContext element = (AssemblyContext) parameters.get("element");
        
        Iterator<Connector> iter = element.getParentStructure__AssemblyContext().getConnectors__ComposedStructure().iterator();
        Connector connector;
            while (iter.hasNext()) {
                connector = iter.next();
                // This structure is not nice code but to clean it up an architecture change is
                // necessary
                if (connector instanceof AssemblyInfrastructureConnector) {
                    AssemblyInfrastructureConnector tmpConnector = (AssemblyInfrastructureConnector) connector;

                    // Remove if element is part of connector
                    if (tmpConnector.getProvidingAssemblyContext__AssemblyInfrastructureConnector().equals(element)
                            || tmpConnector.getRequiringAssemblyContext__AssemblyInfrastructureConnector()
                                    .equals(element)) {
                        iter.remove();
                    }

                } else if (connector instanceof AssemblyConnector) {

                    AssemblyConnector tmpConnector = (AssemblyConnector) connector;

                    // Remove if element is part of connector
                    if (tmpConnector.getProvidingAssemblyContext_AssemblyConnector().equals(element)
                            || tmpConnector.getRequiringAssemblyContext_AssemblyConnector()
                                    .equals(element)) {
                        iter.remove();
                    }

                } else if (connector instanceof AssemblyEventConnector) {

                    AssemblyEventConnector tmpConnector = (AssemblyEventConnector) connector;

                    // Remove if element is part of connector
                    if (tmpConnector.getSinkAssemblyContext__AssemblyEventConnector().equals(element)
                            || tmpConnector.getSourceAssemblyContext__AssemblyEventConnector().equals(element)) {
                        iter.remove();
                    }

                } else if (connector instanceof EventChannelSourceConnector) {

                    EventChannelSourceConnector tmpConnector = (EventChannelSourceConnector) connector;

                    // Remove if element is part of connector
                    if (tmpConnector.getAssemblyContext__EventChannelSourceConnector().equals(element)) {
                        iter.remove();
                    }

                } else if (connector instanceof EventChannelSinkConnector) {

                    EventChannelSinkConnector tmpConnector = (EventChannelSinkConnector) connector;

                    // Remove if element is part of connector
                    if (tmpConnector.getAssemblyContext__EventChannelSinkConnector().equals(element)) {
                        iter.remove();
                    }

                } else if (connector instanceof SinkDelegationConnector) {

                    SinkDelegationConnector tmpConnector = (SinkDelegationConnector) connector;

                    // Remove if element is part of connector
                    if (tmpConnector.getAssemblyContext__SinkDelegationConnector().equals(element)) {
                        iter.remove();
                    }

                } else if (connector instanceof SourceDelegationConnector) {

                    SourceDelegationConnector tmpConnector = (SourceDelegationConnector) connector;

                    // Remove if element is part of connector
                    if (tmpConnector.getAssemblyContext__SourceDelegationConnector().equals(element)) {
                        iter.remove();
                    }

                } else if (connector instanceof ProvidedDelegationConnector) {

                    ProvidedDelegationConnector tmpConnector = (ProvidedDelegationConnector) connector;

                    // Remove if element is part of connector
                    if (tmpConnector.getAssemblyContext_ProvidedDelegationConnector().equals(element)) {
                        iter.remove();
                    }

                } else if (connector instanceof ProvidedInfrastructureDelegationConnector) {

                    ProvidedInfrastructureDelegationConnector tmpConnector = (ProvidedInfrastructureDelegationConnector) connector;

                    // Remove if element is part of connector
                    if (tmpConnector.getAssemblyContext__ProvidedInfrastructureDelegationConnector().equals(element)) {
                        iter.remove();
                    }

                } else if (connector instanceof RequiredInfrastructureDelegationConnector) {

                    RequiredInfrastructureDelegationConnector tmpConnector = (RequiredInfrastructureDelegationConnector) connector;

                    // Remove if element is part of connector
                    if (tmpConnector.getAssemblyContext__RequiredInfrastructureDelegationConnector().equals(element)) {
                        iter.remove();
                    }

                } else if (connector instanceof RequiredDelegationConnector) {

                    RequiredDelegationConnector tmpConnector = (RequiredDelegationConnector) connector;

                    // Remove if element is part of connector
                    if (tmpConnector.getAssemblyContext_RequiredDelegationConnector().equals(element)) {
                        iter.remove();
                    }

                }
            }
        // TODO DelegationConnector
    }

}
