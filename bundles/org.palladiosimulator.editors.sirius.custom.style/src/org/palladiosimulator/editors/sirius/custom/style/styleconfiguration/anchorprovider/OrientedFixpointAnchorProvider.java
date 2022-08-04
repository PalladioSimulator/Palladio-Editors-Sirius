package org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.anchorprovider;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AirDefaultSizeNodeFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.util.AnchorProvider;

/**
 * Provides an anchor with specified offset of the oriented sides center.
 *
 * <br>
 * <br>
 * e.g. if offset = 1/4, the anchor point is moved one quarter of the rectangle width along the
 * center axis away from the Orientated sides centerPoint.
 *
 * @author Jonas Lehmann
 */
public class OrientedFixpointAnchorProvider implements AnchorProvider {

    private final double relativeOffset;

    /**
     * Creates a new {@link OrientedFixpointAnchorProvider}.
     *
     * @param relativeOffset
     *            A fraction inbetween [0.0 (the oriented sides center), 1.0 (the other sides
     *            center)]
     */
    public OrientedFixpointAnchorProvider(final double relativeOffset) {
        this.relativeOffset = relativeOffset;
    }

    @Override
    public ConnectionAnchor createDefaultAnchor(final AirDefaultSizeNodeFigure figure) {
        return new OrientedFixpointAnchor(figure, this.relativeOffset);
    }

    @Override
    public ConnectionAnchor createAnchor(final AirDefaultSizeNodeFigure figure, final PrecisionPoint p) {
        return new OrientedFixpointAnchor(figure, p, this.relativeOffset);
    }
}