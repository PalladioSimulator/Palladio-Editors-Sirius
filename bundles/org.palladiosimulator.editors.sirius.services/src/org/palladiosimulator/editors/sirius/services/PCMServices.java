package org.palladiosimulator.editors.sirius.services;

import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.ui.PlatformUI;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofileapplication.ProfileImport;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import org.palladiosimulator.architecturaltemplates.Role;
import org.palladiosimulator.architecturaltemplates.api.ArchitecturalTemplateAPI;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import de.uka.ipd.sdq.identifier.Identifier;

public class PCMServices {

	private static final String PARAMETER_VALUE_LITERAL_DIALOG_TITLE = "Enter value literal";
	private static final String PARAMETER_VALUE_LITERAL_DIALOG_MESSAGE = "Please enter a literal to set the parameter";

	public PCMServices() {
		super();
	}

	/**
	 * @see StereotypeAPI#getStereotypeApplications(EObject)
	 */
	public Collection<StereotypeApplication> getStereotypeApplications(final EObject eObject) {
		return StereotypeAPI.getStereotypeApplications(eObject);
	}

    /**
     * @see StereotypeAPI#getStereotypeApplications(EObject)
     * @see ArchitecturalTemplateAPI#isArchitecturalTemplateStereotypeApplication(StereotypeApplication)
     */
	public Collection<StereotypeApplication> getATStereotypeApplications(final EObject eObject) {
		return getStereotypeApplications(eObject).stream()
				.filter(ArchitecturalTemplateAPI::isArchitecturalTemplateStereotypeApplication)
				.collect(Collectors.toList());
	}

    /**
     * @see ArchitecturalTemplateAPI#isRole(Stereotype)
     */
    public boolean isRole(final StereotypeApplication stereotypeApplication) {
        return ArchitecturalTemplateAPI.isRole(stereotypeApplication.getStereotype());
    }

	/**
	 * @see ArchitecturalTemplateAPI#isSystemRole(Stereotype)
	 */
	public boolean isSystemRole(final StereotypeApplication stereotypeApplication) {
		return ArchitecturalTemplateAPI.isSystemRole(stereotypeApplication.getStereotype());
	}

