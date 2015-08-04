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


	private static final String DIALOG_MESSAGE = "Add a Linking Resource";
	private static final String RESOURCE_COMMUNICATION_LINK_RESOURCE_SPECIFICATION = "org.palladiosimulator.pcm.resourceenvironment.CommunicationLinkResourceSpecification";

	public AddLinkingResourceAction(){
		super();
	}

	
	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		for (EObject object : selections) {
			if( object instanceof ResourceEnvironment)
				return true;
		}
		return false;
	}
	
	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		java.lang.System.out.println("actual execute befehl");
		
		final ResourceEnvironment resourceEnvironment = (ResourceEnvironment) selections.iterator().next();
      
		
		/* IElementType elementType = ElementTypeRegistry.getInstance().getType( RESOURCE_COMMUNICATION_LINK_RESOURCE_SPECIFICATION);
        

        CreateElementRequest createElementRequest = new CreateElementRequest(resourceEnvironment,elementType);
        createElementRequest.setContainmentFeature(ResourceenvironmentPackage.eINSTANCE
                .getLinkingResource_CommunicationLinkResourceSpecifications_LinkingResource());
        CreateElementCommand createElementCommand = new CreateElementCommand(createElementRequest);

       

        CompositeCommand compositeCommand = new CompositeCommand("");
        compositeCommand.add(createElementCommand);
        //compositeCommand.add(new SetLatencyThroughputAndLanTypeCommand(request));
*/
		
		final Object parameter = parameters.get("newLinkingResource");
	        if (parameter == null || !(parameter instanceof CommunicationLinkResourceSpecification)) {
	        	return;
	        }
			//final EditingDomain edomain = SessionManager.INSTANCE.getSession((EObject) parameter).getTransactionalEditingDomain();
			//new org.eclipse.emf.edit.ui.action.LoadResourceAction(edomain).run();
    	final TransactionalEditingDomain domain = SessionManager.INSTANCE.getSession((EObject)parameter).getTransactionalEditingDomain();
    	
    	
    	
    	//CommunicationLinkRes mit Factory erstellen
    	//CommunicationLinkResourceSpecification communicationLinkingResource = ResourceenvironmentFactory.eINSTANCE.createCommunicationLinkResourceSpecification();
    	
    	//communicationLinkingResource befüllen...
    	//TODO
    	
    	//oder von sirius erstellte LR nutzen ?
    	//CommunicationLinkResourceSpecification communicationLinkingResource = (CommunicationLinkResourceSpecification) parameters.get("newLinkingResource");
		
    	AddLatencyAndThroughputRecordingCommand acommand = new AddLatencyAndThroughputRecordingCommand(domain,(CommunicationLinkResourceSpecification) parameter);
		
		domain.getCommandStack().execute(acommand);
    	


         	
		
	}



	

}
