/**
 * 
 */
package org.palladiosimulator.editors.tabs.parameters;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.parameter.VariableUsage;
import org.palladiosimulator.pcm.repository.BasicComponent;

/**
 * @author Roman Andrej
 */
public class ParameterContentProvider implements IStructuredContentProvider {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {

		if (inputElement instanceof AssemblyContext) {
			AssemblyContext context = (AssemblyContext) inputElement;

			EList<VariableUsage> contextParametrs = context
					.getConfigParameterUsages__AssemblyContext();
			if (context.getEncapsulatedComponent__AssemblyContext() instanceof BasicComponent){
				EList<VariableUsage> componentParameters = ((BasicComponent) context
						.getEncapsulatedComponent__AssemblyContext())
						.getComponentParameterUsage_ImplementationComponentType();
				return meargeParameter(contextParametrs, componentParameters);

			}
			
		}

		return null;
	}
	
	private Object[] meargeParameter(EList<VariableUsage> ctxParameters,
			EList<VariableUsage> cmpParameters) {

		VariableUsageWrapper removeWrapper = null;

		List<VariableUsageWrapper> parametrs = new ArrayList<VariableUsageWrapper>();

		for (VariableUsage variableUsage : cmpParameters) {
			parametrs.add(new VariableUsageWrapper(variableUsage));
		}

		for (VariableUsage ctx_variableUsage : ctxParameters) {
			VariableUsageWrapper wrapper = new VariableUsageWrapper(
					ctx_variableUsage, true);
			for (VariableUsageWrapper cmpWrapper : parametrs) {
				if (wrapper.equals(cmpWrapper)) {
					removeWrapper = cmpWrapper;
				}

			}

			int index = parametrs.size();

			if (removeWrapper != null) {
				index = parametrs.indexOf(removeWrapper);
				parametrs.remove(removeWrapper);
				removeWrapper = null;
			}

			parametrs.add(index, wrapper);
		}

		return parametrs.toArray();
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
		// The implementation is not necessary.
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// The implementation is not necessary.
	}
}
