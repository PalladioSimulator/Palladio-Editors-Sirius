package org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.anchorProvider;

import java.util.EnumMap;
import java.util.Map;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AirDefaultSizeNodeFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.util.AnchorProvider;
import org.palladiosimulator.editors.sirius.custom.style.rotatable.editPart.Orientation;

public class OrientedHalfAlphaBasedAnchorProvider implements AnchorProvider {

	@Override
	public ConnectionAnchor createDefaultAnchor(AirDefaultSizeNodeFigure figure) {
		return new OperationProvidedRoleAnchor(figure);
	}

	@Override
	public ConnectionAnchor createAnchor(final AirDefaultSizeNodeFigure figure, final PrecisionPoint p) {
		return new OperationProvidedRoleAnchor(figure, p);
	}

	private static class OperationProvidedRoleAnchor extends AbstractOrientedAnchor {

		public OperationProvidedRoleAnchor(IFigure f, PrecisionPoint p) {
			super(f, p);
		}

		public OperationProvidedRoleAnchor(IFigure f) {
			super(f);
		}

		@Override
		protected Map<Orientation, Translation> createTranslationCalculators() {
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