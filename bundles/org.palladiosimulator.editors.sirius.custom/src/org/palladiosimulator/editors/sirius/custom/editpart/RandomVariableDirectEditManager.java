package org.palladiosimulator.editors.sirius.custom.editpart;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.yakindu.base.xtext.utils.gmf.directedit.IXtextAwareEditPart;
import org.yakindu.base.xtext.utils.gmf.directedit.XtextDirectEditManager;

import com.google.inject.Injector;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class RandomVariableDirectEditManager extends XtextDirectEditManager {

    private Injector injector;
    private int style;
    private TypeEnum expectedType;

    public RandomVariableDirectEditManager(IXtextAwareEditPart source, Injector injector, int style,
            TypeEnum expectedType) {
        super(source, injector, style);
        this.injector = injector;
        this.style = style;
        this.expectedType = expectedType;
    }

    @Override
    protected CellEditor createCellEditorOn(Composite composite) {

        Composite parent = new Composite(composite, SWT.None);
        FillLayout fillLayout = new FillLayout();
        fillLayout.marginWidth = 10;
        parent.setLayout(fillLayout);

        RandomVariableXtextStyledTextCellEditorEx editor;
        editor = new RandomVariableXtextStyledTextCellEditorEx(style, injector, getRandomVariable(), expectedType);

        editor.create(composite);

        return editor;
    }

    protected Optional<RandomVariable> getRandomVariable() {
        return Optional.ofNullable(getEditPart())
            .map(EditPart::getModel)
            .filter(Node.class::isInstance)
            .map(Node.class::cast)
            .map(Node::getElement)
            .filter(DRepresentationElement.class::isInstance)
            .map(DRepresentationElement.class::cast)
            .map(DRepresentationElement::getSemanticElements)
            .map(c -> (Collection<EObject>) c)
            .orElse(Collections.emptyList())
            .stream()
            .map(contextElement -> {
                if (contextElement instanceof RandomVariable) {
                    return (RandomVariable) contextElement;
                }
                for (TreeIterator<EObject> iter = contextElement.eAllContents(); iter.hasNext();) {
                    EObject current = iter.next();
                    if (current instanceof RandomVariable) {
                        return (RandomVariable) current;
                    }
                }
                return null;
            })
            .filter(Objects::nonNull)
            .findFirst();
    }

}
