package org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.anchorProvider;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PrecisionPointList;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AirDefaultSizeNodeFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.util.AnchorProvider;

/**
 * Provides a slidable anchor located on a diamond shaped border.
 * 
 * @author Jonas Lehmann
 */
public class DiamondShapedSlidableAnchorProvider implements AnchorProvider {

	/**
	 * Creates a provider for anchors that are located on a diamond shaped border.
	 * The diamond is defined by the centerpoints of sides of the figures rectangle
	 * bounds. The anchorpoints are slidable on the specified diamond edges.
	 */
	public DiamondShapedSlidableAnchorProvider() {
	}

	@Override
	public ConnectionAnchor createDefaultAnchor(AirDefaultSizeNodeFigure figure) {
		return new DiamondShapedSlidableAnchor(figure);
	}

	@Override
	public ConnectionAnchor createAnchor(final AirDefaultSizeNodeFigure figure, final PrecisionPoint p) {
		return new DiamondShapedSlidableAnchor(figure, p);
	}

	private static class DiamondShapedSlidableAnchor extends SlidableAnchor {

		public DiamondShapedSlidableAnchor(IFigure f, PrecisionPoint p) {
			super(f, p);
		}

		public DiamondShapedSlidableAnchor(IFigure f) {
			super(f);
		}

		protected PointList getIntersectionPoints(Point ownReference, Point foreignReference) {
			PrecisionRectangle r = new PrecisionRectangle(getBox());
			PrecisionPointList ptList = new PrecisionPointList(5);
			ptList.addPoint(new PrecisionPoint(r.getTop().preciseX(), r.getTop().preciseY()));
			ptList.addPoint(new PrecisionPoint(r.getLeft().preciseX(), r.getLeft().preciseY()));
			ptList.addPoint(new PrecisionPoint(r.getBottom().preciseX(), r.getBottom().preciseY()));
			ptList.addPoint(new PrecisionPoint(r.getRight().preciseX(), r.getRight().preciseY()));
			ptList.addPoint(new PrecisionPoint(r.getTop().preciseX(), r.getTop().preciseY()));
			final PointList polygon = ptList;
			return (new LineSeg(ownReference, foreignReference)).getLineIntersectionsWithLineSegs(polygon);
		}
	}

}