/**
 * 
 */
package org.palladiosimulator.editors.tabs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @author Snowball
 * A tabbed property sheet section which can be configured to display 
 * a set of generic EMFPropertTextEditField (and maybe other later on).
 * It uses a template method pattern to get a list of characterising objects
 * describing the fields to be displayed in this sheet section
 */

public abstract class GenericEMFPropertySection extends AbstractPropertySection {

	private ArrayList<EMFPropertyTextEdit> editFields = new ArrayList<EMFPropertyTextEdit>();
	
	/**
	 * This method sets up the property sheet section using a template method
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public void createControls(
		Composite parent,
		TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		
		// Call the template method
		Collection<EMFPropertySectionFieldInfo> editFieldsInfo = getEditableTextFields();
		
		EMFPropertyTextEdit predecessor = null;
		Composite composite =
			getWidgetFactory().createFlatFormComposite(parent);
		for(Iterator<EMFPropertySectionFieldInfo> it = editFieldsInfo.iterator(); it.hasNext(); )
		{
			EMFPropertySectionFieldInfo info = it.next();
			EMFPropertyTextEdit newField = 
				new EMFPropertyTextEdit(composite, info.getLabel(), 
						getEClassToEdit().
							getEStructuralFeature(info.getFeatureID()), 
							getWidgetFactory(),
							predecessor);
			predecessor = newField;
			editFields.add(newField);
		}
	}	
	
	/**
	 * Returns a list of edit fields which are used to display features of EMF meta-model 
	 * classes 
	 * @return A list of EMF features which should be editable in this property sheet section
	 */
	protected abstract Collection<EMFPropertySectionFieldInfo> getEditableTextFields();
	
	/**
	 * The EClass of the objects which can be displayed in this property sheet.
	 * A single model object is edited in the property sheet. The features which are
	 * diplayed on the sheet are set using the getEditableTextFields method
	 * @return
	 */
	protected abstract EClass getEClassToEdit();
	
	/**
	 * The model object which should be edited in the tab sheet is passed in here
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		Assert.isTrue(selection instanceof IStructuredSelection);
		Object input = ((IStructuredSelection)selection).getFirstElement();
		if (input instanceof GraphicalEditPart)
		{
			GraphicalEditPart ep = (GraphicalEditPart)input;
			input = ep.getModel();
		}
		if (input instanceof View){
			input = ((View)input).getElement();
		}
		Assert.isTrue(input instanceof EObject);
		EObject inputEObject = (EObject)input;
		for(Iterator<EMFPropertyTextEdit> it = editFields.iterator(); it.hasNext(); )
		{
			EMFPropertyTextEdit editField = it.next();
			editField.setEObject(inputEObject);
		}
	}

}
