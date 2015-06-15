package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.borderItemLocator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;

/**
 * This class is a bad workaround for a Sirius bug and should be removed as soon
 * as possible. It prevents the border item offset to be set to the default
 * value by overriding the {@link #getBorderItemOffset()}-method.
 * 
 * @author Max Schettler
 *
 */
public class RoleBorderItemLocator extends DBorderItemLocator {
	
	private static final Dimension BORDER_ITEM_OFFSET = new Dimension(0, 0);

	public RoleBorderItemLocator(IFigure parentFigure, int preferredSide) {
		super(parentFigure, preferredSide);
		super.setBorderItemOffset(BORDER_ITEM_OFFSET);
	}

	@Override
	public void setBorderItemOffset(final Dimension borderItemOffset) {
		// Prevent resetting the borderItemOffset
	}
	

}
