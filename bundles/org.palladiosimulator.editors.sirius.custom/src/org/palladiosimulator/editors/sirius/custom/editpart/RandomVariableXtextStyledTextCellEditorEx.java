package org.palladiosimulator.editors.sirius.custom.editpart;

import java.awt.Toolkit;
import java.util.Optional;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.yakindu.base.xtext.utils.gmf.viewers.XtextStyledTextCellEditorEx;
import org.yakindu.base.xtext.utils.jface.viewers.StyledTextXtextAdapter;
import org.yakindu.base.xtext.utils.jface.viewers.context.IXtextFakeContextResourcesProvider;

import com.google.inject.Injector;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class RandomVariableXtextStyledTextCellEditorEx extends XtextStyledTextCellEditorEx {

	private final Color errorColor;
	private Color whiteColor;

	private final Optional<RandomVariable> randomVariable;
	private final TypeEnum expectedType;

	private boolean errorDisplayed = false;

	public RandomVariableXtextStyledTextCellEditorEx(int style, Injector injector, Optional<RandomVariable> randomVariable, TypeEnum expectedType) {
		super(style, injector);
		this.expectedType = expectedType;
		this.randomVariable = randomVariable;
		Display display = Display.getCurrent();
		errorColor = display.getSystemColor(SWT.COLOR_RED);
	}

	@Override
	protected void focusLost() {
		// don't close the editor if the new value has errors
		if (getXtextAdapter().getXtextValidationIssues().size() > 0) {
			displayError();
		} else {
			super.focusLost();
		}
	}

	private void displayError() {
		Toolkit.getDefaultToolkit().beep();
		text.setBackground(errorColor);
		errorDisplayed = true;

		Display display = Display.getCurrent();
		display.asyncExec(new Runnable() {

			@Override
			public void run() {
				if (!text.isDisposed())
					text.forceFocus();
			}

		});
	}

	@Override
	protected void editOccured(ModifyEvent e) {
		if (errorDisplayed) {
			text.setBackground(whiteColor);
			errorDisplayed = false;
		}
		super.editOccured(e);
	}

	@Override
	protected Control createControl(Composite parent) {
		StyledText text = (StyledText) super.createControl(parent);
		text.addTraverseListener(new TraverseListener() {
			@Override
			public void keyTraversed(TraverseEvent e) {
				if (e.character == SWT.TAB) {
					e.doit = false;
				}
			}
		});
		return text;
	}

	@Override
	protected StyledTextXtextAdapter createXtextAdapter() {
		return new RandomVariableXtextAdapter(this.getInjector(),
				getContextFakeResourceProvider() == null ? IXtextFakeContextResourcesProvider.NULL_CONTEXT_PROVIDER
						: getContextFakeResourceProvider(),
						randomVariable, expectedType);
	}

}
