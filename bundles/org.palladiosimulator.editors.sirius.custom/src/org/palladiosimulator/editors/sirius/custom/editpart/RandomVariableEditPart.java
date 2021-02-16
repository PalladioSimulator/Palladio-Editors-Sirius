package org.palladiosimulator.editors.sirius.custom.editpart;

import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.swt.SWT;
import org.palladiosimulator.pcm.stoex.ui.internal.StoexActivator;
import org.yakindu.base.xtext.utils.gmf.directedit.IXtextAwareEditPart;
import com.google.inject.Injector;

import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

@SuppressWarnings("restriction")
public class RandomVariableEditPart extends DNodeListElementEditPart implements IXtextAwareEditPart {

	private TypeEnum expectedType;

	public RandomVariableEditPart(View view, TypeEnum expectedType) {
		super(view);
		this.expectedType = expectedType;
	}

	public RandomVariableEditPart(View view) {
		this(view, TypeEnum.ANY);
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
		return new RandomVariableDirectEditManager(this, getInjector(), getEditorStyles(), expectedType);
	}
	
	private Injector getInjector() {
		return StoexActivator.getInstance().getInjector(StoexActivator.ORG_PALLADIOSIMULATOR_PCM_STOEX_PCMSTOEX);
	}

	public void setExpectedType(TypeEnum expectedType) {
		this.expectedType = expectedType;
	}

}
