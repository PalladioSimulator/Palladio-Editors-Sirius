package org.palladiosimulator.editors.usage.externaljavaactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.registry.IProfileRegistry;
import org.palladiosimulator.editors.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.RepositoryPackage;
import org.palladiosimulator.pcm.usagemodel.EntryLevelSystemCall;
import org.palladiosimulator.pcm.usagemodel.ScenarioBehaviour;
import org.palladiosimulator.pcm.usagemodel.UsageModel;
import org.palladiosimulator.pcm.usagemodel.UsagemodelFactory;

/**
 * This dialog doesn't work as intended and should be rewritten!
 * 
 * @author Michael
 *
 */
public class EntryLevelSystemCallCreationDialog implements IExternalJavaAction {

	@Override
	public boolean canExecute(Collection<? extends EObject> arg0) {
		return true;
	}

	private UsageModel getModel(ScenarioBehaviour beh) {
		EObject element = beh;
		while (!(element instanceof UsageModel)) {
			element = element.eContainer();
		}
		return (UsageModel) element;
	}

	@Override
	public void execute(Collection<? extends EObject> arg0, Map<String, Object> arg1) {
		Object element = (Object) arg0.iterator().next();
		ScenarioBehaviour beh = (ScenarioBehaviour) element;

		EntryLevelSystemCall newElement = UsagemodelFactory.eINSTANCE.createEntryLevelSystemCall();
		beh.getActions_ScenarioBehaviour().add(newElement);

		UsageModel usage = getModel(beh);
		Collection<Profile> profiles = IProfileRegistry.eINSTANCE.getRegisteredProfiles();

		AdapterFactoryEditingDomain domain = new AdapterFactoryEditingDomain(new AdapterFactoryImpl(),
				new BasicCommandStack());

		System.out.println(newElement.eResource().getResourceSet().getResources());
		Resource r = newElement.eResource().getResourceSet()
				.getResource(URI.createURI("platform:/resource/MediaStoreExample/PrioritizingMediaStore.system"), true);
		OperationSignature signature = null;
		OperationProvidedRole providedRole = null;
		ArrayList<Object> filterList = new ArrayList<Object>();
		filterList.add(System.class);
		filterList.add(OperationProvidedRole.class);

		/* first dialog for selection of the operation provided role */
		ArrayList<EReference> additionalReferences = new ArrayList<EReference>();
		additionalReferences
				.add(RepositoryPackage.eINSTANCE.getOperationProvidedRole_ProvidedInterface__OperationProvidedRole());
		additionalReferences.add(RepositoryPackage.eINSTANCE.getInterface_ParentInterfaces__Interface());

		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), filterList, additionalReferences,
				domain.getResourceSet());
		dialog.setProvidedService(OperationProvidedRole.class);
		dialog.open();
		if (dialog.getResult() == null) {
			return;
		}
		if (!(dialog.getResult() instanceof OperationProvidedRole)) {
			return;
		}

		providedRole = (OperationProvidedRole) dialog.getResult();

		/* second dialog for selection of the signature of the interface */
		filterList.clear();
		filterList.add(OperationProvidedRole.class);
		filterList.add(OperationInterface.class);
		filterList.add(OperationSignature.class);

		dialog = new PalladioSelectEObjectDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				filterList, additionalReferences, providedRole);
		dialog.setProvidedService(OperationSignature.class);
		dialog.open();
		if (dialog.getResult() == null) {
			return;
		}
		if (!(dialog.getResult() instanceof OperationSignature)) {
			return;
		}
		signature = (OperationSignature) dialog.getResult();
	}
}