	/**
	 * Queries an {@link String} literal from the user and uses
	 * {@link #setParameterValue(EStructuralFeature, EObject, String)} to set the
	 * value.
	 * 
	 * @param parameter
	 *            the parameter to set
	 * @param owningEObject
	 *            the object for which the feature will be set
	 */
	public void queryAndSetParameterValue(final EStructuralFeature parameter, final EObject owningEObject) {
		final InputDialog inputDialog = new InputDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				PARAMETER_VALUE_LITERAL_DIALOG_TITLE, PARAMETER_VALUE_LITERAL_DIALOG_MESSAGE, null, null);
		if (inputDialog.open() == Dialog.OK) {
			setParameterValue(parameter, owningEObject, inputDialog.getValue());
		}
	}

	/**
	 * Sets the value of the {@link EStructuralFeature} on the given {@link EObject}
	 * to the parsed value literal.
	 * 
	 * @param parameter
	 *            the feature to set
	 * @param owningEObject
	 *            the object for which the feature will be set
	 * @param valueLiteral
	 *            the literal representing the new value
	 */
	public void setParameterValue(final EStructuralFeature parameter, final EObject owningEObject,
			final String valueLiteral) {
		final EClassifier eType = parameter.getEType();
		Object value = null;

		if (valueLiteral == null && eType != null) {
			value = parameter.isMany() ? null : eType.getDefaultValue();
		} else if (eType instanceof EDataType) {
			final EFactory factory = eType.getEPackage().getEFactoryInstance();
			final EDataType eDataType = (EDataType) eType;
			if (eDataType.isSerializable()) {
				try {
					value = factory.createFromString(eDataType, valueLiteral);
				} catch (final Throwable e) {
					// At development time, the real factory may not be
					// available. Just return null.
					//
				}
			}
		}
		owningEObject.eSet(parameter, value);
	}

	/**
	 * Returns the {@link EStructuralFeature}s that define the {@link Stereotype}`s
	 * parameters.
	 * 
	 * @param stereotype
	 *            the {@link Stereotype}
	 * @return the parameters` features
	 */
	public Collection<EStructuralFeature> getParameters(final Stereotype stereotype) {
		return StereotypeAPI.getParameters(stereotype);
	}

	/**
	 * Returns the {@link StereotypeApplication}s that define a {@link Role}
	 * -Application on the given {@link EObject}.
	 * 
	 * @param eObject
	 *            object to get roles for
	 * @return collection of role-StereotypeApplications
	 * @see ArchitecturalTemplateAPI#getRoleApplications(EObject)
	 */
	public Collection<StereotypeApplication> getRoleApplications(final EObject eObject) {
		return ArchitecturalTemplateAPI.getRoleApplications(eObject);
	}

    public Collection<StereotypeApplication> getStereotypeApplicationsWithoutRoles(final EObject eObject) {
        return ArchitecturalTemplateAPI.getATStereotypeApplicationsWithoutRoles(eObject);
    }

    /**
     * Returns the {@link Profile}s on the given {@link EObject} that are associated with architectural templates.
     * 
     * @param eObject
     *            object to get profiles for
     * @return collection of Profiles
     * @see ArchitecturalTemplateAPI#getProfiles(EObject)
     */
    public Collection<ProfileImport> getATProfileImports(final EObject eObject) {
        return ArchitecturalTemplateAPI.getATProfileImports(eObject);
    }

	/**
	 * Returns, whether the given {@link StereotypableElement} has roles applied.
	 * 
	 * @param object
	 *            object to test
	 * @return indicator for applied roles
	 */
	public boolean hasRoles(final EObject object) {
		return ArchitecturalTemplateAPI.hasRoles(object);
	}

	/**
	 * Sets the given string as a specification on the {@link PCMRandomVariable}. It
	 * is assumed that the given string is a valid value (Validation is performed in
	 * the {@link RandomVariableXtextStyledTextCellEditorEx}.
	 *
	 * @param pcmRandomVariable
	 *            the random variable
	 * @param expression
	 *            the expression
	 * @return the random variable
	 */
	public EObject editPCMRandomVariable(final EObject pcmRandomVariable, final String expressionString) {
		if (!(pcmRandomVariable instanceof PCMRandomVariable)) {
			return null;
		}

		((PCMRandomVariable) pcmRandomVariable).setSpecification(expressionString);

		return pcmRandomVariable;
	}

	/**
	 * Retrieves the top-most container view (i.e. the diagram element) from the
	 * specified container view. It does the job of getting the element referenced
	 * by the variable "diagram". This function should in fact be used when the
	 * variable "diagram" is not available.
	 * 
	 * @param containerView
	 *            the container view from which the top-most container is going to
	 *            be retrieved
	 * @return the diagram element
	 */
	public DSemanticDiagram getSemanticDiagram(EObject containerView) {
		EObject container = containerView;
		while (!(container instanceof DSemanticDiagram)) {
			container = container.eContainer();
		}
		return (DSemanticDiagram) container;
	}

	/**
	 * Copies an EObject
	 * 
	 * @param eObject
	 *            the EObject to be copied
	 * @return The copy
	 */
	public EObject copyEObject(EObject eObject) {
		Copier copier = new Copier();
		EObject copy = copier.copy(eObject);
		copier.copyReferences();
		return copy;

	}

	/**
	 * Copies an Identifier and generates new IDs for it and all its contents
	 * 
	 * @param eObject
	 *            Identifier to be copied
	 * @return The copy
	 */
	public Identifier copyIdentifier(Identifier eObject) {
		Identifier copy = (Identifier) copyEObject(eObject);
		copy.setId(EcoreUtil.generateUUID());
		TreeIterator<EObject> it = copy.eAllContents();
		while (it.hasNext()) {
			EObject o = it.next();
			if (o instanceof Identifier) {
				((Identifier) o).setId(EcoreUtil.generateUUID());
			}
		}
		return copy;
	}

}