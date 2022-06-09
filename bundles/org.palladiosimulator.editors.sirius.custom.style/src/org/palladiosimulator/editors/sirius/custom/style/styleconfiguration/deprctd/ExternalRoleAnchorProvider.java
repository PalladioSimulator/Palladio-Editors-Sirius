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

public class ExternalRoleAnchorProvider implements AnchorProvider {

	@Override
	public ConnectionAnchor createDefaultAnchor(AirDefaultSizeNodeFigure figure) {
		return new OperationRolesAnchor(figure);
	}

	@Override
	public ConnectionAnchor createAnchor(final AirDefaultSizeNodeFigure figure, final PrecisionPoint p) {
		return new OperationRolesAnchor(figure, p);
	}

	private static class OperationRolesAnchor extends AbstractOrientedAnchor {

		public OperationRolesAnchor(IFigure f, PrecisionPoint p) {
			super(f, p);
		}

		public OperationRolesAnchor(IFigure f) {
			super(f);
		}
		
		@Override
		protected Map<Orientation, Translation> createTranslationCalculators() {
			Map<Orientation, Translation> calculators = new EnumMap<>(Orientation.class);
			calculators.put(Orientation.LEFT, (p, fb) -> {
				return fb.getTopRight().translate(0, fb.height() / 2);
			});
			calculators.put(Orientation.RIGHT, (p, fb) -> {
				return fb.getTopLeft().translate(0, fb.height() / 2);
			});
			calculators.put(Orientation.BOTTOM, (p, fb) -> {
				return fb.getTopLeft().translate(fb.width() / 2, 0);
			});
			calculators.put(Orientation.TOP, (p, fb) -> {
				return fb.getBottomLeft().translate(fb.width() / 2, 0);
			});
			return calculators;
		}
	}
}