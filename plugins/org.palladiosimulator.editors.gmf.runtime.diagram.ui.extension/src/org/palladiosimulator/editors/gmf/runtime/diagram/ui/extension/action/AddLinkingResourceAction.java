package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider;


public class AddLinkingResourceAction implements  IEditPolicyProvider  {

	/*public AddLinkingResourceAction(){
		super(PalladioComponentModelElementTypes.LinkingResource_2005);
		}
	@Override
	public boolean canExecute(Collection<? extends EObject> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void execute(Collection<? extends EObject> arg0, Map<String, Object> arg1) {
		// TODO Auto-generated method stub
		OpenLatencyDialog openLatencyDialog = new OpenLatencyDialog();
		//installEditPolicy <- openLatencyDialog ist das? 
		
		System.out.println("huhu");
	}
*/
	@Override
	public void addProviderChangeListener(IProviderChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean provides(IOperation operation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeProviderChangeListener(IProviderChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createEditPolicies(EditPart editPart) {
		// TODO Auto-generated method stub
		
	}

}
