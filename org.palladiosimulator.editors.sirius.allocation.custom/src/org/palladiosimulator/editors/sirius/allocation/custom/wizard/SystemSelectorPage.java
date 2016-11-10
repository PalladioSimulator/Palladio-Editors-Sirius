package org.palladiosimulator.editors.sirius.allocation.custom.wizard;

import org.eclipse.sirius.business.api.session.Session;
import org.palladiosimulator.pcm.system.System;

/**
 * This wizard page uses the regular resource selector page, but checks the type
 * of the resource selected by the user to be a System.
 */
class SystemSelectorPage extends ResourceSelectorPage {


	/**
	 * Creates the system selector page.
	 * 
	 * @param selectedSystem
	 *            the selected system
	 * @param session
	 *            current session
	 * 
	 */
	protected SystemSelectorPage() {
		super("Select system");
		setTitle("Diagram system");
		setDescription("Select the system to be used for the diagram.");
	}

	/**
	 * Gets the selected system.
	 * 
	 * @return the system
	 */
	protected System getSelectedSystem(Session session) {
		return (System) getResult(session);
	}
	

	@Override
	protected boolean validatePage() {
		if (getResolvedObject() == null) {
			setErrorMessage("No system selected");
			return false;
		}

		if (!(getResolvedObject() instanceof System)) {
			setErrorMessage("Wrong resource type selected");
			return false;
		}

		setErrorMessage(null);
		return super.validatePage();
	}
}