/**
 * 
 */
package org.palladiosimulator.editors.tabs.parameters;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.palladiosimulator.editors.dialogs.stoex.StoExCompletionProcessor;
import org.palladiosimulator.pcm.repository.Parameter;

/**
 * @author Roman Andrej
 *
 */
public class StoExTextCellEditor extends TextCellEditor {
	
	/**
	 * The text control; initially <code>null</code>.
	 */
    protected Text text;
    
    private ContentAssistant contentAssistant;
    
	public StoExTextCellEditor(Composite parent) {
		super(parent);
		contentAssistant = new ContentAssistant();
		contentAssistant.setContentAssistProcessor(
				new StoExCompletionProcessor(new Parameter[0]),
				IDocument.DEFAULT_CONTENT_TYPE);
		contentAssistant.setAutoActivationDelay(1);
		contentAssistant.enableAutoActivation(true);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.TextCellEditor#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createControl(Composite parent) {
		// TODO Auto-generated method stub
		text =  (Text) super.createControl(parent);
		
		
		text.addListener(SWT.KeyDown, new Listener(){

			public void handleEvent(Event event) {
				if (event.character == ' ' && (event.stateMask & SWT.CTRL) == SWT.CTRL){
					contentAssistant.showPossibleCompletions();
				}
			}
			
		});
		
		return text;
	}
}
