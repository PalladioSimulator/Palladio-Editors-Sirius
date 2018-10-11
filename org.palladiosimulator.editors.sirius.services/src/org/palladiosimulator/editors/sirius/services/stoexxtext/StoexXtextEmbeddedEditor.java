package org.palladiosimulator.editors.sirius.services.stoexxtext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.IResourceFactory;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorModelAccess;
import org.obeonetwork.dsl.viewpoint.xtext.support.XtextEmbeddedEditor;

import com.google.inject.Injector;

import de.uka.ipd.sdq.errorhandling.IIssue;
import de.uka.ipd.sdq.stoex.Expression;
import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.exceptions.ExpectedTypeMismatchIssue;
import de.uka.ipd.sdq.stoex.analyser.visitors.ExpressionInferTypeVisitor;
import de.uka.ipd.sdq.stoex.analyser.visitors.NonProbabilisticExpressionInferTypeVisitor;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeCheckVisitor;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class StoexXtextEmbeddedEditor extends XtextEmbeddedEditor {
	
	private TypeEnum expectedType;

	public StoexXtextEmbeddedEditor(IGraphicalEditPart editPart, Injector xtextInjector, TypeEnum expectedType) {
		super(editPart, xtextInjector);
		this.expectedType = expectedType;
	}

	protected XtextResource createVirtualXtextResource(URI uri, EObject semanticElement) throws IOException {
		IResourceFactory resourceFactory = xtextInjector.getInstance(IResourceFactory.class);
		// TODO use the syntheticscheme.
		XtextResourceSet rs = xtextInjector.getInstance(XtextResourceSet.class);
		rs.setClasspathURIContext(getClass()); // Create virtual resource
		XtextResource xtextVirtualResource = (XtextResource) resourceFactory
				.createResource(URI.createURI(uri.toString()));
		rs.getResources().add(xtextVirtualResource);

		// Populate virtual resource with the given semantic element to edit
		xtextVirtualResource.getContents().add((EObject) ((RandomVariable) semanticElement).getExpression());

		// Save and reparse in order to initialize virtual Xtext resource
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		xtextVirtualResource.save(out, Collections.emptyMap());
		xtextVirtualResource.reparse(new String(out.toByteArray()));

		return xtextVirtualResource;
	}
	
	protected void updateXtextResource() throws IOException, BadLocationException {
		String newText = xtextPartialEditor.getSerializedModel();
		xtextResource.reparse(newText);
		EcoreUtil.resolveAll(xtextResource);
		final IParseResult parseResult = xtextResource.getParseResult();
		final NonProbabilisticExpressionInferTypeVisitor typeVisitor = new NonProbabilisticExpressionInferTypeVisitor();
		final Expression expression = (Expression) xtextResource.getContents().get(0);
		if (!parseResult.hasSyntaxErrors() && !hasDanglingRefs(xtextResource) 
				&& assertType(expression, typeVisitor, expectedType).size() == 0) {
			reconcile(originalResource, xtextResource);
		}
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
    private Collection<? extends IIssue> assertType(final Expression result, final ExpressionInferTypeVisitor typeVisitor,
            final TypeEnum expectedType) {
        if (!TypeCheckVisitor.typesCompatible(expectedType, typeVisitor.getType(result))) {
            return Collections.singletonList(
                    new ExpectedTypeMismatchIssue(expectedType, typeVisitor.getType(result)));
        }
        return Collections.emptyList();
    }

	@Override
	protected void reconcile(Resource resourceInSirius, XtextResource resourceInEmbeddedEditor) {
		EObject randomVariableInSirius = resourceInSirius.getEObject(semanticElementFragment);

		// replace specification
		String newSpecification = resourceInEmbeddedEditor.getParseResult().getRootNode().getText();

		final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(originalResource);
		editingDomain.getCommandStack()
				.execute(new RecordingCommand(editingDomain, "update resource after direct text edit") {

					@Override
					protected void doExecute() {
						((RandomVariable) randomVariableInSirius).setSpecification(newSpecification);
					}
				});
	}

	@Override
	protected void createXtextEditor() throws Exception {
		EObject semanticElementInDocument = xtextResource.getContents().get(0);
		ICompositeNode rootNode = xtextResource.getParseResult().getRootNode();
		String allText = rootNode.getText();
		ICompositeNode elementNode = NodeModelUtils.findActualNodeFor(semanticElementInDocument);
		String prefix = "";
		String editablePart = allText.substring(elementNode.getOffset(), elementNode.getEndOffset());
		String suffix = "";

		createXtextEditor(semanticElementInDocument, prefix, editablePart, suffix);
	}

}