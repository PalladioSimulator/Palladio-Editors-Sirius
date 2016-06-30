package org.palladiosimulator.editors.tabs;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.views.properties.tabbed.AbstractTypeMapper;


/**
 * @author Snowball
 * The necessary PropertyTypeMapper. We react on any EMF object.
 */
public class PropertyTypeMapper extends AbstractTypeMapper {

	@Override
	public Class<?> mapType(Object object) {
		Class<?> type = object.getClass();
		if (object instanceof EObject) {
			return type;
		}
		return super.mapType(object);
	}

}
