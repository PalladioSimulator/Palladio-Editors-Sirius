package org.palladiosimulator.editors.sirius.resourceenvironment.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.commons.dialogs.stoex.StochasticExpressionEditDialog;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;
import org.palladiosimulator.pcm.resourceenvironment.CommunicationLinkResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;

import de.uka.ipd.sdq.pcm.stochasticexpressions.PCMStoExPrettyPrintVisitor;
import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class CreateExternalStoexEditor implements IExternalJavaAction {

	public CreateExternalStoexEditor() {
		super();
	}
	
	@Override
	public boolean canExecute(Collection<? extends EObject> arg0) {
		return true;
	}

	@Override
	public void execute(Collection<? extends EObject> arg0, Map<String, Object> arg1) {
		if(arg0.size() != 1)
			throw new IllegalArgumentException("Multiselection not supported");
		var linking =  arg0.iterator().next();
		if(!(linking instanceof CommunicationLinkResourceSpecification))
			throw new IllegalArgumentException("Only CommunicationLinkResourceSpecification supported");
		var linkingResource = (CommunicationLinkResourceSpecification) linking;
		
		RandomVariable randVar;
		var type = (String) arg1.get("type");
		
		switch (type) {
		case "latency":
			randVar  = linkingResource.getLatency_CommunicationLinkResourceSpecification();
			break;

		default:
			throw new IllegalArgumentException("RandomVar Type missing");
		}
				
		
		
		createDialog(randVar);

	}

	private void createDialog(RandomVariable randVar) {
		StochasticExpressionEditDialog dialog = new StochasticExpressionEditDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				getExpectedType(randVar), randVar);
		dialog.setInitialExpression(randVar);
		dialog.open();
		if (dialog.getReturnCode() == Dialog.OK) {
			String result = new PCMStoExPrettyPrintVisitor().prettyPrint(dialog.getResult());
			randVar.setSpecification(result);
		}
	}
	private TypeEnum getExpectedType(RandomVariable rv) {
		TypeEnum expectedType = TypeEnum.ANY; 
		if (rv instanceof VariableCharacterisation){
			expectedType = StochasticExpressionEditDialog.getTypeFromVariableCharacterisation((VariableCharacterisation) rv);
		}
		return expectedType;
	}	

}
