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
 * A property sheet to edit Interfaces of the PCM Repository package
 */
public class InterfacePropertySection extends GenericEMFPropertySection {

	/* (non-Javadoc)
	 * @see org.palladiosimulator.editors.tabs.GenericEMFPropertySection#getEClassToEdit()
	 */
	@Override
	protected EClass getEClassToEdit() {
		return RepositoryPackage.eINSTANCE.getInterface();
	}

	/* (non-Javadoc)
	 * @see org.palladiosimulator.editors.tabs.GenericEMFPropertySection#getEditableTextFields()
	 */
	@Override
	protected Collection<EMFPropertySectionFieldInfo> getEditableTextFields() {
		Collection<EMFPropertySectionFieldInfo> infos = new ArrayList<EMFPropertySectionFieldInfo>();
		infos.add(new EMFPropertySectionFieldInfo(RepositoryPackage.INTERFACE__ENTITY_NAME,"Name"));
		infos.add(new EMFPropertySectionFieldInfo(RepositoryPackage.INTERFACE__ID,"ID"));
		return infos;
	}

}
