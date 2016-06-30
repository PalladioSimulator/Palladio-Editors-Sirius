package org.palladiosimulator.editors.tabs;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.IFilter;

import org.palladiosimulator.pcm.repository.EventGroup;
import org.palladiosimulator.pcm.repository.EventType;

/**
 * Filter to select only elements related to event groups and types.
 * 
 * In the past, this was about the meanwhile generic Interface model 
 * element but has become more specific to distinct between OperationInterfaces 
 * and EventGroups.
 * 
 * This filter is used by the property tabs definition in the GMF editors.
 * 
 * @author Benjamin Klatt
 *
 */
public class EventGroupFilter implements IFilter {

	/**
	 * Decide if an object is about event groups and types.
	 * This could be an EventGroup or an EventType.
	 * @param toTest The element object to test.
	 * @return true/false if the object is of type EventGroup or EventType.
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
		return input instanceof EventGroup || input instanceof EventType;
	}

}
