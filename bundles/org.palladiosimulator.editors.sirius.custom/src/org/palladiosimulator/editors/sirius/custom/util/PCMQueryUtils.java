package org.palladiosimulator.editors.sirius.custom.util;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashSet;
import java.util.Queue;

import org.palladiosimulator.pcm.repository.OperationInterface;

public final class PCMQueryUtils {

    private PCMQueryUtils() {
        // intentionally empty
    }

    public static Collection<OperationInterface> getTransitiveParentInterfaceClosureIncludingSelf(
            OperationInterface self) {
        Collection<OperationInterface> closure = new HashSet<>();
        Queue<OperationInterface> queue = new ArrayDeque<>();
        queue.add(self);
        while (!queue.isEmpty()) {
            OperationInterface current = queue.poll();
            closure.add(current);
            current.getParentInterfaces__Interface()
                .stream()
                .filter(OperationInterface.class::isInstance)
                .map(OperationInterface.class::cast)
                .filter(i -> !closure.contains(i))
                .forEach(queue::add);
        }
        return closure;
    }

}
