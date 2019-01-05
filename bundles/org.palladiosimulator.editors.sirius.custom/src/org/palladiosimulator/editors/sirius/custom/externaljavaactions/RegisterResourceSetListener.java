package org.palladiosimulator.editors.sirius.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.editors.sirius.custom.validation.MyResourceSetListener;

/**
 * registers a ResourceSetListener on the TransactionalEditingDomain of the
 * passed EObject. FIXME: needed only until
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=447666 is fixed.
 *
 */
public class RegisterResourceSetListener implements IExternalJavaAction {

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		EObject eObject = (EObject) parameters.get("element");
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(eObject);
		domain.addResourceSetListener(new MyResourceSetListener());
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
