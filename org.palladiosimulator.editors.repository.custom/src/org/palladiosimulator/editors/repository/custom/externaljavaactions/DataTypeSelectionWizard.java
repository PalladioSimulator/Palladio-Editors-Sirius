package org.palladiosimulator.editors.repository.custom.externaljavaactions;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.Repository;

public class DataTypeSelectionWizard {
	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	
	
	public static DataType selectDataType(EObject eObject) {
		Collection<Object> filter = new ArrayList<Object>();
		filter.add(Repository.class);
		filter.add(DataType.class);
		
		// Additional Child References
		Collection<EReference> additionalChildReferences = new ArrayList<EReference>();

		// Creating the dialog
		PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(SHELL, filter, additionalChildReferences,
				eObject.eResource().getResourceSet());

		// Setting the needed object type
		dialog.setProvidedService(DataType.class);

		dialog.open();

		return (DataType) dialog.getResult();
	}
}
