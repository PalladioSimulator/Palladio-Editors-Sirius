package org.palladiosimulator.editors.sirius.allocation.custom.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.palladiosimulator.editors.sirius.allocation.custom.Activator;
import org.palladiosimulator.editors.sirius.ui.wizard.model.ModelCreationPage;
import org.palladiosimulator.editors.sirius.ui.wizard.model.NewModelWizard;
import org.palladiosimulator.pcm.allocation.Allocation;
import org.palladiosimulator.pcm.allocation.AllocationFactory;

public class AllocationCreationWizard extends NewModelWizard {

	private ResourceEnvironmentSelectorPage resourceEnvironmentSelectorpage;
	private SystemSelectorPage systemSelectorPage;
	
	@Override
	protected void init(IStructuredSelection selection) {
		String viewpointName = Activator.VIEWPOINT_NAME;
		viewpoint = Activator.getDefault().getViewpoint();
		String ext = Activator.getDefault().getViewpoint().getModelFileExtension();
		modelCreationPage = new ModelCreationPage(selection, viewpointName + " Creation Wizard", "new" + viewpointName,  ext);
		representationDescription = Activator.getDefault().getRepresentationDescription();

		resourceEnvironmentSelectorpage = new ResourceEnvironmentSelectorPage();
		systemSelectorPage = new SystemSelectorPage();

		additionalPages.add(resourceEnvironmentSelectorpage);
		additionalPages.add(systemSelectorPage);

		Allocation obj = AllocationFactory.eINSTANCE.createAllocation();
		obj.setEntityName("New Allocation");
		
		modelObject = obj;
		
	}
	
	@Override
	protected void finish() {
		Allocation allocation = (Allocation) modelObject;
		Session session = SessionManager.INSTANCE.getSession(modelObject);
		TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
		String fileString = modelURI.toPlatformString(true);
		IPath path = new Path(fileString);
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
		List<IFile> list = new ArrayList<IFile>();
		list.add(file);
		AbstractTransactionalCommand command = new AbstractTransactionalCommand(domain, "Save Allocation model.",  list) {
			
			@Override
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				allocation.setTargetResourceEnvironment_Allocation(resourceEnvironmentSelectorpage.getSelectedResourceEnvironment(session));
				allocation.setSystem_Allocation(systemSelectorPage.getSelectedSystem(session));
				
				return CommandResult.newOKCommandResult();
			}
		};
		
		try {
			OperationHistoryFactory.getOperationHistory().execute(command, new NullProgressMonitor(), null);
		} catch (ExecutionException e) {
			System.out.println("Unable to save allocation model.");
			e.printStackTrace();
		}
		
		
		
	}

}
