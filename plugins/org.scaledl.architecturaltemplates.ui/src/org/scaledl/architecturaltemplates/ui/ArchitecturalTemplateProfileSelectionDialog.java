package org.scaledl.architecturaltemplates.ui;

import java.util.Arrays;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.modelversioning.emfprofile.Profile;
import org.scaledl.architecturaltemplates.api.ArchitecturalTemplateAPI;
import org.scaledl.architecturaltemplates.type.AT;

/**
 * A dialog for selecting a profile that defines an Architectural Template ({@link AT}).
 * TODO maybe this should select the AT directly?
 * @author Max Schettler
 *
 */
public class ArchitecturalTemplateProfileSelectionDialog extends
		ElementListSelectionDialog {

	private static final String EMPTY_LIST_MESSAGE = "There are no registered Architectural Templates";
	private static final String EMPTY_SELECTION_MESSAGE = "You need to select an Architectural Template";
	private static final String TITLE = "Select an Architectural Template";

	public ArchitecturalTemplateProfileSelectionDialog(Shell parent) {
		super(parent, new LabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Profile) element).getName();
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
	 * @throws IllegalArgumentException if not all elements are of the type {@link Profile}
	 */
	@Override
	public void setElements(Object[] elements) {
		if (!Arrays.stream(elements).allMatch(element -> element instanceof Profile) && Arrays.stream(elements).map(element -> (Profile) element).allMatch(ArchitecturalTemplateAPI.isArchitecturalTemplate)) {
			throw new IllegalArgumentException("All elements must be of type \"Profile\"");
		}
		super.setElements(elements);
	}
	
	public Profile getResultProfile() {
		return (Profile) getResult()[0];
	}

	

}
