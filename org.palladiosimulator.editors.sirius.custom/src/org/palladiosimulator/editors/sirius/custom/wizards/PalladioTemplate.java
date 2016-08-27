package org.palladiosimulator.editors.sirius.custom.wizards;

import java.util.HashMap;
import java.util.Map;

/**
 * A template for a palladio model set.
 * @author Benjamin Klatt
 *
 */
public class PalladioTemplate {
	
	/** The unique id of the template. */
	private String name = null;
		
	/** The map of model files with original source path => target filename. */
	private Map<String,String> modelFiles = new HashMap<String, String>(); 
	
	/** An optional description of the template. */
	private String description = null;
	
	public PalladioTemplate() {}
	
	/**
	 * Constructor to set the template attributes.
	 * @param templateID The id of the template.
	 * @param modelFiles The set of model files (source URI => target file name)
	 * @param description The description of the template.
	 */
	public PalladioTemplate(String templateID, Map<String,String> modelFiles, String description) {
		this.name = templateID;
		this.modelFiles = modelFiles;
		this.description = description;
	}
	

	/**
	 * @return the templateID
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the modelFiles
	 */
	public Map<String, String> getModelFiles() {
		return modelFiles;
	}

	/**
	 * @param modelFiles the modelFiles to set
	 */
	public void setModelFiles(Map<String, String> modelFiles) {
		this.modelFiles = modelFiles;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
