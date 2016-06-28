/**
 * 
 */
package org.palladiosimulator.editors.dialogs.stoex;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import org.palladiosimulator.pcm.repository.Parameter;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractGrammarBasedViewerConfiguration.
 * 
 * @author Snowball
 */
public class AbstractGrammarBasedViewerConfiguration extends SourceViewerConfiguration {

    /** The annotation model. */
    private IAnnotationModel annotationModel;

    /** The my lexer class. */
    private Class myLexerClass;

    /** The my mapper. */
    private ITokenMapper myMapper;

    /** The context. */
    private Parameter[] context = null;

    /** The my assistant. */
    private ContentAssistant myAssistant;

    /**
     * Instantiates a new abstract grammar based viewer configuration.
     * 
     * @param annotationModel
     *            the annotation model
     * @param context
     *            the context
     * @param lexerClass
     *            the lexer class
     * @param myMapper
     *            the my mapper
     */
    public AbstractGrammarBasedViewerConfiguration(IAnnotationModel annotationModel, Parameter[] context,
            Class lexerClass, ITokenMapper myMapper) {
        this.annotationModel = annotationModel;
        this.myLexerClass = lexerClass;
        this.myMapper = myMapper;
        this.context = context;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.text.source.SourceViewerConfiguration#getPresentationReconciler(org.eclipse
     * .jface.text.source.ISourceViewer)
     */
    @Override
    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
        PresentationReconciler reconciler = new PresentationReconciler();

        DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getKeywordScanner());
        reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

        return reconciler;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.text.source.SourceViewerConfiguration#getAnnotationHover(org.eclipse.jface
     * .text.source.ISourceViewer)
     */
    @Override
    public IAnnotationHover getAnnotationHover(ISourceViewer sourceViewer) {
        return new AnnotationHover(annotationModel);
    }

    /**
     * Gets the keyword scanner.
     * 
     * @return the keyword scanner
     */
    private ITokenScanner getKeywordScanner() {
        return new ANTLRTokenScannerAdapter(myLexerClass, myMapper);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.text.source.SourceViewerConfiguration#getContentAssistant(org.eclipse.jface
     * .text.source.ISourceViewer)
     */
    @Override
    public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
        if (myAssistant == null) {
            myAssistant = new ContentAssistant();
            myAssistant
                    .setContentAssistProcessor(new StoExCompletionProcessor(context), IDocument.DEFAULT_CONTENT_TYPE);
            myAssistant.setAutoActivationDelay(1);
            myAssistant.enableAutoActivation(true);
        }
        return myAssistant;
    }
}

/**
 * annotation hover manager.
 * 
 */
class AnnotationHover implements IAnnotationHover, ITextHover {

    /** The annotation model. */
    private IAnnotationModel fAnnotationModel;

    /**
     * Instantiates a new annotation hover.
     * 
     * @param annotationModel
     *            the annotation model
     */
    public AnnotationHover(IAnnotationModel annotationModel) {
        this.fAnnotationModel = annotationModel;
    }

    /**
     * Gets the hover info.
     * 
     * @param sourceViewer
     *            the source viewer
     * @param lineNumber
     *            the line number
     * @return the hover info
     * @see org.eclipse.jface.text.source.IAnnotationHover#getHoverInfo(org.eclipse.jface.text.source.ISourceViewer,
     *      int)
     */
    public String getHoverInfo(ISourceViewer sourceViewer, int lineNumber) {
        Iterator ite = fAnnotationModel.getAnnotationIterator();

        ArrayList all = new ArrayList();

        while (ite.hasNext()) {
            Annotation a = (Annotation) ite.next();
            if (a instanceof Annotation) {
                all.add(a.getText());
            }
        }

        StringBuffer total = new StringBuffer();
        for (int x = 0; x < all.size(); x++) {
            String str = (String) all.get(x);
            total.append(" " + str + (x == (all.size() - 1) ? "" : "\n"));
        }

        return total.toString();
    }

    /**
     * Gets the hover info.
     * 
     * @param textViewer
     *            the text viewer
     * @param hoverRegion
     *            the hover region
     * @return the hover info
     * @see org.eclipse.jface.text.ITextHover#getHoverInfo(org.eclipse.jface.text.ITextViewer,
     *      org.eclipse.jface.text.IRegion)
     */
    public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) {
        return null;
    }

    /**
     * Gets the hover region.
     * 
     * @param textViewer
     *            the text viewer
     * @param offset
     *            the offset
     * @return the hover region
     * @see org.eclipse.jface.text.ITextHover#getHoverRegion(org.eclipse.jface.text.ITextViewer,
     *      int)
     */
    public IRegion getHoverRegion(ITextViewer textViewer, int offset) {
        return null;
    }
}