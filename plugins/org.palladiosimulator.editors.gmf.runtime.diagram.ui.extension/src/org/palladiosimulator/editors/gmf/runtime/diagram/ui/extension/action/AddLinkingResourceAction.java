package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.modelversioning.emfprofile.Stereotype;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentFactory;
import org.palladiosimulator.pcm.system.System;
import org.scaledl.architecturaltemplates.api.ArchitecturalTemplateAPI;
import org.scaledl.architecturaltemplates.type.AT;
import org.scaledl.architecturaltemplates.ui.dialogs.ArchitecturalTemplateSelectionDialog;
import org.scaledl.architecturaltemplates.ui.dialogs.RoleStereotypeSelectionDialog;


public class AddLinkingResourceAction extends EditElementCommand implements  IExternalJavaAction  {


	private static final String DIALOG_MESSAGE = "Add a Linking Resource";

	
	public AddLinkingResourceAction(CreateElementRequest req){
		super(req.getLabel(),null,req);
	}
	
	public boolean getElementToEdit(Collection<? extends EObject> selections){
	//	EObject container = ((CreateElementRequest) getRequest()).getContainer();
		for (EObject object : selections) {
			return object instanceof LinkingResource;
		}
		return true;
	}
	
	
	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		/*if (selections.size() != 1) {
			return false;
		}
		for (EObject object : selections) {
			return object instanceof LinkingResource;
		}*/
		
		return true;
	}


	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		LinkingResource linkingResource = ResourceenvironmentFactory.eINSTANCE.createLinkingResource();

		ResourceEnvironment owner = (ResourceEnvironment) getElementToEdit();
		owner.getLinkingResources__ResourceEnvironment().add(linkingResource);
		
		doConfigure(linkingResource, monitor, info);
		return CommandResult.newOKCommandResult(linkingResource);
	}
	
	
	protected void doConfigure(LinkingResource linkingResource, IProgressMonitor monitor, IAdaptable info)
            throws ExecutionException {
        IElementType elementType = ((CreateElementRequest) getRequest()).getElementType();
        ConfigureRequest configureRequest = new ConfigureRequest(getEditingDomain(), linkingResource, elementType);
        configureRequest.setClientContext(((CreateElementRequest) getRequest()).getClientContext());
        configureRequest.addParameters(getRequest().getParameters());
        ICommand configureCommand = elementType.getEditCommand(configureRequest);
        if (configureCommand != null && configureCommand.canExecute()) {
            configureCommand.execute(monitor, info);
        }
    }
	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameter) {
		//final LinkingResource linkingResource = (LinkingResource) selections.iterator().next();
		//LinkingResource linkingResource = ResourceenvironmentFactory.eINSTANCE.createLinkingResource();

		//ResourceEnvironment owner = (ResourceEnvironment) getElementToEdit()
	  
		/*ArchitecturalTemplateSelectionDialog profileSelectionDialog = new ArchitecturalTemplateSelectionDialog(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());

		profileSelectionDialog.setElements((AT[]) ArchitecturalTemplateAPI.getRegisteredArchitecturalTemplates()
				.toArray(new AT[ArchitecturalTemplateAPI.getRegisteredArchitecturalTemplates().size()]));
		profileSelectionDialog.setMessage(DIALOG_MESSAGE);

		if (profileSelectionDialog.open() != Dialog.OK) {
			return;
		}

		final AT at = profileSelectionDialog.getResultArchitecturalTemplate();

		//ArchitecturalTemplateAPI.applyArchitecturalTemplate(linkingResource, at);*/
		
	}



	

}
