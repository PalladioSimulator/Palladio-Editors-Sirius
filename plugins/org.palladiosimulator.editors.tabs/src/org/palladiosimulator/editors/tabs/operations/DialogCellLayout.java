/**
 * 
 */
package org.palladiosimulator.editors.tabs.operations;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

/**
 * Internal class for laying out the dialog.
 */
public class DialogCellLayout extends Layout {

	/**
     * The current contents.
     */
    private Control contents;
    /**
     * The button.
     */
	private Button selButton;
	private Button delButton;
	
	
	public DialogCellLayout(Control contents, Button selectionButton, Button deleteButton) {
		this.selButton = selectionButton;
		this.delButton = deleteButton;
		this.contents = contents;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Layout#computeSize(org.eclipse.swt.widgets.Composite, int, int, boolean)
	 */
	@Override
	protected Point computeSize(Composite composite, int wHint, int hHint,
			boolean force) {
		 if (wHint != SWT.DEFAULT && hHint != SWT.DEFAULT) {
				return new Point(wHint, hHint);
			}
         Point contentsSize = contents.computeSize(SWT.DEFAULT, SWT.DEFAULT,
                 force);
         Point buttonSize = selButton.computeSize(SWT.DEFAULT, SWT.DEFAULT,
                 force);
         // Just return the button width to ensure the button is not clipped
         // if the label is long.
         // The label will just use whatever extra width there is
         Point result = new Point(buttonSize.x, Math.max(contentsSize.y,
                 buttonSize.y));
         return result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Layout#layout(org.eclipse.swt.widgets.Composite, boolean)
	 */
	@Override
	protected void layout(Composite editor, boolean force) {
		   Rectangle bounds = editor.getClientArea();
           Point selSize = selButton.computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
           Point delSize = delButton.computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
           if (contents != null) {
				contents.setBounds(bounds.x, 0, bounds.width - selSize.x - delSize.x, bounds.height);
			}
           selButton.setBounds(bounds.width - selSize.x, 0, selSize.x, bounds.height);
           delButton.setBounds(bounds.width - selSize.x - delSize.x, 0, delSize.x, bounds.height);
	}

}
