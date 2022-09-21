package org.palladiosimulator.editors.sirius.custom.style.styleconfiguration;

import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.SimpleStyleConfiguration;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.util.AnchorProvider;

public class ConnectorStyleConfiguration extends SimpleStyleConfiguration {

    private final static int MAX_LABEL_DISTANCE = 50;

    AnchorProvider anchorProvider;

    /**
     * Creates a StyleConfiguration for providing anchors for a connector.<br>
     * Furthermore it changes the Label positioning to make the Labels freely draggable.
     *
     * @param anchorProvider
     *            the provider for the specific anchortype
     */
    public ConnectorStyleConfiguration(final AnchorProvider anchorProvider) {
        this.anchorProvider = anchorProvider;
    }

    @Override
    public AnchorProvider getAnchorProvider() {
        return this.anchorProvider;
    }

    @Override
    public IBorderItemLocator getNameBorderItemLocator(final DNode node, final IFigure mainFigure) {
        final IBorderItemLocator locator = super.getNameBorderItemLocator(node, mainFigure);
        return new FreeDraggableBorderItemLocator(mainFigure, locator.getCurrentSideOfParent());
    }

    /**
     * A BorderItemLocator for Labels that should be freely draggable. <br>
     * <br>
     * Labels can be dragged to a maximal distance of MAX_LABEL_DISTANCE (50 Pixels) away from the
     * node.
     *
     * @author Jonas Lehmann
     */
    private class FreeDraggableBorderItemLocator extends DBorderItemLocator {

        /**
         * Creates a new {@link FreeDraggableBorderItemLocator}.
         *
         * @param parentFigure
         *            The Parent Node to which the label belongs to
         * @param preferredSide
         *            The preferred side
         */
        public FreeDraggableBorderItemLocator(final IFigure parentFigure, final int preferredSide) {
            super(parentFigure, preferredSide);
        }

        @Override
        public void relocate(final IFigure borderItem) {
            final Rectangle parentBounds = this.getParentFigure()
                .getBounds()
                .getCopy();
            if (!(parentBounds.x == 0 && parentBounds.y == 0 && parentBounds.width <= 0 && parentBounds.height <= 0)) {
                super.relocate(borderItem);
                final Point newLocation = this.translateIntoBounds(this.getPreferredLocation(borderItem), parentBounds,
                        borderItem.getBounds());
                borderItem.setLocation(newLocation);
            }
        }

        @Override
        public Rectangle getValidLocation(final Rectangle proposedLocation, final IFigure borderItem,
                final Collection<IFigure> figuresToIgnore, final List<IFigure> additionalFiguresForConflictDetection) {
            return this.getValidLocation(proposedLocation, borderItem);
        }

        @Override
        public Rectangle getValidLocation(final Rectangle proposedLocation, final IFigure borderItem) {
            final Point newLocation = this.translateIntoBounds(proposedLocation.getTopLeft(), this.getParentFigure()
                .getBounds(), borderItem.getBounds());
            return proposedLocation.setLocation(newLocation);
        }

        private Point translateIntoBounds(final Point location, final Rectangle parentBounds,
                final Rectangle borderItemBounds) {
            final int width = borderItemBounds.width();
            final int height = borderItemBounds.height();
            location.setX(Math.max(parentBounds.getLeft().x - MAX_LABEL_DISTANCE - width,
                    (Math.min(parentBounds.getRight().x + MAX_LABEL_DISTANCE, location.x))));
            location.setY(Math.max(parentBounds.getTop().y - MAX_LABEL_DISTANCE - height,
                    (Math.min(parentBounds.getBottom().y + MAX_LABEL_DISTANCE, location.y))));
            return location;
        }
    }
}