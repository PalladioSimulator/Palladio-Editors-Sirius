package org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.deprctd;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PrecisionPointList;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.SimpleStyleConfiguration;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AirDefaultSizeNodeFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.util.AnchorProvider;

public class EventChannelStyleConfiguration extends SimpleStyleConfiguration {

	private static class EventChannelAnchorProvider implements AnchorProvider {

		@Override
		public ConnectionAnchor createDefaultAnchor(AirDefaultSizeNodeFigure figure) {
			return new EventChannelAnchor(figure);
		}

		@Override
		public ConnectionAnchor createAnchor(final AirDefaultSizeNodeFigure figure, final PrecisionPoint p) {
			return new EventChannelAnchor(figure, p);
		}

	}

	private static class EventChannelAnchor extends SlidableAnchor {

		
		public EventChannelAnchor(IFigure f, PrecisionPoint p) {
			super(f, p);
		}
		
		public EventChannelAnchor(IFigure f) {
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
	

	@Override
	public AnchorProvider getAnchorProvider() {
		return new EventChannelAnchorProvider();
	}

}