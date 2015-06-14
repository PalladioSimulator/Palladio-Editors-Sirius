package org.scaledl.architecturaltemplates.ui;

import java.util.Arrays;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.modelversioning.emfprofile.Stereotype;
import org.scaledl.architecturaltemplates.type.Role;

/**
 * A dialog for selecting {@link Role}s.
 * @author Max Schettler
 *
 */
public class RoleSelectionDialog extends ElementListSelectionDialog {

	private static final String EMPTY_LIST_MESSAGE = "No Roles can be selected";
	private static final String TITLE = "Select Role";
	private static final String EMPTY_SELECTION_MESSAGE = "You need to select a Role to continue";

	public RoleSelectionDialog(final Shell parent) {
		super(parent, new LabelProvider() {
			
			@Override
			public String getText(Object element) {
				return ((Stereotype) element).getName();
			}
			
			@Override
			public Image getImage(Object element) {
				// TODO implement
				return null;
			}
		});
		setValidator(new ISelectionStatusValidator() {
			
			@Override
			public IStatus validate(Object[] selection) {
				return selection.length == 1 ? Status.OK_STATUS : Status.CANCEL_STATUS;
			}
		});
		
		setTitle(TITLE);
		setEmptySelectionMessage(EMPTY_SELECTION_MESSAGE);
		setEmptyListMessage(EMPTY_LIST_MESSAGE);
	}
	
	/**
	 * {@inheritDoc}
	 * @throws IllegalArgumentException if not all elements are of the type {@link Role}
	 */
	@Override
	public void setElements(Object[] elements) {
		if (!Arrays.stream(elements).allMatch(element -> element instanceof Role)) {
			throw new IllegalArgumentException("All elements must be of type \"Role\"");
		}
		super.setElements(elements);
	}

	/**
	 * Returns the selected {@link Role}.
	 * @return
	 */
	public Role getResultRole() {
		return (Role) getResult()[0];
	}

}
