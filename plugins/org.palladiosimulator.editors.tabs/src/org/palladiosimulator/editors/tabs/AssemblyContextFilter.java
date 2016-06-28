/**
 * 
 */
package org.palladiosimulator.editors.tabs;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.IFilter;

import org.palladiosimulator.pcm.core.composition.AssemblyContext;

/**
 * @author Roman Andrej
 *
 */
public class AssemblyContextFilter implements IFilter {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
	 */
	public boolean select(Object toTest) {
		Object input = toTest;
		if (input instanceof GraphicalEditPart) {
			GraphicalEditPart ep = (GraphicalEditPart) input;
			input = ep.getModel();
		}
		if (input instanceof View) {
			input = ((View) input).getElement();
		}
		return input instanceof AssemblyContext;
	}

}
