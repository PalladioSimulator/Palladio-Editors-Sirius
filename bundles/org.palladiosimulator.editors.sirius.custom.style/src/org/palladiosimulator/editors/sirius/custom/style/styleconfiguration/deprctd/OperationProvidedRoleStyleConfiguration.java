package org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.deprctd;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
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

public class OperationProvidedRoleStyleConfiguration extends SimpleStyleConfiguration {

	private static class OperationProvidedRoleAnchorProvider implements AnchorProvider {

		@Override
		public ConnectionAnchor createDefaultAnchor(AirDefaultSizeNodeFigure figure) {
			return new OperationProvidedRoleAnchor(figure);
		}

		@Override
		public ConnectionAnchor createAnchor(final AirDefaultSizeNodeFigure figure, final PrecisionPoint p) {
			return new OperationProvidedRoleAnchor(figure, p);
		}

	}

	private static class OperationProvidedRoleAnchor extends AlphaBasedSlidableImageAnchor {

		private static final Map<Orientation, Translation> TRANSLATION_CALCULATORS = createTranslationCalculators();

		public OperationProvidedRoleAnchor(IFigure f, PrecisionPoint p) {
			super(f, p);
		}

		public OperationProvidedRoleAnchor(IFigure f) {
			super(f);
		}

		@Override
		public Point getLocation(Point reference) {
			
			Rectangle figureBounds = getOwner().getBounds();
			Point anchorPoint = super.getLocation(reference);
			getOwner().translateToRelative(anchorPoint);
			
			Point newAnchorPoint = getOrientation(getOwner()).map(TRANSLATION_CALCULATORS::get).map(t -> t.translateToOrientedHalf(anchorPoint, figureBounds))
					.orElse(anchorPoint);
			
			getOwner().translateToAbsolute(newAnchorPoint);

			return newAnchorPoint;
		}
		
		private interface Translation {
			Point translateToOrientedHalf(Point previous, Rectangle r);
		}
		
		private static Map<Orientation, Translation> createTranslationCalculators() {
			Map<Orientation, Translation> calculators = new EnumMap<>(Orientation.class);
			calculators.put(Orientation.LEFT, (p, fb) -> {return p.setX(Math.min(fb.getTop().x, p.x));});
			calculators.put(Orientation.RIGHT, (p, fb) -> {return p.setX(Math.max(fb.getTop().x, p.x));});
			calculators.put(Orientation.TOP, (p, fb) -> {return p.setY(Math.min(fb.getLeft().y, p.y));});
			calculators.put(Orientation.BOTTOM, (p, fb) -> {return p.setY(Math.max(fb.getLeft().y, p.y));});
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
		return new OperationProvidedRoleAnchorProvider();
	}

}