package org.palladiosimulator.editors.sirius.services.stoexxtext;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.obeonetwork.dsl.viewpoint.xtext.support.action.OpenXtextEmbeddedEditor;

import com.google.inject.Injector;
import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public abstract class OpenStoexXtextEmbeddedEditor extends OpenXtextEmbeddedEditor {

	protected void openEmbeddedEditor(IGraphicalEditPart graphicalEditPart, RandomVariable randomVariable, TypeEnum expectedType) {
		StoexXtextEmbeddedEditor embeddedEditor = new StoexXtextEmbeddedEditor(graphicalEditPart, getInjector(), expectedType);
		embeddedEditor.showEditor((EObject) randomVariable);
	}
	
	protected abstract Injector getInjector();

	
}
