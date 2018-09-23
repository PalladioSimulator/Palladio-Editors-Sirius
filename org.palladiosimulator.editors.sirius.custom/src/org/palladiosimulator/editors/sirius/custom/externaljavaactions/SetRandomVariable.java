package org.palladiosimulator.editors.sirius.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.commons.stoex.ui.internal.StoexActivator;
import org.palladiosimulator.editors.commons.dialogs.stoex.StochasticExpressionEditDialog;
import org.palladiosimulator.editors.sirius.services.stoexxtext.OpenStoexXtextEmbeddedEditor;

import com.google.inject.Injector;

import de.uka.ipd.sdq.pcm.stochasticexpressions.PCMStoExPrettyPrintVisitor;
import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

/**
 * This External Java Action opens a StochasticExpressionEditDialog and sets the
 * RandomVariable of the object passed to getRandomVariable.
 * 
 * The RandomVariable instance should already be created.
 * 
 * This class is abstract and therefore must be extended. (See for example
 * {@link SetVariableCharacterisationSpecification})
 * 
 * The RandomVariable's container (e.g. VariableCharacterisation in
 * {@link SetVariableCharacterisationSpecification}) must be passed as parameter
 * to the External Java Action with the name "element". (See for example
 * repository.odesign > Section Internal Elements > Node Creation Variable
 * Characterisation and Double Click Edit Variable Characterisation)
 * 
 * @author Amine Kechaou
 *
 */
public abstract class SetRandomVariable extends OpenStoexXtextEmbeddedEditor {

	@Override
	protected Injector getInjector() {
		return StoexActivator.getInstance().getInjector("org.palladiosimulator.commons.stoex.Stoex");
	}

	@Override
	public void execute(Collection<? extends EObject> context, Map<String, Object> parameters) {
		RandomVariable randomVariable = getRandomVariable((EObject) parameters.get("element"));

		DiagramEditPart diagramEditPart = ((DiagramEditor) getActiveEditor()).getDiagramEditPart();
		for (EObject o : context) {
			EditPart editPart = diagramEditPart.findEditPart(diagramEditPart, o);
			if (editPart != null && (editPart instanceof IGraphicalEditPart)) {
				openEmbeddedEditor((IGraphicalEditPart) editPart, randomVariable, getExpectedType());
				break;
			}
		}
	}

	/**
	 * Gets the expected type of the RandomVariable to be set
	 * @return the expected type of the RandomVariable to be set
	 */
	public abstract TypeEnum getExpectedType();

	/**
	 * Gets the RandomVariable of the element 
	 * @param element Element containing the RandomVariable to be set
	 * @return The RandomVariable of the given element
	 */
	public abstract RandomVariable getRandomVariable(EObject element);

}
