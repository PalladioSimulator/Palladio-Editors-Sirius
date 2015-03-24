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

package org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.provider;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.AbstractEditPartProvider;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.CustomStyle;
import org.obeonetwork.gmf.runtime.diagram.ui.extension.ExtensionActivator;
import org.obeonetwork.gmf.runtime.diagram.ui.extension.rotatable.editPart.ParentBasedRotatingImageEditPart;

/**
 * Specific Edit Part Provider for rotatable image
 * 
 * @author nlepine
 * @author hmarchadour
 * @author max
 */
public class RotatableImageEditPartProvider extends AbstractEditPartProvider {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.gmf.runtime.diagram.ui.services.editpart.AbstractEditPartProvider#getNodeEditPartClass(org.eclipse.gmf.runtime.notation.View)
	 */
	protected Class<?> getNodeEditPartClass(final View view) {
		final EObject semanticElement = ViewUtil.resolveSemanticElement(view);
		if (semanticElement instanceof CustomStyle) {
			final CustomStyle customStyle = (CustomStyle) semanticElement;
			if (customStyleSupported(customStyle)) {
				return ParentBasedRotatingImageEditPart.class;
			}
		}
		return super.getNodeEditPartClass(view);
	}

	private boolean customStyleSupported(CustomStyle customStyle) {
		return customStyle.getId() != null
				&& ExtensionActivator
						.getDefault()
						.getImageExtensions()
						.stream()
						.anyMatch(
								desc -> customStyle.getId()
										.equals(desc.getId()));
	}
}
