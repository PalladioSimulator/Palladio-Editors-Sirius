package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.borderItemLocator.provider;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.BorderItemLocatorProvider;
import org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.borderItemLocator.RoleBorderItemLocator;

import de.uka.ipd.sdq.pcm.repository.Role;

/**
 * A {@link BorderItemLocatorProvider} responsible for elements that are parents of {@link Role}s.
 * @author Max Schettler
 *
 */
public final class RoleBorderItemLocatorProvider implements BorderItemLocatorProvider {
	
	private static final RoleBorderItemLocatorProvider INSTANCE = new RoleBorderItemLocatorProvider();
	
	private RoleBorderItemLocatorProvider() {}
	
	public static RoleBorderItemLocatorProvider getInstance() {
		return INSTANCE;
	}

	/**
	 * Returns a {@link DBorderItemLocator} with an zero-item-offset.
	 * @see DBorderItemLocator#setBorderItemOffset(Dimension)
	 */
	@Override
	public IBorderItemLocator getBorderItemLocator(IFigure figure,
			DDiagramElement diagramElementOwner,
			DDiagramElement diagramElementBorderItem) {
		return new RoleBorderItemLocator(figure, PositionConstants.NSEW);
	}

}
