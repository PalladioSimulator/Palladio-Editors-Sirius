package org.palladiosimulator.editors.sirius.custom.validation;

import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.gmf.runtime.common.ui.util.WorkbenchPartDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * Triggers a custom validation that shows errors for semantic elements that do
 * not have a diagram element representation
 * FIXME: needed only until https://bugs.eclipse.org/bugs/show_bug.cgi?id=447666 is fixed.
 */
public class MyResourceSetListener extends ResourceSetListenerImpl {

	@Override
	public void resourceSetChanged(ResourceSetChangeEvent event) {
		Display display = Display.getCurrent();
		display.asyncExec(new Runnable() {

			@Override
			public void run() {
				IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
						.getActiveEditor();

				WorkbenchPartDescriptor workbenchPartDescriptor = new WorkbenchPartDescriptor(
						editorPart.getSite().getId(), editorPart.getClass(), editorPart.getSite().getPage());

				SemanticElementsValidateAction va = new SemanticElementsValidateAction(workbenchPartDescriptor);
				va.run();
			}
		});
	}

}
