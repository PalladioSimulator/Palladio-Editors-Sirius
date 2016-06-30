/**
 * 
 */
package org.palladiosimulator.editors.tabs;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EClass;

import org.palladiosimulator.pcm.repository.RepositoryPackage;

/**
 * @author Snowball
 * This class provides a property sheet section to edit ProvidesComponentTypes
 */
public class ComponentPropertySection extends GenericEMFPropertySection {

	@Override
	protected EClass getEClassToEdit() {
		return RepositoryPackage.eINSTANCE.getProvidesComponentType();
	}

	@Override
	protected Collection<EMFPropertySectionFieldInfo> getEditableTextFields() {
		Collection<EMFPropertySectionFieldInfo> infos = new ArrayList<EMFPropertySectionFieldInfo>();
		infos.add(new EMFPropertySectionFieldInfo(RepositoryPackage.PROVIDES_COMPONENT_TYPE__ENTITY_NAME,"Name"));
		infos.add(new EMFPropertySectionFieldInfo(RepositoryPackage.PROVIDES_COMPONENT_TYPE__ID,"ID"));
		return infos;
	}	
}
