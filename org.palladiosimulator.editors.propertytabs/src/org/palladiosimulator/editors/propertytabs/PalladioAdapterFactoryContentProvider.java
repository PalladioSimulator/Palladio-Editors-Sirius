package org.palladiosimulator.editors.propertytabs;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.celleditor.ExtendedDialogCellEditor;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.palladiosimulator.editors.dialogs.stoex.StochasticExpressionEditDialog;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;
import de.uka.ipd.sdq.pcm.stochasticexpressions.PCMStoExPrettyPrintVisitor;
import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class PalladioAdapterFactoryContentProvider extends
		AdapterFactoryContentProvider {

	public PalladioAdapterFactoryContentProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	protected IPropertySource createPropertySource(Object object, IItemPropertySource itemPropertySource) {
	    if (object instanceof RandomVariable)
	    {
	    	return getRandomVariablePropertySheet(object, itemPropertySource);
	    }
	    else
	    	return super.createPropertySource(object, itemPropertySource);
	}

	protected IPropertySource getRandomVariablePropertySheet(Object object,IItemPropertySource itemPropertySource) {
		return new PropertySource(object, itemPropertySource) {

			@Override
			protected IPropertyDescriptor createPropertyDescriptor(IItemPropertyDescriptor itemPropertyDescriptor) {
				if (itemPropertyDescriptor.getDisplayName(object).equals("Specification")) {
					return getDescriptorWithStoExParser(object,itemPropertyDescriptor);
				} else {
					return super.createPropertyDescriptor(itemPropertyDescriptor);
				}
			}
			
		};
	}

	private IPropertyDescriptor getDescriptorWithStoExParser(Object object, IItemPropertyDescriptor itemPropertyDescriptor) {
		return new PropertyDescriptor(object,itemPropertyDescriptor) {

			@Override
			public CellEditor createPropertyEditor(Composite composite) {
				
				CellEditor result = new ExtendedDialogCellEditor(composite, new AdapterFactoryLabelProvider(adapterFactory)) {

					@Override
					protected Object openDialogBox(Control cellEditorWindow) {
						RandomVariable randVar = (RandomVariable) object;
						StochasticExpressionEditDialog dialog =
							new StochasticExpressionEditDialog(
									cellEditorWindow.getShell(),
									getExpectedType(randVar),
									randVar);
						dialog.setInitialExpression(randVar);
						dialog.open();
						if (dialog.getReturnCode() == Dialog.OK) {
							String result = new PCMStoExPrettyPrintVisitor().prettyPrint(dialog.getResult());
							return result;
						}
						return null;
					}
				};
				return result;
			}
		};
	}

	protected TypeEnum getExpectedType(RandomVariable rv) {
		TypeEnum expectedType = TypeEnum.ANY; 
		if (rv instanceof VariableCharacterisation){
			expectedType = StochasticExpressionEditDialog.getTypeFromVariableCharacterisation((VariableCharacterisation) rv);
		}
		return expectedType;
	}	
}
