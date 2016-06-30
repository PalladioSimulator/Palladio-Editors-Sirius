package org.palladiosimulator.editors.tabs;

/**
 * @author Snowball
 * This class is a record to store the specification of a generic input field
 * for the tabbed propery sheets. The generic fields can edit any eCore attribute 
 * given
 */
public class EMFPropertySectionFieldInfo {

	private int featureID = 0;
	private String label = null;

	/**
	 * The ID of the eCore feature which is being specified in this record
	 * @return the feature which is edited in the field specified here
	 */
	public int getFeatureID() {
		return featureID;
	}
	/**
	 * Returns the display label displayed in front of the edit field
	 * which is used to edit the stored feature
	 * @return the label which is displayed in front of the edit field
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Constructor of the Field Info record. It stores the EMF feature id
	 * and the display label of the described field
	 * @param featureID The id of the EMF feature
	 * @param label The display label which is displayed in front of the edit field
	 */
	public EMFPropertySectionFieldInfo(int featureID, String label)
	{
		this.featureID = featureID;
		this.label = label;
	}
}
