package org.palladiosimulator.editors.sirius.services;

import org.palladiosimulator.pcm.core.entity.ComposedProvidingRequiringEntity;

public class ComposedProvidingEntityTypeService {
    public ComposedProvidingEntityTypeService() {
    }
    
    public String getTypeString(ComposedProvidingRequiringEntity entity) {
        for (int i = 0; i < entity.getClass().getInterfaces().length; i++) {
            if (entity.getClass().getSimpleName().contains(entity.getClass().getInterfaces()[i].getSimpleName())) {
                return entity.getClass().getInterfaces()[i].getSimpleName();
            }
        }
        return "";
    }
}
