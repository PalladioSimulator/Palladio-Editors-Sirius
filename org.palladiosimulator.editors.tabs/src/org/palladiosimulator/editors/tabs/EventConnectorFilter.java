package org.palladiosimulator.editors.tabs;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.IFilter;

import org.palladiosimulator.pcm.core.composition.AssemblyEventConnector;
import org.palladiosimulator.pcm.core.composition.EventChannelSinkConnector;


public class EventConnectorFilter implements IFilter {

	@Override
	public boolean select(Object toTest) {
		Object input = toTest;
		if (input instanceof GraphicalEditPart) {
			GraphicalEditPart ep = (GraphicalEditPart) input;
			input = ep.getModel();
		}
		if (input instanceof View) {
			input = ((View) input).getElement();
		}
	return input instanceof EventChannelSinkConnector || input instanceof AssemblyEventConnector;
	}
}
