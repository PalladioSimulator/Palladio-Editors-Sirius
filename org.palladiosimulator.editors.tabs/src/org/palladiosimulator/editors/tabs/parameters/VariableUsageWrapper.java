package org.palladiosimulator.editors.tabs.parameters;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.palladiosimulator.pcm.parameter.VariableUsage;
import de.uka.ipd.sdq.pcm.stochasticexpressions.PCMStoExPrettyPrintVisitor;

/**
 * The class build a container for VariableUsage value. IsEdited value will by
 * set:
 * 	- if VariableUsage belong to BasicComponente on 'false'.
 *  - if VariableUsage belong to AssemblyContect on 'true'.
 */
public class VariableUsageWrapper extends EObjectImpl {
	
	private VariableUsage variableUsage = null;
	private boolean isEdited = false;
	
	
	public VariableUsageWrapper() {
	}
	
	public VariableUsageWrapper(VariableUsage variableUsage) {
		this.variableUsage = variableUsage;
	}

	
	public VariableUsageWrapper(VariableUsage variableUsage, boolean isEdited) {
		this.variableUsage = variableUsage;
		this.isEdited = isEdited;
	}


	/**
	 * @return the variableUsage
	 */
	public VariableUsage getVariableUsage() {
		return variableUsage;
	}


	/**
	 * @param variableUsage the variableUsage to set
	 */
	public void setVariableUsage(VariableUsage variableUsage) {
		this.variableUsage = variableUsage;
	}


	/**
	 * @return the isEdited
	 */
	public boolean isEdited() {
		return isEdited;
	}


	/**
	 * @param isEdited the isEdited to set
	 */
	public void setEdited(boolean isEdited) {
		this.isEdited = isEdited;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object eObject) {
		if (eObject instanceof VariableUsageWrapper) {
			VariableUsageWrapper wrapper = (VariableUsageWrapper) eObject;
			return getName(variableUsage).equals(
					getName(wrapper.getVariableUsage()));

		}
		return super.equals(eObject);
	}
	
	
	private String getName(VariableUsage variableUsage){
		return new PCMStoExPrettyPrintVisitor().prettyPrint(variableUsage);
	}
	
	
	
	
	
}
