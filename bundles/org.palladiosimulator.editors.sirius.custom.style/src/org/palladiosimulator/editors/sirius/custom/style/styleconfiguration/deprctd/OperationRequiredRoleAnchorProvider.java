package org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.deprctd;

import java.util.EnumMap;
import java.util.Map;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AirDefaultSizeNodeFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.util.AnchorProvider;
import org.palladiosimulator.editors.sirius.custom.style.rotatable.editPart.Orientation;
import org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.anchorProvider.AbstractOrientedAnchor;

/**
 * Provides an anchor whith 1/4 offset of the oriented sides center
 * @author Jonas Lehmann
 */
public class OperationRequiredRoleAnchorProvider implements AnchorProvider {

	@Override
	public ConnectionAnchor createDefaultAnchor(AirDefaultSizeNodeFigure figure) {
		return new OperationRequiredRoleAnchor(figure);
	}

	@Override
	public ConnectionAnchor createAnchor(final AirDefaultSizeNodeFigure figure, final PrecisionPoint p) {
		return new OperationRequiredRoleAnchor(figure, p);
	}

	private static class OperationRequiredRoleAnchor extends AbstractOrientedAnchor {

		public OperationRequiredRoleAnchor(IFigure f, PrecisionPoint p) {
			super(f, p);
		}

		public OperationRequiredRoleAnchor(IFigure f) {
			super(f);
		}
		
		@Override
		protected Map<Orientation, Translation> createTranslationCalculators() {
			Map<Orientation, Translation> calculators = new EnumMap<>(Orientation.class);
			calculators.put(Orientation.LEFT, (p, fb) -> {
				return fb.getTopLeft().translate(fb.width() / 4, fb.height() / 2);
			});
			calculators.put(Orientation.RIGHT, (p, fb) -> {
				return fb.getTopRight().translate(-fb.width() / 4, fb.height() / 2);
			});
			calculators.put(Orientation.BOTTOM, (p, fb) -> {
				return fb.getBottomLeft().translate(fb.width() / 2, -fb.height() / 4);
			});
			calculators.put(Orientation.TOP, (p, fb) -> {
				return fb.getTopLeft().translate(fb.width() / 2, fb.height() / 4);
			});
			return calculators;
		}
	}
}