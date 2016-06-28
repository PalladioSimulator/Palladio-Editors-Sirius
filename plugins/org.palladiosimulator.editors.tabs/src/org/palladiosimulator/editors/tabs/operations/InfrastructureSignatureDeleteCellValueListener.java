/**
 * 
 */
package org.palladiosimulator.editors.tabs.operations;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.palladiosimulator.editors.tabs.generic.SelectionChangedListener;

/**
 * @author groenda
 */
public class InfrastructureSignatureDeleteCellValueListener extends SelectionChangedListener implements SelectionListener {

	/** SWT control of the table displaying the infrastructure signatures. */
	private TableViewer viewer;
	
	/** Constructor */
	public InfrastructureSignatureDeleteCellValueListener(TableViewer viewer) {
		this.viewer = viewer;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetSelected(SelectionEvent e) {
		viewer.refresh();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetDefaultSelected(SelectionEvent e) {
		// The implementation is not necessary.
	}
}
