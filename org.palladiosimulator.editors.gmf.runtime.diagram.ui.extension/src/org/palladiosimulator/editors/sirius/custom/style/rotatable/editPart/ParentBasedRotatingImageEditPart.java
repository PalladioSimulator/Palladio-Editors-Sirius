package org.palladiosimulator.editors.sirius.custom.style.rotatable.editPart;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.notation.View;

/**
 * An {@link AbstractRotatableImageEditPart} that rotates the image based on the
 * position relative to its parent. This is meant to be used only as a style of
 * Sirius` BorderedNodes.
 * 
 * @author max
 *
 */
public class ParentBasedRotatingImageEditPart extends
		AbstractRotatableImageEditPart {

	/**
	 * Some offset... Maybe twice the border width? The parents border is null
	 * though... FIXME: will propably result in issues when the border width is
	 * changed to some value that isnt 1.
	 */
	private static final int WEIRD_OFFSET = 2;

	/**
	 * An enum that codes the orientation of the image or the side the
	 * BorderedNode is attached to.
	 * 
	 * @author max
	 *
	 */
	private static enum Orientation {
		TOP, BOTTOM, LEFT, RIGHT
	}

	/**
	 * Default constructor.
	 * 
	 * @param view
	 *            view
	 */
	public ParentBasedRotatingImageEditPart(View view) {
		super(view);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void figureHasChanged() {
		if (!(getParent() instanceof ShapeEditPart))
			return;

		switch (orientationRelativeToEditPart((ShapeEditPart) getParent()
				.getParent())) {
		case TOP:
			setFigureAtTop();
			break;
		case BOTTOM:
			setFigureAtBottom();
			break;
		case LEFT:
			setFigureAtLeft();
			break;
		case RIGHT:
			setFigureAtRight();
			break;
		}
	}

	/**
	 * Determines on which side of its parent this EditPart is displayed.
	 * 
	 * @param parent
	 *            the parent EditPart
	 * @return the orientation
	 */
	private Orientation orientationRelativeToEditPart(ShapeEditPart parent) {
		Rectangle parentRectangle = parent.getFigure().getBounds();

		if (parentRectangle.getTop().y > getLocation().y) {
			return Orientation.TOP;
		} else if (parentRectangle.getBottom().y - WEIRD_OFFSET <= getLocation().y) {
			return Orientation.BOTTOM;
		} else if (parentRectangle.getLeft().x > getLocation().x) {
			return Orientation.LEFT;
		} else if (parentRectangle.getRight().x - WEIRD_OFFSET <= getLocation().x) {
			return Orientation.RIGHT;
		}

		// This should not happen
		return Orientation.TOP;
	}

}
