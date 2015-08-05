package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.commands.AddLatencyAndThroughputRecordingCommand;
import org.palladiosimulator.pcm.resourceenvironment.CommunicationLinkResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentFactory;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentPackage;


public class AddLinkingResourceAction implements  IExternalJavaAction  {

	public AddLinkingResourceAction(){
		super();
	}

	
	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		for (EObject object : selections) {
			if( object instanceof LinkingResource || object instanceof ResourceEnvironment)
				return true;
		}
		return false;
	}
	
	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
				
		
		final Object lparameter = parameters.get("newLinkingResource");
		final Object cparameter = parameters.get("newCommunicationLinkResourceSpecification");
	        if (lparameter == null || !(lparameter instanceof LinkingResource ) || !(cparameter instanceof CommunicationLinkResourceSpecification)) {
	        	return;
	        }
	        
	    	      
		final TransactionalEditingDomain domain = SessionManager.INSTANCE.getSession((EObject)cparameter).getTransactionalEditingDomain();
    	
    	
    	
    	AddLatencyAndThroughputRecordingCommand acommand = new AddLatencyAndThroughputRecordingCommand(domain,(CommunicationLinkResourceSpecification) cparameter);
		
		domain.getCommandStack().execute(acommand);
    	


         	
		
	}



	

}
