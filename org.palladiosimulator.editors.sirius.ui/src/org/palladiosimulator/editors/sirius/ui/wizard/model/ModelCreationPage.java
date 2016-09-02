package org.palladiosimulator.editors.sirius.ui.wizard.model;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

public class ModelCreationPage extends WizardNewFileCreationPage {
    private final String pageName;
    private final String message = "Choose a file name and location";

    public ModelCreationPage(final IStructuredSelection selection, String pageName, String fileName, String fileExt) {
        super(pageName, selection);
        setFileName(fileName);
        setMessage(message);
        setFileExtension(fileExt);
        
        this.pageName = pageName;
    }

    @Override
    public void createControl(final Composite parent) {
        super.createControl(parent);
        setMessage(message);
        setTitle(pageName);
    }

    /**
     * Need to override this to set the correct message. The super-implementation sets it to
     * null.
     */
    @Override
    protected boolean validatePage() {
        final boolean valid = super.validatePage();
        if (valid)
            setMessage(message);
        return valid;
    }

    public URI getPlatformURI() {
        return URI.createPlatformResourceURI(getContainerFullPath().append(getFileName()).toOSString(), true);
    }
}
