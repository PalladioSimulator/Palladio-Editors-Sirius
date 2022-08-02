package org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.anchorprovider;

import java.util.EnumMap;
import java.util.Map;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AirDefaultSizeNodeFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.util.AnchorProvider;
import org.palladiosimulator.editors.sirius.custom.style.rotatable.editPart.Orientation;

/**
 * Provides an alphabased anchor pointing to the center of the oriented half.
 * 
 * @author Jonas Lehmann
 */
public class OrientedHalfAlphaBasedAnchorProvider implements AnchorProvider {

	/**
	 * Creates a provider for alphabased anchors that are located on visible imagepixels.
	 * The connector points to the center of the oriented half.
	 */
	public OrientedHalfAlphaBasedAnchorProvider() {
	}

	@Override
	public ConnectionAnchor createDefaultAnchor(AirDefaultSizeNodeFigure figure) {
		return new OrientedHalfAlphaBasedAnchor(figure);
	}

	@Override
	public ConnectionAnchor createAnchor(final AirDefaultSizeNodeFigure figure, final PrecisionPoint p) {
		return new OrientedHalfAlphaBasedAnchor(figure, p);
	}

	private static class OrientedHalfAlphaBasedAnchor extends AbstractOrientedAnchor {

		private final Map<Orientation, Translation> orientedCenterCalculators = createOrientedCenterCalculators();

		public OrientedHalfAlphaBasedAnchor(IFigure f, PrecisionPoint p) {
			super(f, p);
		}

		public OrientedHalfAlphaBasedAnchor(IFigure f) {
			super(f);
		}

		@Override
		protected Point getLocation(final Point ownReference, final Point foreignReference) {

			// Overrides the superclass method to set the centerOfOrientedHalf as reference
			// instead of the cursorPosition
			return getLocation(getCenterOfOrientedHalf(), foreignReference, PositionConstants.NONE);
		}

		private Point getCenterOfOrientedHalf() {
			Rectangle figureBounds = getOwner().getBounds();
			Point p = figureBounds.getCenter();
			Point orientedCenter = super.getOrientation(getOwner()).map(orientedCenterCalculators::get)
					.map(t -> t.translate(p, figureBounds)).orElse(p);
			getOwner().translateToAbsolute(orientedCenter);
			return orientedCenter;
		}

		private Map<Orientation, Translation> createOrientedCenterCalculators() {

			// maps a center function to each orientation to return the center of the
			// oriented half
			Map<Orientation, Translation> calculators = new EnumMap<>(Orientation.class);
			calculators.put(Orientation.LEFT, (p, fb) -> {
				return fb.getLeft().translate(fb.width / 4, 0);
			});
			calculators.put(Orientation.RIGHT, (p, fb) -> {
				return fb.getLeft().translate(fb.width * 3 / 4, 0);
			});
			calculators.put(Orientation.TOP, (p, fb) -> {
				return fb.getTop().translate(0, fb.height / 4);
			});
			calculators.put(Orientation.BOTTOM, (p, fb) -> {
				return fb.getTop().translate(0, fb.height * 3 / 4);
			});
			return calculators;
		}

		@Override
		protected Map<Orientation, Translation> createTranslationCalculators() {

			// Implements the superclass method to move the anchorPoint to the oriented half
			// if it accidentally connects to the base segment of a connector
			Map<Orientation, Translation> calculators = new EnumMap<>(Orientation.class);
			calculators.put(Orientation.LEFT, (p, fb) -> {
				return p.setX(Math.min(fb.getTop().x, p.x));
			});
			calculators.put(Orientation.RIGHT, (p, fb) -> {
				return p.setX(Math.max(fb.getTop().x, p.x));
			});
			calculators.put(Orientation.TOP, (p, fb) -> {
				return p.setY(Math.min(fb.getLeft().y, p.y));
			});
			calculators.put(Orientation.BOTTOM, (p, fb) -> {
				return p.setY(Math.max(fb.getLeft().y, p.y));
			});
			return calculators;
		}
	}
}