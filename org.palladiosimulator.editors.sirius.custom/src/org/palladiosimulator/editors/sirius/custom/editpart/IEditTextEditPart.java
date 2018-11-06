package org.palladiosimulator.editors.sirius.custom.editpart;

import org.eclipse.gef.Request;

/**
 * Interface for EditParts that allow to set a custom initial text.
 *
 */
public interface IEditTextEditPart {

	/**
	 * Performs the specified request and sets the initial edit text
	 * @param req request to be sent
	 * @param editText initial text that should be shown in the editPart
	 */
	abstract public void performRequest(Request req, String editText);

}
