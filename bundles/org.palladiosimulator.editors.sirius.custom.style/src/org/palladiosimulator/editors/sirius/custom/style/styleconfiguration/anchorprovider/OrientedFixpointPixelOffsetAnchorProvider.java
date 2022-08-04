package org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.anchorprovider;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AirDefaultSizeNodeFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.util.AnchorProvider;

/**
 * Does the same as {@link OrientedFixpointAnchorProvider}.
 *
 * <br>
 * <br>
 *
 * But moves the tip of the edges connection 2 PIXELS away from the usually calculated anchorPoint.
 * <br>
 * This is to cope with the rendered arrow thickness of Sirius, which is 2 PIXELs.
 *
 * @author Jonas Lehmann
 */
public class OrientedFixpointPixelOffsetAnchorProvider implements AnchorProvider {

    private final double relativeOffset;
    private static int PIXEL_OFFSET = 2;

    /**
     * Creates a new {@link OrientedFixpointPixelOffsetAnchorProvider}.
     *
     * @param relativeOffset
     *            A fraction inbetween [0.0 (the oriented sides center), 1.0 (the other sides
     *            center)]
     */
    public OrientedFixpointPixelOffsetAnchorProvider(final double relativeOffset) {
        this.relativeOffset = relativeOffset;
    }

    @Override
    public ConnectionAnchor createDefaultAnchor(final AirDefaultSizeNodeFigure figure) {
        return new OrientedFixpointPixelOffsetAnchor(figure, this.relativeOffset);
    }

    @Override
    public ConnectionAnchor createAnchor(final AirDefaultSizeNodeFigure figure, final PrecisionPoint p) {
        return new OrientedFixpointPixelOffsetAnchor(figure, p, this.relativeOffset);
    }

    private static class OrientedFixpointPixelOffsetAnchor extends OrientedFixpointAnchor {

        public OrientedFixpointPixelOffsetAnchor(final IFigure f, final PrecisionPoint p, final double relativeOffset) {
            super(f, p, relativeOffset);
        }

        public OrientedFixpointPixelOffsetAnchor(final IFigure f, final double relativeOffset) {
            super(f, relativeOffset);
        }

        @Override
        public Point getLocation(final Point reference) {
            final Point anchorPoint = super.getLocation(reference);

            this.getOwner()
                .translateToRelative(anchorPoint);
            this.getOwner()
                .translateToRelative(reference);

            final double distance = anchorPoint.getDistance(reference);
            anchorPoint
                .translate((reference.getTranslated(anchorPoint.getNegated())).getScaled(1 / distance * PIXEL_OFFSET));

            this.getOwner()
                .translateToAbsolute(anchorPoint);
            this.getOwner()
                .translateToAbsolute(reference);

            return anchorPoint;
        }
    }
}