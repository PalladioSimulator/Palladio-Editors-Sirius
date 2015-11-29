package org.scaledl.architecturaltemplates.ocl.editor;

import org.eclipse.emf.common.ui.celleditor.ExtendedDialogCellEditor;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.ocl.examples.xtext.console.XtextConsolePlugin;
import org.eclipse.ocl.examples.xtext.console.xtfo.EmbeddedXtextEditor;
import org.eclipse.ocl.pivot.values.Value;
import org.eclipse.ocl.xtext.base.ui.model.BaseDocument;
import org.eclipse.ocl.xtext.essentialocl.utilities.EssentialOCLPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.IXtextModelListener;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.scaledl.architecturaltemplates.type.OCLConstraint;

import com.google.inject.Injector;

public class OCLConstraintPropertyDescriptor extends PropertyDescriptor {
    private StyledText textWidget;
    private EmbeddedXtextEditor embeddedXtextEditor;

    private String oclConstraintExpression;
    private String newOclConstraintExpression;

    public OCLConstraintPropertyDescriptor(final Object id, final IItemPropertyDescriptor itemPropertyDescriptor) {
        super(id, itemPropertyDescriptor);
        // TODO Auto-generated constructor stub
    }

    @Override
    public CellEditor createPropertyEditor(final Composite composite) {
        CellEditor result = super.createPropertyEditor(composite);
        if (result == null)
            return result;

        if (object instanceof org.scaledl.architecturaltemplates.type.OCLConstraint) {
            final String attributeName = ((EStructuralFeature) itemPropertyDescriptor.getFeature(object)).getName();
            if (attributeName.equals("constraint")) {
                final OCLConstraint oclConstraint = (OCLConstraint) object;
                oclConstraintExpression = oclConstraint.getConstraint();
                if (oclConstraintExpression == null) {
                    oclConstraintExpression = "";
                }

                result = new ExtendedDialogCellEditor(composite, getEditLabelProvider()) {

                    @Override
                    protected Object openDialogBox(final Control cellEditorWindow) {
                        final TitleAreaDialog d = new TitleAreaDialog(cellEditorWindow.getShell()) {

                            @Override
                            public void create() {
                                super.create();
                                setTitle("OCL Constraint Dialog");
                                setMessage("Type in an OCL Constraint", IMessageProvider.INFORMATION);
                            }

                            @Override
                            protected Control createDialogArea(final Composite parent) {

                                final Composite dialogArea = (Composite) super.createDialogArea(parent);
                                final Composite container = new Composite(dialogArea, SWT.NONE);
                                container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
                                final GridLayout layout = new GridLayout(1, true);
                                container.setLayout(layout);

                                final Injector injector = XtextConsolePlugin.getInstance()
                                        .getInjector(EssentialOCLPlugin.LANGUAGE_ID);
                                embeddedXtextEditor = new EmbeddedXtextEditor(container,
                                        injector, /*
                                                   * SWT. BORDER |
                                                   */
                                        SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
                                textWidget = embeddedXtextEditor.getViewer().getTextWidget();

                                textWidget.setWordWrap(true);
                                textWidget.setBounds(10, 10, 50, 50);
                                textWidget.setText(oclConstraintExpression);
                                // updateContext(org.palladiosimulator.pcm.system.SystemPackage.Literals.SYSTEM);
                                updateContext(
                                        org.modelversioning.emfprofileapplication.EMFProfileApplicationPackage.Literals.STEREOTYPE_APPLICATION);

                                textWidget.addModifyListener(new ModifyListener() {

                                    @Override
                                    public void modifyText(final ModifyEvent e) {

                                        modify();

                                    }
                                });
                                embeddedXtextEditor.getDocument().addModelListener(new IXtextModelListener() {
                                    @Override
                                    public void modelChanged(final XtextResource resource) {
                                        modify();
                                    }
                                });

                                return dialogArea;

                            }
                        };
                        d.setBlockOnOpen(true);
                        d.open();

                        if (d.getReturnCode() == Dialog.OK) {
                            return newOclConstraintExpression;
                        }

                        return null;

                    }

                };

            }

        }
        return result;

    }

    protected void updateContext(final EClassifier contextClassifier) {
        if (embeddedXtextEditor != null) {
            final BaseDocument editorDocument = (BaseDocument) embeddedXtextEditor.getDocument();
            editorDocument.modify(new IUnitOfWork<Object, XtextResource>() {
                @Override
                public Value exec(final XtextResource resource) throws Exception {
                    editorDocument.setContext(contextClassifier, null);
                    return null;
                }
            });
        }
    }

    protected void modify() {
        if (embeddedXtextEditor != null) {
            final String newValue = embeddedXtextEditor.getDocument().get();
            if (!newValue.equals(newOclConstraintExpression)) {
                newOclConstraintExpression = newValue;
            }
        }
    }

}
