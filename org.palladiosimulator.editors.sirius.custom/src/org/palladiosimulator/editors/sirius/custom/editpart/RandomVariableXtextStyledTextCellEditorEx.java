package org.palladiosimulator.editors.sirius.custom.editpart;

import java.awt.Toolkit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.yakindu.base.xtext.utils.gmf.viewers.XtextStyledTextCellEditorEx;

import com.google.inject.Injector;

public class RandomVariableXtextStyledTextCellEditorEx extends XtextStyledTextCellEditorEx {

	private Color errorColor;
	private Color whiteColor;

	private boolean errorDisplayed = false;

	public RandomVariableXtextStyledTextCellEditorEx(int style, Injector injector) {
		super(style, injector);
		Display display = Display.getCurrent();
		errorColor = display.getSystemColor(SWT.COLOR_RED);
	}

	// don't close the editor if the new value has errors
	@Override
	protected void focusLost() {
		if (getXtextAdapter().getXtextValidationIssues().size() > 0) {
			Toolkit.getDefaultToolkit().beep();
			text.setBackground(errorColor);
			errorDisplayed = true;

			Display display = Display.getCurrent();
			display.asyncExec(new Runnable() {

				@Override
				public void run() {
					text.forceFocus();
				}

			});

			return;
		} else {
			super.focusLost();
		}
	}

	@Override
	protected void editOccured(ModifyEvent e) {
		if (errorDisplayed) {
			text.setBackground(whiteColor);
			errorDisplayed = false;
		}
		super.editOccured(e);
	}

}
