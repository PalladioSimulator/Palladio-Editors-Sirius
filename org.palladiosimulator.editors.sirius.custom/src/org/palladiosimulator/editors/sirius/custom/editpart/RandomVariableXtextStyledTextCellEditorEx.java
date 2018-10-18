package org.palladiosimulator.editors.sirius.custom.editpart;

import java.awt.Toolkit;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.yakindu.base.xtext.utils.gmf.viewers.XtextStyledTextCellEditorEx;

import com.google.inject.Injector;

import de.uka.ipd.sdq.errorhandling.IIssue;
import de.uka.ipd.sdq.stoex.Expression;
import de.uka.ipd.sdq.stoex.analyser.exceptions.ExpectedTypeMismatchIssue;
import de.uka.ipd.sdq.stoex.analyser.visitors.ExpressionInferTypeVisitor;
import de.uka.ipd.sdq.stoex.analyser.visitors.NonProbabilisticExpressionInferTypeVisitor;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeCheckVisitor;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class RandomVariableXtextStyledTextCellEditorEx extends XtextStyledTextCellEditorEx {

	private Color errorColor;
	private Color whiteColor;
	
	private TypeEnum expectedType;

	private boolean errorDisplayed = false;

	public RandomVariableXtextStyledTextCellEditorEx(int style, Injector injector, TypeEnum expectedType) {
		super(style, injector);
		this.expectedType = expectedType;
		Display display = Display.getCurrent();
		errorColor = display.getSystemColor(SWT.COLOR_RED);
	}

	// don't close the editor if the new value has errors
	@Override
	protected void focusLost() {
 		final NonProbabilisticExpressionInferTypeVisitor typeVisitor = new NonProbabilisticExpressionInferTypeVisitor(); 
 		EObject resultType = getXtextAdapter().getXtextParseResult().getRootASTElement(); 
 		typeVisitor.doSwitch(resultType);
		if (getXtextAdapter().getXtextValidationIssues().size() > 0 
				|| assertType(resultType, typeVisitor, expectedType).size() > 0) {
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
	
	/**
     * Assert type.
     * 
     * @param result
     *            the result
     * @param typeVisitor
     *            the type visitor
     * @param expectedType
     *            the expected type
     * @return the collection<? extends i issue>
     */
    private Collection<? extends IIssue> assertType(final EObject result, final ExpressionInferTypeVisitor typeVisitor,
            final TypeEnum expectedType) {
        if (!TypeCheckVisitor.typesCompatible(expectedType, typeVisitor.getType((Expression) result))) {
            return Collections.singletonList(
                    new ExpectedTypeMismatchIssue(expectedType, typeVisitor.getType((Expression) result)));
        }
        return Collections.emptyList();
    }

}
