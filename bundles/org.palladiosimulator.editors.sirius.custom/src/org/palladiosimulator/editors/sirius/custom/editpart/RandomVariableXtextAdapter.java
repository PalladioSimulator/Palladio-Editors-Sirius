package org.palladiosimulator.editors.sirius.custom.editpart;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.Issue;
import org.yakindu.base.xtext.utils.jface.viewers.StyledTextXtextAdapter;
import org.yakindu.base.xtext.utils.jface.viewers.context.IXtextFakeContextResourcesProvider;

import com.google.inject.Injector;

import de.uka.ipd.sdq.errorhandling.IIssue;
import de.uka.ipd.sdq.stoex.Expression;
import de.uka.ipd.sdq.stoex.analyser.exceptions.ExpectedTypeMismatchIssue;
import de.uka.ipd.sdq.stoex.analyser.visitors.ExpressionInferTypeVisitor;
import de.uka.ipd.sdq.stoex.analyser.visitors.NonProbabilisticExpressionInferTypeVisitor;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeCheckVisitor;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class RandomVariableXtextAdapter extends StyledTextXtextAdapter {

	private TypeEnum expectedType;

	public RandomVariableXtextAdapter(Injector injector, IXtextFakeContextResourcesProvider contextFakeResourceProvider,
			TypeEnum expectedType) {
		super(injector, contextFakeResourceProvider);
		this.expectedType = expectedType;
	}

	@Override
	public List<Issue> getXtextValidationIssues() {
		List<Issue> issues = getValidationJob().createIssues(new NullProgressMonitor());
		String warningText = "";
		for (Issue issue : issues) {
			warningText = warningText + issue.getMessage() + "\n";
		}

		final NonProbabilisticExpressionInferTypeVisitor typeVisitor = new NonProbabilisticExpressionInferTypeVisitor();
		EObject resultType = getXtextParseResult().getRootASTElement();
		typeVisitor.doSwitch(resultType);
		Collection<? extends IIssue> typeIssues = assertType(resultType, typeVisitor, expectedType);

		for (IIssue typeIssue : typeIssues) {
			warningText = warningText + typeIssue.getMessage() + "\n";
		}

		getDecoration().setDescriptionText(warningText);
		return issues;
	}

	/**
	 * Assert type.
	 * 
	 * @param result
	 *            the result
	 * @param typeVisitor
	 *            the type visitor
	 * @param expectedType
	 *            the expected type
	 * @return the collection<? extends i issue>
	 */
	private Collection<? extends IIssue> assertType(final EObject result, final ExpressionInferTypeVisitor typeVisitor,
			final TypeEnum expectedType) {
		if (!TypeCheckVisitor.typesCompatible(expectedType, typeVisitor.getType((Expression) result))) {
			return Collections.singletonList(
					new ExpectedTypeMismatchIssue(expectedType, typeVisitor.getType((Expression) result)));
		}
		return Collections.emptyList();
	}

}
