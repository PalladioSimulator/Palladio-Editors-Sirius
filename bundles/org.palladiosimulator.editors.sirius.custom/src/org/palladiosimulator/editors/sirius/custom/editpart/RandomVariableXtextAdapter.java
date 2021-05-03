package org.palladiosimulator.editors.sirius.custom.editpart;

import java.util.List;
import java.util.Optional;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.xtext.validation.Issue;
import org.palladiosimulator.editors.commons.dialogs.stoex.StoExContextProvidingAdapter;
import org.palladiosimulator.editors.commons.dialogs.stoex.StoxContextProvidingAdapterFactory;
import org.yakindu.base.xtext.utils.jface.viewers.StyledTextXtextAdapter;
import org.yakindu.base.xtext.utils.jface.viewers.context.IXtextFakeContextResourcesProvider;
import org.yakindu.base.xtext.utils.jface.viewers.context.XtextFakeResourceContext;

import com.google.inject.Injector;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class RandomVariableXtextAdapter extends StyledTextXtextAdapter {

    private final TypeEnum expectedType;
    private final Optional<RandomVariable> randomVariable;

    public RandomVariableXtextAdapter(Injector injector, IXtextFakeContextResourcesProvider contextFakeResourceProvider,
            Optional<RandomVariable> randomVariable, TypeEnum expectedType) {
        super(injector, contextFakeResourceProvider);
        this.randomVariable = randomVariable;
        this.expectedType = expectedType;
    }

    @Override
    protected void initXtextDocument(XtextFakeResourceContext context) {
        super.initXtextDocument(context);
        registerContext();
    }

    @Override
    public List<Issue> getXtextValidationIssues() {
        List<Issue> issues = getValidationJob().createIssues(new NullProgressMonitor());
        String warningText = "";
        for (Issue issue : issues) {
            warningText = warningText + issue.getMessage() + "\n";
        }
        getDecoration().setDescriptionText(warningText);
        return issues;
    }

    protected void registerContext() {
        StoExContextProvidingAdapter adapter = StoxContextProvidingAdapterFactory.create();
        randomVariable.ifPresent(adapter::setStoexContainer);
        adapter.setExpectedType(expectedType);
        getXtextDocument().readOnly(r -> {
            return r.eAdapters()
                .add(adapter);
        });
    }
}
