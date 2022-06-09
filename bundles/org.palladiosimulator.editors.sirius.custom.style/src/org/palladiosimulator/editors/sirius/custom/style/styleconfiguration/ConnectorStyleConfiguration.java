package org.palladiosimulator.editors.sirius.custom.style.styleconfiguration;

import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.SimpleStyleConfiguration;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.util.AnchorProvider;

public class ConnectorStyleConfiguration extends SimpleStyleConfiguration {

	AnchorProvider anchorProvider;

	/**
	 * Creates a StyleConfiguration for providing anchors for a connector
	 * 
	 * @param anchorProvider the provider for the specific anchortype
	 */
	public ConnectorStyleConfiguration(AnchorProvider anchorProvider) {
		this.anchorProvider = anchorProvider;
	}

	@Override
	public AnchorProvider getAnchorProvider() {
		return anchorProvider;
	}

}