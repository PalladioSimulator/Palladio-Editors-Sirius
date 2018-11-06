package org.palladiosimulator.editors.sirius.custom.externaljavaactions;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.sirius.diagram.impl.DDiagramElementImpl;
import org.eclipse.sirius.diagram.ui.tools.internal.part.SiriusDiagramGraphicalViewer;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.sirius.custom.editpart.IEditTextEditPart;

/**
 * This External Java Action sends a direct edit request to the associated edit
 * part of the RandomVariable.
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
@SuppressWarnings("restriction")
public abstract class SetRandomVariable implements IExternalJavaAction {

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		EObject element = (EObject) parameters.get("element");

		Display display = Display.getCurrent();
		display.asyncExec(new Runnable() {

			@Override
			public void run() {
				List<IGraphicalEditPart> editParts = findEditParts(element);
				if (editParts.size() == 0)
					return;
				for (IGraphicalEditPart editPart : editParts) {
					EObject semanticElement = editPart.resolveSemanticElement();
					String actualMappingName = ((DDiagramElementImpl) semanticElement).getMapping().getName();

					if (editPart instanceof IEditTextEditPart && checkMapping(actualMappingName)) {
						((IEditTextEditPart) editPart).performRequest(new DirectEditRequest(), getInitialText());
					}
				}
			}
		});
	}

	/**
	 * Gets the initial text that should be displayed on the edit label
	 * 
	 * @return initial text to display
	 */
	protected abstract String getInitialText();

	/**
	 * checks if the mapping of the editPart is the one of the random variable to be
	 * set in the action. The mapping name is defined in the *.odesign files through
	 * the "id" attribute of the node.
	 * 
	 * @return true if it is the same, false otherwise
	 */
	protected abstract boolean checkMapping(String mappingName);

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

	private List<IGraphicalEditPart> findEditParts(EObject element) {
		IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		SiriusDiagramGraphicalViewer viewer = (SiriusDiagramGraphicalViewer) ((IDiagramWorkbenchPart) editor)
				.getDiagramGraphicalViewer();

		List<IGraphicalEditPart> associatedParts = viewer.findEditPartsForElement(element, IGraphicalEditPart.class);

		return associatedParts;
	}

}
