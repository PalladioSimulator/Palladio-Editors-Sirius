package org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.anchorprovider;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AirDefaultSizeNodeFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.util.AnchorProvider;

/**
 * This is an AnchorProvider which decorates another AnchorProvider with an additional Offset.
 *
 * <br>
 * <br>
 *
 * It moves the tip of the edges connection 2 PIXELS away from the usually calculated anchorPoint.
 * <br>
 * This is to cope with the rendered arrow thickness of Sirius, which is 2 PIXELs.
 *
 * @author Jonas Lehmann
 */
public class PixelOffsetDecorator implements AnchorProvider {

    private final static int PIXEL_OFFSET = 2;
    private final AnchorProvider anchorProvider;

    /**
     * Creates a new {@link PixelOffsetDecorator}.
     *
     * @param anchorProvider
     *            the AnchorProvider which should be decorated
     */
    public PixelOffsetDecorator(final AnchorProvider anchorProvider) {
        this.anchorProvider = anchorProvider;
    }

    @Override
    public ConnectionAnchor createDefaultAnchor(final AirDefaultSizeNodeFigure figure) {
        final ConnectionAnchor anchor = this.anchorProvider.createDefaultAnchor(figure);
        return new AnchorDecorator(anchor, figure);
    }

    @Override
    public ConnectionAnchor createAnchor(final AirDefaultSizeNodeFigure figure, final PrecisionPoint p) {
        final ConnectionAnchor anchor = this.anchorProvider.createAnchor(figure, p);
        return new AnchorDecorator(anchor, figure);
    }

    private class AnchorDecorator extends AbstractConnectionAnchor {

        private final ConnectionAnchor anchor;

        public AnchorDecorator(final ConnectionAnchor anchor, final IFigure f) {
            super(f);
            this.anchor = anchor;
        }

        @Override
        public Point getLocation(final Point reference) {
            final PrecisionPoint ref = new PrecisionPoint(reference);
            final Point prevAnchorPoint = this.anchor.getLocation(ref);
            final PrecisionPoint anchorPoint = new PrecisionPoint(prevAnchorPoint);

            this.getOwner()
                .translateToRelative(anchorPoint);
            this.getOwner()
                .translateToRelative(ref);

            final double distance = anchorPoint.getDistance(ref);
            anchorPoint.translate((ref.getTranslated(anchorPoint.getNegated())).getScaled(1 / distance * PIXEL_OFFSET));

            this.getOwner()
                .translateToAbsolute(anchorPoint);
            this.getOwner()
                .translateToAbsolute(ref);

            return anchorPoint;
        }
    }
}