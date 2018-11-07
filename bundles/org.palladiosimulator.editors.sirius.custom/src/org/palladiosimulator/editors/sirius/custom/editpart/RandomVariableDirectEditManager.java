package org.palladiosimulator.editors.sirius.custom.editpart;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.yakindu.base.xtext.utils.gmf.directedit.IXtextAwareEditPart;
import org.yakindu.base.xtext.utils.gmf.directedit.XtextDirectEditManager;
import org.yakindu.base.xtext.utils.gmf.viewers.XtextStyledTextCellEditorEx;

import com.google.inject.Injector;

import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class RandomVariableDirectEditManager extends XtextDirectEditManager {

	private Injector injector;
	private int style;
	private TypeEnum expectedType;
	private String editText = null;

	public RandomVariableDirectEditManager(IXtextAwareEditPart source, Injector injector, int style,
			TypeEnum expectedType) {
		super(source, injector, style);
		this.injector = injector;
		this.style = style;
		this.expectedType = expectedType;
	}

	public RandomVariableDirectEditManager(IXtextAwareEditPart source, Injector injector, int style,
			TypeEnum expectedType, String editText) {
		this(source, injector, style, expectedType);
		this.editText = editText;
	}

	@Override
	public void show() {
		super.show();
		if (editText != null) {
			setEditText(editText);
		}
	}

	@Override
	protected CellEditor createCellEditorOn(Composite composite) {

		Composite parent = new Composite(composite, SWT.None);
		FillLayout fillLayout = new FillLayout();
		fillLayout.marginWidth = 10;
		parent.setLayout(fillLayout);

		RandomVariableXtextStyledTextCellEditorEx editor;

		if (editText != null) {
			editor = new RandomVariableXtextStyledTextCellEditorEx(style, injector, expectedType, editText);
		} else {
			editor = new RandomVariableXtextStyledTextCellEditorEx(style, injector, expectedType);
		}
		editor.create(composite);

		return editor;
	}

}
