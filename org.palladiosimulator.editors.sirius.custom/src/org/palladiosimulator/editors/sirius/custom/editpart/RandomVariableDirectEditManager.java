package org.palladiosimulator.editors.sirius.custom.editpart;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.yakindu.base.xtext.utils.gmf.directedit.IXtextAwareEditPart;
import org.yakindu.base.xtext.utils.gmf.directedit.XtextDirectEditManager;
import org.yakindu.base.xtext.utils.gmf.viewers.XtextStyledTextCellEditorEx;

import com.google.inject.Injector;

public class RandomVariableDirectEditManager extends XtextDirectEditManager {
	
	private Injector injector;
	private int style;

	public RandomVariableDirectEditManager(IXtextAwareEditPart source, Injector injector, int style) {
		super(source, injector, style);
		this.injector = injector;
		this.style = style;
	}

	@Override
	protected CellEditor createCellEditorOn(Composite composite) {

		Composite parent = new Composite(composite, SWT.None);
		FillLayout fillLayout = new FillLayout();
		fillLayout.marginWidth = 10;
		parent.setLayout(fillLayout);

		RandomVariableXtextStyledTextCellEditorEx editor;

		editor = new RandomVariableXtextStyledTextCellEditorEx(style, injector);
		editor.create(composite);

		return editor;
	}

	
	
}
