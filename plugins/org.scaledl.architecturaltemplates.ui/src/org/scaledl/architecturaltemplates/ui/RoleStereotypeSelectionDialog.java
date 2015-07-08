package org.scaledl.architecturaltemplates.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.modelversioning.emfprofile.Stereotype;
import org.palladiosimulator.commons.eclipseutils.FileHelper;
import org.scaledl.architecturaltemplates.api.ArchitecturalTemplateAPI;
import org.scaledl.architecturaltemplates.type.Role;

/**
 * A dialog for selecting {@link Role}-{@link Stereotype}s.
 * 
 * @see ArchitecturalTemplateAPI#isRole(Stereotype)
 * 
 *      TODO: This dialog should be reworked to select {@link Role}s directly
 *      instead of their {@link Stereotype}s (As it is done in the
 *      {@link ArchitecturalTemplateSelectionDialog}. However, this breaks the
 *      ComposedProvidingRequiringEntity Editor (Acceleo ArgumentTypeMissmatch)
 *      at the moment...
 * 
 * @author Max Schettler
 *
 */
public class RoleStereotypeSelectionDialog extends ElementListSelectionDialog {

	private static final String EMPTY_LIST_MESSAGE = "No Role-Stereotypes can be selected";
	private static final String TITLE = "Select Role-Stereotype";
	private static final String EMPTY_SELECTION_MESSAGE = "You need to select a Role-Stereotype to continue";

	public RoleStereotypeSelectionDialog(final Shell parent) {
		super(parent, new LabelProvider() {
			
			@Override
			public String getText(Object element) {
				return ((Stereotype) element).getName();
			}

			@Override
			public Image getImage(Object element) {
                try {
					return new Image(Display.getCurrent(), new FileInputStream(FileHelper.getFile("platform:plugin/org.scaledl.architecturaltemplates.edit/icons/full/obj16/Role.gif")));
				} catch (FileNotFoundException e) {
					e.printStackTrace();	// TODO proper error handling
					return null;
				}
			}
			
			@Override
			public void dispose() {
				super.dispose();
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
	 * 
	 * @throws IllegalArgumentException
	 *             if not all elements are of the type {@link Stereotype} and
	 *             define a {@link Role}s.
	 * @see ArchitecturalTemplateAPI#isRole(Stereotype)
	 */
	@Override
	public void setElements(Object[] elements) {
		for (Object o : elements) {
			if (!(o instanceof Stereotype && ArchitecturalTemplateAPI.isRole((Stereotype) o))) {
				throw new IllegalArgumentException("All elements must be of type \"Stereotype\"");
			}
		}
		super.setElements(elements);
	}

	/**
	 * Returns the selected {@link Stereotype}.
	 * 
	 * @return the stereotype
	 */
	public Stereotype getResultRoleStereotype() {
		return (Stereotype) getResult()[0];
	}

}
