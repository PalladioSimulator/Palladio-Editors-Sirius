package org.palladiosimulator.editors.sirius.custom.editpart;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.swt.SWT;
import org.palladiosimulator.pcm.stoex.ui.internal.StoexActivator;
import org.yakindu.base.xtext.utils.gmf.directedit.IXtextAwareEditPart;

import com.google.inject.Injector;

import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

@SuppressWarnings("restriction")
public class RandomVariableNodeContainerEditPart extends DNodeContainer2EditPart implements IXtextAwareEditPart {

	private TypeEnum expectedType;

	public RandomVariableNodeContainerEditPart(View view, TypeEnum expectedType) {
		super(view);
		this.expectedType = expectedType;
	}
	
	public RandomVariableNodeContainerEditPart(View view) {
		this(view, TypeEnum.ANY);
	}

	protected int getEditorStyles() {
		return SWT.SINGLE | SWT.WRAP;
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

	@Override
	public String getEditText() {
		//not needed if a directEditLabel was created in the odesign-file 
		EObject semanticElement = resolveSemanticElement();
		return (String) semanticElement.eGet(semanticElement.eClass().getEStructuralFeature("name"));
	}

	@Override
	public void setLabelText(String text) {
		//TODO auto generated.
	}
}
