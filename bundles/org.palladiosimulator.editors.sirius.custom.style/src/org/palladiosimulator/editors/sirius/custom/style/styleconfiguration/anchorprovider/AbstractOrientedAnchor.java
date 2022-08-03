package org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.anchorprovider;

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
 * An abstract anchor which calculates the location of its anchorPoint dependent of its figures
 * orientation.
 *
 * The abstract method createTranslationCalculators() has to be implemented. <br>
 * <br>
 * See {@link AbstractOrientedAnchor#createTranslationCalculators()}
 *
 * @author Jonas Lehmann
 */
public abstract class AbstractOrientedAnchor extends AlphaBasedSlidableImageAnchor {

    private final Map<Orientation, Translation> translationCalculators = this.createTranslationCalculators();

    public AbstractOrientedAnchor(final IFigure f, final PrecisionPoint p) {
        super(f, p);
    }

    public AbstractOrientedAnchor(final IFigure f) {
        super(f);
    }

    @Override
    public Point getLocation(final Point reference) {
        final Rectangle figureBounds = this.getOwner()
            .getBounds();
        final Point anchorPoint = super.getLocation(reference);
        this.getOwner()
            .translateToRelative(anchorPoint);

        final Point newAnchorPoint = getOrientation(this.getOwner()).map(this.translationCalculators::get)
            .map(t -> t.translate(anchorPoint, figureBounds))
            .orElse(anchorPoint);

        this.getOwner()
            .translateToAbsolute(newAnchorPoint);

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
     * Implementation of mapping a {@link Translation} function to each orientation to translate a
     * cursor position to a valid anchorPoint location.
     * 
     * <br>
     * <br>
     * For an example implementation see {@link OperationRequiredRoleAnchorProvider}
     * 
     * @return the specified map
     */
    protected abstract Map<Orientation, Translation> createTranslationCalculators();

    protected static Optional<Orientation> getOrientation(final IFigure figure) {
        return findRotatableFigure(figure).map(RotatableSVGWorkspaceImageFigure::getCurrentOrientation);
    }

    private static Optional<RotatableSVGWorkspaceImageFigure> findRotatableFigure(final IFigure figure) {
        final LinkedList<IFigure> queue = new LinkedList<>();
        queue.add(figure);
        while (!queue.isEmpty()) {
            final IFigure currentFigure = queue.pop();
            for (final Object child : currentFigure.getChildren()) {
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