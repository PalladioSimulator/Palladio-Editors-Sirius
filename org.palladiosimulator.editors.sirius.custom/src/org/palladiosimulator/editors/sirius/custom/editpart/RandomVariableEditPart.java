package org.palladiosimulator.editors.sirius.custom.editpart;

import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.swt.SWT;
import org.palladiosimulator.commons.stoex.ui.internal.StoexActivator;
import org.yakindu.base.xtext.utils.gmf.directedit.IXtextAwareEditPart;
import org.yakindu.base.xtext.utils.gmf.directedit.XtextDirectEditManager;
import com.google.inject.Injector;

@SuppressWarnings("restriction")
public class RandomVariableEditPart extends DNodeListElementEditPart implements IXtextAwareEditPart {

	public RandomVariableEditPart(View view) {
		super(view);
	}

	protected int getEditorStyles() {
		return SWT.SINGLE | SWT.WRAP;
	}
	
	@Override
	protected boolean isDirectEditEnabled() {
		return true;
	}

	@Override
	public void performRequest(Request req) {
		if (req.getType() == RequestConstants.REQ_DIRECT_EDIT) {
			final DirectEditManager manager = createDirectEditManager();
			manager.show();
		}
	}

	protected DirectEditManager createDirectEditManager() {
		return new RandomVariableDirectEditManager(this, getInjector(), getEditorStyles());
	}

	private Injector getInjector() {
		return StoexActivator.getInstance().getInjector("org.palladiosimulator.commons.stoex.Stoex");
	}

}
