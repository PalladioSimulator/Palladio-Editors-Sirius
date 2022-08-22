package org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.anchorprovider;

import org.eclipse.draw2d.AnchorListener;
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
        return new AnchorDecorator(anchor);
    }

    @Override
    public ConnectionAnchor createAnchor(final AirDefaultSizeNodeFigure figure, final PrecisionPoint p) {
        final ConnectionAnchor anchor = this.anchorProvider.createAnchor(figure, p);
        return new AnchorDecorator(anchor);
    }

    private class AnchorDecorator implements ConnectionAnchor {

        private final ConnectionAnchor anchor;

        public AnchorDecorator(final ConnectionAnchor anchor) {
            this.anchor = anchor;
        }

        @Override
        public void addAnchorListener(final AnchorListener listener) {
            this.anchor.addAnchorListener(listener);
        }

        @Override
        public Point getLocation(final Point reference) {
            PrecisionPoint ref = new PrecisionPoint(reference);
            final Point anchorPoint = this.anchor.getLocation(ref);

            this.getOwner()
                .translateToRelative(anchorPoint);
            this.getOwner()
                .translateToRelative(ref);

            final double distance = anchorPoint.getDistance(ref);
            anchorPoint
                .translate((ref.getTranslated(anchorPoint.getNegated())).getScaled(1 / distance * PIXEL_OFFSET));

            this.getOwner()
                .translateToAbsolute(anchorPoint);
            this.getOwner()
                .translateToAbsolute(ref);

            return anchorPoint;
        }

        @Override
        public IFigure getOwner() {
            return this.anchor.getOwner();
        }

        @Override
        public Point getReferencePoint() {
            return this.anchor.getReferencePoint();
        }

        @Override
        public void removeAnchorListener(final AnchorListener listener) {
            this.anchor.removeAnchorListener(listener);
        }

    }

}
