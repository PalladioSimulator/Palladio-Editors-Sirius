package org.palladiosimulator.editors.sirius.services;

import org.palladiosimulator.architecturaltemplates.Completion;
import org.palladiosimulator.pcm.core.entity.ComposedProvidingRequiringEntity;
import org.palladiosimulator.pcm.repository.CompositeComponent;
import org.palladiosimulator.pcm.subsystem.SubSystem;
import org.palladiosimulator.pcm.system.System;

public class ComposedProvidingEntityTypeService {
    public ComposedProvidingEntityTypeService() {
    }
    
    public String getTypeString(ComposedProvidingRequiringEntity entity) {
        if (entity instanceof System) {
            return "System";
        } else if (entity instanceof SubSystem) {
            return "SubSystem";
        } else if (entity instanceof CompositeComponent) {
            return "CompositeComponent";
        } else if (entity instanceof Completion) {
            return "Completion";
        } else {
            return "ComposedProvidingRequiringEntity";
        }
    }
}
