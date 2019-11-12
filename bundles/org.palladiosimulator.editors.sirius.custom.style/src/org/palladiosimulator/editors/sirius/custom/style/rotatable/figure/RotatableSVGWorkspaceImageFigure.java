/*******************************************************************************
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/

package org.palladiosimulator.editors.sirius.custom.style.rotatable.figure;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.CustomStyle;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SVGWorkspaceImageFigure;
import org.palladiosimulator.editors.sirius.custom.style.ExtensionActivator;
import org.palladiosimulator.editors.sirius.custom.style.rotatable.NodeImageExtension;
import org.palladiosimulator.editors.sirius.custom.style.rotatable.editPart.Orientation;

/**
 * Rotatable Workspace Image Figure : switch mode ROTATION or IMAGE, rotate the
 * specific image or display four images in North South East and West.
 * 
 * @author nlepine
 * @author hmarchadour
 */
public class RotatableSVGWorkspaceImageFigure extends SVGWorkspaceImageFigure {

	private final Map<Orientation, String> imgPaths = new EnumMap<>(Orientation.class);
	private Orientation currentOrientation = Orientation.TOP;

	/**
	 * Creates a rotative image
	 * 
	 * @param path
	 *            the path of the top image.
	 */
	public RotatableSVGWorkspaceImageFigure(int mode, String topImgPath,
			String leftImgPath, String bottomImgPath, String rightImgPath) {
		super();

		imgPaths.put(Orientation.TOP, topImgPath);
		imgPaths.put(Orientation.BOTTOM, bottomImgPath);
		imgPaths.put(Orientation.LEFT, leftImgPath);
		imgPaths.put(Orientation.RIGHT, rightImgPath);

		refreshFigure();
	}

	/**
	 * Refresh the figure.
	 * 
	 * @param imageStyle
	 *            the image associated to the figure
	 */
	public void refreshFigure(final CustomStyle imageStyle) {
		
		for (Iterator<NodeImageExtension> iterator = ExtensionActivator.getDefault()
				.getImageExtensions().iterator(); iterator.hasNext(); ) {
			NodeImageExtension desc = (NodeImageExtension) iterator.next();
			if (imageStyle.getId() != null && imageStyle.getId().equals(desc.getId())) {
				imgPaths.put(Orientation.TOP, desc.getTopImage());
				imgPaths.put(Orientation.BOTTOM, desc.getBottomImage());
				imgPaths.put(Orientation.LEFT, desc.getLeftImage());
				imgPaths.put(Orientation.RIGHT, desc.getRightImage());
				break;
			}
		}

		refreshFigure();
		this.repaint();
	}

	private void refreshFigure() {
		WorkspaceImage createWorkspaceImage = DiagramFactory.eINSTANCE
				.createWorkspaceImage();
		createWorkspaceImage.setWorkspacePath(getCurrentImgPath());
		refreshFigure(createWorkspaceImage);
	}

	/**
	 * Refresh the figure.
	 * 
	 * @param containerStyle
	 *            the style of the container
	 */
	public void refreshFigure(final ContainerStyle containerStyle) {

		if (containerStyle instanceof CustomStyle) {
			refreshFigure((CustomStyle) containerStyle);
		}
	}

	protected void setCurrentOrientation(Orientation orientation) {
		this.currentOrientation = orientation;
		refreshFigure();
	}
	
	public Orientation getCurrentOrientation() {
		return currentOrientation;
	}
	
	/**
	 * @return the currentImgPath
	 */
	public String getCurrentImgPath() {
		return imgPaths.get(currentOrientation);
	}

	/**
	 * @param bottomImgPath
	 *            the bottomImgPath to set
	 */
	public void setBottomImgAsCurrent() {
		setCurrentOrientation(Orientation.BOTTOM);
	}

	/**
	 * @param leftImgPath
	 *            the leftImgPath to set
	 */
	public void setLeftImgAsCurrent() {
		setCurrentOrientation(Orientation.LEFT);
	}

	/**
	 * @param rightImgPath
	 *            the rightImgPath to set
	 */
	public void setRightImgAsCurrent() {
		setCurrentOrientation(Orientation.RIGHT);
	}

	/**
	 * @param topImgPath
	 *            the topImgPath to set
	 */
	public void setTopImgAsCurrent() {
		setCurrentOrientation(Orientation.TOP);
	}

}
