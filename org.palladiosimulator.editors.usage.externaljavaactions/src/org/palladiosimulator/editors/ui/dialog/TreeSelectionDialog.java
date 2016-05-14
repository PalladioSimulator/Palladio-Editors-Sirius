package org.palladiosimulator.editors.ui.dialog;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

public class TreeSelectionDialog extends ElementListSelectionDialog {

	public TreeSelectionDialog(Shell parent) {
		super(parent, new AdapterFactoryLabelProvider(null));
	}

}
