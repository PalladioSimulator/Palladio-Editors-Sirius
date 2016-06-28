/**
 * 
 */
package org.palladiosimulator.editors.tabs.generic;

import java.util.Observable;

import org.eclipse.jface.viewers.ICellModifier;

/**
 * @author Roman Andrej
 */
public abstract class ObservableCellModifier extends Observable implements
		ICellModifier {

	/* (non-Javadoc)
	 * @see java.util.Observable#notifyObservers()
	 */
	@Override
	public void notifyObservers() {
		setChanged();
		super.notifyObservers();
	}

	/* (non-Javadoc)
	 * @see java.util.Observable#notifyObservers(java.lang.Object)
	 */
	@Override
	public void notifyObservers(Object arg) {
		setChanged();
		super.notifyObservers(arg);
	}
	
	
}
