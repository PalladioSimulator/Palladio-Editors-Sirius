package org.palladiosimulator.editors.sirius.ui.wizard.project;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * Helper class to access installed work-flow extensions.
 * 
 * @author Benjamin Klatt
 */
public class ExtensionHelper {

    /** The Constant for the palladio model extension point id. */
    private static final String TemplateExtensionPointId = "org.palladiosimulator.editors.sirius.ui.wizard.template";

    /** The name attribute of the template extension point. */
    private static final String TemplateExtensionPointAttribute_Name = "name";

    /** The description attribute of the template extension point. */
    private static final String TemplateExtensionPointAttribute_Description = "description";

    /** The description attribute of the template extension point. */
    private static final String TemplateExtensionPointAttribute_ModelFile = "model_file";

	private static final String TemplateExtensionPointAttribute_TemplateURI = "template_uri";

	private static final String TemplateExtensionPointAttribute_TargetFile = "target_file";

    /**
     * Build the set of palladio model templates registered for the
     * according extension point.
     * 
     * @return The list of identified palladio model templates.
     */
    public Set<PalladioTemplate> getPalladioModelTemplates() {
        HashSet<PalladioTemplate> templates = new HashSet<PalladioTemplate>();
        IExtension[] registeredExtensions = getRegisteredTemplateModelExtensions();
        if (registeredExtensions == null) {
        	return templates;
        }
        for (int i = 0; i < registeredExtensions.length; i++) {
            IExtension registeredExtension = registeredExtensions[i];
            IConfigurationElement[] elements = registeredExtension.getConfigurationElements();
            for (int j = 0; j < elements.length; j++) {
                IConfigurationElement element = elements[j];
                String name = element.getAttribute(TemplateExtensionPointAttribute_Name);
                String description = element.getAttribute(TemplateExtensionPointAttribute_Description);
                
                HashMap<String,String> modelFiles = new HashMap<String,String>();
                IConfigurationElement[] fileDefinitions = element.getChildren(TemplateExtensionPointAttribute_ModelFile);
                for (IConfigurationElement iConfigurationElement : fileDefinitions) {
                	String source = iConfigurationElement.getAttribute(TemplateExtensionPointAttribute_TemplateURI);
                	String target = iConfigurationElement.getAttribute(TemplateExtensionPointAttribute_TargetFile);
                	modelFiles.put(source, target);
                }
                
                PalladioTemplate template = new PalladioTemplate(name, modelFiles, description);
                templates.add(template);
            }
        }
        return templates;
    }

    /**
     * Get the extensions providing the model template extension point.
     * 
     * @return The list of installed model templates.
     */
    private IExtension[] getRegisteredTemplateModelExtensions() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        if (registry == null) {
            // No registry available
            return null;
        }
        IExtensionPoint extensionPoint = registry.getExtensionPoint(TemplateExtensionPointId);
        if (extensionPoint == null) {
            // No extension point found!
            return null;
        }
        IExtension[] extensions = extensionPoint.getExtensions();
        return extensions;
    }

}
