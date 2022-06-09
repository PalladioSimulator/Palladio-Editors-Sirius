package org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.deprctd;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.SimpleStyleConfiguration;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AirDefaultSizeNodeFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AlphaBasedSlidableImageAnchor;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.util.AnchorProvider;
import org.palladiosimulator.editors.sirius.custom.style.rotatable.editPart.Orientation;
import org.palladiosimulator.editors.sirius.custom.style.rotatable.figure.RotatableSVGWorkspaceImageFigure;

public class OperationRequiredRoleStyleConfiguration extends SimpleStyleConfiguration {

	private static class OperationRequiredRoleAnchorProvider implements AnchorProvider {

		@Override
		public ConnectionAnchor createDefaultAnchor(AirDefaultSizeNodeFigure figure) {
			return new OperationRequiredRoleAnchor(figure);
		}

		@Override
		public ConnectionAnchor createAnchor(final AirDefaultSizeNodeFigure figure, final PrecisionPoint p) {
			return new OperationRequiredRoleAnchor(figure, p);
		}

	}

	private static class OperationRequiredRoleAnchor extends AlphaBasedSlidableImageAnchor {

		private static final Map<Orientation, Function<Rectangle, Point>> LOCATION_CALCULATORS = createLocationCalculators();

		public OperationRequiredRoleAnchor(IFigure f, PrecisionPoint p) {
			super(f, p);
		}

		public OperationRequiredRoleAnchor(IFigure f) {
			super(f);
		}

		@Override
		public Point getLocation(Point reference) {
			Rectangle figureBounds = getOwner().getBounds();
			Point anchorPoint = super.getLocation(reference);
			getOwner().translateToRelative(anchorPoint);

			anchorPoint = getOrientation(getOwner()).map(LOCATION_CALCULATORS::get).map(c -> c.apply(figureBounds))
					.orElse(anchorPoint);
			getOwner().translateToAbsolute(anchorPoint);

			return anchorPoint;
		}
		
		private static Map<Orientation, Function<Rectangle, Point>> createLocationCalculators() {
			Map<Orientation, Function<Rectangle, Point>> calculators = new EnumMap<>(Orientation.class);
			calculators.put(Orientation.LEFT, fb -> fb.getTopLeft().translate(fb.width() / 4, fb.height() / 2));
			calculators.put(Orientation.RIGHT, fb -> fb.getTopRight().translate(-fb.width() / 4, fb.height() / 2));
			calculators.put(Orientation.BOTTOM, fb -> fb.getBottomLeft().translate(fb.width() / 2, -fb.height() / 4));
			calculators.put(Orientation.TOP, fb -> fb.getTopLeft().translate(fb.width() / 2, fb.height() / 4));
			return calculators;
		}

		private static Optional<Orientation> getOrientation(IFigure figure) {
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

	@Override
	public AnchorProvider getAnchorProvider() {
		return new OperationRequiredRoleAnchorProvider();
	}

}