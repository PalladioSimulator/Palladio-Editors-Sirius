package org.palladiosimulator.editors.tabs;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.services.IDisposable;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Snowball
 * This class provides a fully generic edit field which can be used to edit
 * EMF features of type string. Any given feature of any EMF meta-model can 
 * be specified and will be displayed using a label and a text edit field.
 */
public class EMFPropertyTextEdit implements IDisposable {


	private TabbedPropertySheetWidgetFactory factory;

	private Text valueText;

	private Listener listener;

	private String labelText;
	
	/**
	 * The transactional editing domain which is used to get the commands and alter the model 
	 */
	protected TransactionalEditingDomain editingDomain = null;
	
	private EObject element;
	private EStructuralFeature attribute;

	private EMFPropertyTextEdit predecessor;

	private EContentAdapter listeningAdapter;

	private Composite composite;


	/**
	 * Constructor of the generic EMF edit field
	 * @param parentForm The SWT parent form on which this edit field will be shown
	 * @param labelText The text which is displayed in front of the edit field
	 * @param attribute The EMF feature which is being edited in the edit field
	 * @param factory The widged factory used to create the UI parts
	 * @param predecessor The preceeding edit field - 
	 * 		  this is used to layout the edit fields on the property tab sheets.
	 * 		  Pass in null for the first field
	 */
	public EMFPropertyTextEdit(Composite parentForm, 
			String labelText,
			EStructuralFeature attribute,
			TabbedPropertySheetWidgetFactory factory,
			EMFPropertyTextEdit predecessor) {
		
		this.factory = factory;
		this.labelText = labelText;
		this.attribute = attribute;
		this.predecessor = predecessor;
		this.composite = parentForm;

		init();
	}

	private void init() {
		createTextField();
		createDisplayLabel();
		addChangeListener();
	}


	/**
	 * This listener reacts on Focus Out and On Enter, in both cases we store the current value in the model via a command
	 */
	private void addChangeListener() {
		listener = new Listener() {

			public void handleEvent(Event event) {
				switch (event.type) {
				case SWT.KeyDown:
					if (event.character == SWT.CR)
						textChanged((Control) event.widget);
					break;
				case SWT.FocusOut:
					textChanged((Control) event.widget);
					break;
				}
			}
		};
		valueText.addListener(SWT.KeyDown, listener);
		valueText.addListener(SWT.FocusOut, listener);
		valueText.addListener(SWT.Modify, listener);
	}

	private void createDisplayLabel() {
		FormData data;
		CLabel valueLabel = factory.createCLabel(composite, labelText+":"); //$NON-NLS-1$
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(valueText,
				-ITabbedPropertyConstants.HSPACE);
		data.top = new FormAttachment(valueText, 0, SWT.CENTER);
		valueLabel.setLayoutData(data);
	}

	private void createTextField() {
		FormData data;
		valueText = factory.createText(composite, ""); //$NON-NLS-1$
		data = new FormData();
		if (predecessor == null){
			data.top = new FormAttachment(0, 0);
			data.left = new FormAttachment(0,
				AbstractPropertySection.STANDARD_LABEL_WIDTH);
			data.right = new FormAttachment(100, 0);
		} else {
			data.top =
				new FormAttachment(
					predecessor.getValueText(),
					ITabbedPropertyConstants.VSPACE,
					SWT.BOTTOM);
			data.left = new FormAttachment(predecessor.getValueText(), 0, SWT.LEFT);
			data.right = new FormAttachment(predecessor.getValueText(), 0, SWT.RIGHT);
		}
		valueText.setLayoutData(data);
	}
	
	private Text getValueText() {
		return valueText;
	}

	/**
	 * Initialise this edit field with its model object which is displayed and
	 * edited in this text field
	 * @param object Model object which is edited in this text field
	 */
	public void setEObject(EObject object)
	{
		this.editingDomain = TransactionUtil.getEditingDomain(object);
		this.element = object;
		this.listeningAdapter = new EContentAdapter(){

			/* (non-Javadoc)
			 * @see org.eclipse.emf.ecore.util.EContentAdapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
			 */
			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				if (notification.getEventType() == Notification.SET && notification.getFeature().equals(attribute))
				{
					if (!valueText.getText().equals(element.eGet(attribute)))
						valueText.setText((String)element.eGet(attribute));
				}
			}
			
		}; 
		object.eAdapters().add(listeningAdapter);
		String newValue = element.eGet(attribute) == null ? "" : (String)element.eGet(attribute); 
		valueText.setText(newValue);
	}
	
	/**
	 * Listener which issues edit commands whenever the edit field content
	 * is changed
	 * @param control The control sending the event
	 */
	private void textChanged(Control control) {
		RecordingCommand recCommand = new RecordingCommand(editingDomain) {

			@Override
			protected void doExecute() {
				element.eSet(attribute, valueText.getText());
			}
			
		};
		if (!valueText.getText().equals(element.eGet(attribute)))
		{
			recCommand.setDescription("Edit Component Property");
			recCommand.setLabel("Set "+attribute.getName());
			editingDomain.getCommandStack().execute(recCommand);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.services.IDisposable#dispose()
	 */
	public void dispose() {
		this.element.eAdapters().remove(listeningAdapter);
		valueText.removeListener(SWT.KeyDown, listener);
		valueText.removeListener(SWT.FocusOut, listener);
		valueText.removeListener(SWT.Modify, listener);
	}
	
}
