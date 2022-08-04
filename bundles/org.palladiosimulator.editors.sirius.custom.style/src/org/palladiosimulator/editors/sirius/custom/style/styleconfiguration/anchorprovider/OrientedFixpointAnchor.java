package org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.anchorprovider;

import java.util.EnumMap;
import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.palladiosimulator.editors.sirius.custom.style.rotatable.editPart.Orientation;

public class OrientedFixpointAnchor extends AbstractOrientedAnchor {

    private final double relativeOffset;

    public OrientedFixpointAnchor(final IFigure f, final PrecisionPoint p, final double relativeOffset) {
        super(f, p);
        this.relativeOffset = relativeOffset;
    }

    public OrientedFixpointAnchor(final IFigure f, final double relativeOffset) {
        super(f);
        this.relativeOffset = relativeOffset;
    }

    @Override
    protected Map<Orientation, Translation> createTranslationCalculators() {
        final Map<Orientation, Translation> calculators = new EnumMap<>(Orientation.class);
        calculators.put(Orientation.LEFT, (p, fb) -> {
            return fb.getTopLeft()
                .translate(fb.width() * this.relativeOffset, fb.height() / 2);
        });
        calculators.put(Orientation.RIGHT, (p, fb) -> {
            return fb.getTopRight()
                .translate(-fb.width() * this.relativeOffset, fb.height() / 2);
        });
        calculators.put(Orientation.BOTTOM, (p, fb) -> {
            return fb.getBottomLeft()
                .translate(fb.width() / 2, -fb.height() * this.relativeOffset);
        });
        calculators.put(Orientation.TOP, (p, fb) -> {
            return fb.getTopLeft()
                .translate(fb.width() / 2, fb.height() * this.relativeOffset);
        });
        return calculators;
    }
}