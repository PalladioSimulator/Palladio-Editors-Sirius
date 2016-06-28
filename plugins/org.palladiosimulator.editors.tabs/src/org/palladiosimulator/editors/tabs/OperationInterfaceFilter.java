package org.palladiosimulator.editors.tabs;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.IFilter;

import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationSignature;

/**
 * Filter to select only elements of types related to operation interfaces.
 * In the past, this was about the meanwhile generic Interface model 
 * element but has become more specific to distinct between OperationInterfaces 
 * and EventGroups.
 * 
 * This filter is used by the property tabs definition in the GMF editors.
 * 
 * @author Benjamin Klatt
 *
 */
public class OperationInterfaceFilter implements IFilter {

	/**
	 * Decide if an object is about operation interfaces.
	 * This could be an OperationInterface or an OperationSignature.
	 * @param toTest The element object to test.
	 * @return true/false if the object is of type OperationInterface or OperationSignature
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
		return input instanceof OperationInterface || input instanceof OperationSignature;
	}

}
