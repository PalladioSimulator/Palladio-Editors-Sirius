package org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.anchorProvider;

import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AlphaBasedSlidableImageAnchor;
import org.palladiosimulator.editors.sirius.custom.style.rotatable.editPart.Orientation;
import org.palladiosimulator.editors.sirius.custom.style.rotatable.figure.RotatableSVGWorkspaceImageFigure;

/**
 * An abstract anchor which calculates the location of its anchorPoint dependent
 * of its figures orientation.
 * 
 * The abstract method createTranslationCalculators() has to be implemented.
 * <br>
 * <br>
 * See {@link AbstractOrientedAnchor#createTranslationCalculators()}
 * 
 * @author Jonas Lehmann
 */
public abstract class AbstractOrientedAnchor extends AlphaBasedSlidableImageAnchor {

	private final Map<Orientation, Translation> translationCalculators = createTranslationCalculators();

	public AbstractOrientedAnchor(IFigure f, PrecisionPoint p) {
		super(f, p);
	}

	public AbstractOrientedAnchor(IFigure f) {
		super(f);
	}

	@Override
	public Point getLocation(Point reference) {
		Rectangle figureBounds = getOwner().getBounds();
		Point anchorPoint = super.getLocation(reference);
		getOwner().translateToRelative(anchorPoint);

		Point newAnchorPoint = getOrientation(getOwner()).map(translationCalculators::get)
				.map(t -> t.translate(anchorPoint, figureBounds)).orElse(anchorPoint);

		getOwner().translateToAbsolute(newAnchorPoint);

		return newAnchorPoint;
	}

	/**
	 * A translation inbetween rectangle bounds. <br>
	 * e.g. translateToLeftHalf: <br>
	 * (p, bounds) -> {return p.setX(Math.min(bounds.getTop().x, p.x));}
	 */
	protected interface Translation {
		Point translate(Point previous, Rectangle r);
	}

	/**
	 * Implementation of mapping a {@link Translation} function to each orientation
	 * to translate a cursor position to a valid anchorPoint location.
	 * 
	 * <br>
	 * <br>
	 * For an example implementation see {@link OperationRequiredRoleAnchorProvider}
	 * 
	 * @return the specified map
	 */
	protected abstract Map<Orientation, Translation> createTranslationCalculators();

	protected static Optional<Orientation> getOrientation(IFigure figure) {
		return findRotatableFigure(figure).map(RotatableSVGWorkspaceImageFigure::getCurrentOrientation);
	}

	private static Optional<RotatableSVGWorkspaceImageFigure> findRotatableFigure(IFigure figure) {
		LinkedList<IFigure> queue = new LinkedList<>();
		queue.add(figure);
		while (!queue.isEmpty()) {
			IFigure currentFigure = queue.pop();
			for (Object child : currentFigure.getChildren()) {
				if (child instanceof RotatableSVGWorkspaceImageFigure) {
					return Optional.of((RotatableSVGWorkspaceImageFigure) child);
				} else if (child instanceof IFigure) {
					queue.add((IFigure) child);
				}
			}
		}
		return Optional.empty();
	}
}