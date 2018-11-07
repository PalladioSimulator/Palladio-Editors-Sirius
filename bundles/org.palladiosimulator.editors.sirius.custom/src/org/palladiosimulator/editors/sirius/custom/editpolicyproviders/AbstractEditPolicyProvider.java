package org.palladiosimulator.editors.sirius.custom.editpolicyproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.common.core.service.ProviderChangeEvent;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider;

public abstract class AbstractEditPolicyProvider implements IEditPolicyProvider {

    /** All listeners. */
    private final List<IProviderChangeListener> listeners = new ArrayList<IProviderChangeListener>(1);

    @Override
    public void addProviderChangeListener(final IProviderChangeListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void removeProviderChangeListener(final IProviderChangeListener listener) {
        this.listeners.remove(listener);
    }

    protected void fireProviderChanged() {
        final ProviderChangeEvent event = new ProviderChangeEvent(this);
        final Iterator<IProviderChangeListener> iterListener = this.listeners.iterator();
        while (iterListener.hasNext()) {
            final IProviderChangeListener listener = iterListener.next();
            listener.providerChanged(event);
        }
    }

}
