package org.palladiosimulator.editors.repository.externaljavaactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.pcm.repository.CollectionDataType;
import org.palladiosimulator.pcm.repository.PrimitiveDataType;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryPackage;

public class AddCollectionDataType implements IExternalJavaAction {

	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		CollectionDataType cdt = (CollectionDataType) parameters.get("instance");
		PrimitiveDataType pdt = getPrimitiveDataType(cdt);
		cdt.setInnerType_CollectionDataType(pdt);

	}

	private PrimitiveDataType getPrimitiveDataType(CollectionDataType cdt) {
		// Filter list (Repository > PrimitiveDataType)
		Collection<Object> filter = new ArrayList<Object>();
		filter.add(Repository.class);
		filter.add(PrimitiveDataType.class);

		// Additional Child References
		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();
		additionalChildReferences.add(RepositoryPackage.Literals.COLLECTION_DATA_TYPE__INNER_TYPE_COLLECTION_DATA_TYPE);

		// Creating the dialog
		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				cdt.eResource().getResourceSet());

		// Setting the needed object type
		dialog.setProvidedService(PrimitiveDataType.class);

		dialog.open();

		return (PrimitiveDataType) dialog.getResult();
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
