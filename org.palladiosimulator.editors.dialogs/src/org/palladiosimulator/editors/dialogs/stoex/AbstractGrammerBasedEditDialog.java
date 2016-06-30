/**
 *
 */
package org.palladiosimulator.editors.dialogs.stoex;

import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.AnnotationModel;
import org.eclipse.jface.text.source.AnnotationRulerColumn;
import org.eclipse.jface.text.source.CompositeRuler;
import org.eclipse.jface.text.source.IAnnotationAccess;
import org.eclipse.jface.text.source.IAnnotationAccessExtension;
import org.eclipse.jface.text.source.ISharedTextColors;
import org.eclipse.jface.text.source.ImageUtilities;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.editors.text.EditorsPlugin;
import org.eclipse.ui.texteditor.AnnotationPreference;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;
import org.palladiosimulator.editors.dialogs.DialogsImages;
import org.palladiosimulator.editors.dialogs.SWTResourceManager;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.stochasticexpressions.parser.ErrorEntry;

import de.uka.ipd.sdq.errorhandling.IIssue;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractGrammerBasedEditDialog.
 *
 * @author Snowball
 * @author joerg henss
 */
public abstract class AbstractGrammerBasedEditDialog extends TitleAreaDialog {

    /** The dialog title. */
    private String DIALOG_TITLE = "Edit a stochastic expression";

    /** The Constant ERROR_TYPE. */
    public static final String ERROR_TYPE = "ERROR";

    /** The Constant ERROR_COLOR_PREF. */
    private static final String ERROR_COLOR_PREF = "errorIndicationColor";

    /** The Constant ERROR_HIGHLIGHT_PREF. */
    private static final String ERROR_HIGHLIGHT_PREF = "errorIndicationHighlighting";

    /** The Constant ERROR_TEXT_PREF. */
    private static final String ERROR_TEXT_PREF = "errorIndication";

    /** The Constant ERROR_TEXT_STYLE. */
    private static final String ERROR_TEXT_STYLE = "errorTextStyle";

    /** The Constant WARNING_TYPE. */
    public static final String WARNING_TYPE = "WARNING";

    /** The Constant WARNING_COLOR_PREF. */
    private static final String WARNING_COLOR_PREF = "warningIndicationColor";

    /** The Constant WARNING_HIGHLIGHT_PREF. */
    private static final String WARNING_HIGHLIGHT_PREF = "warningIndicationHighlighting";

    /** The Constant WARNING_TEXT_PREF. */
    private static final String WARNING_TEXT_PREF = "warningIndication";

    /** The Constant WARNING_TEXT_STYLE. */
    private static final String WARNING_TEXT_STYLE = "warningTextStyle";

    /** The Constant SHARED_COLORS. */
    private static final ISharedTextColors SHARED_COLORS = new GrammarSharedColors();

    // private Text editText;
    /** The text viewer. */
    private SourceViewer textViewer;

    /** The source viewer decoration support. */
    private SourceViewerDecorationSupport fSourceViewerDecorationSupport;

    /** The new text. */
    protected String newText = null;

    /** The annotation model. */
    private AnnotationModel fAnnotationModel;

    /** The result. */
    private Object result = null;

    /** The result text. */
    private String resultText = null;

    /** The context. */
    protected Parameter[] context = null;

    /** The annotation access. */
    private IAnnotationAccess fAnnotationAccess;

    /**
     * Constructor.
     *
     * @param parent
     *            shell
     */
    public AbstractGrammerBasedEditDialog(final Shell parent) {
        super(parent);
        newText = getInitialText();
        this.context = new Parameter[] {};
        // make the possible change dialogue size.
        this.setShellStyle(SWT.RESIZE | SWT.MAX);
    }

    /**
     * Constructor.
     *
     * @param parent
     *            shell
     * @param context
     *            - A list of parameters used in code completion
     */
    public AbstractGrammerBasedEditDialog(final Shell parent, final Parameter[] context) {
        super(parent);
        newText = getInitialText();
        this.context = context;
        // make the possible change dialogue size.
        setShellStyle(SWT.RESIZE | SWT.MAX);
        // activate help
        this.setHelpAvailable(true);
    }

    /**
     * Creates the source viewer.
     *
     * @param parent
     *            the parent
     * @param ruler
     *            the ruler
     * @param styles
     *            the styles
     * @return the source viewer
     */
    protected SourceViewer createSourceViewer(final Composite parent, final CompositeRuler ruler, final int styles) {

        final SourceViewer sourceViewer = new SourceViewer(parent, ruler, styles);
        return sourceViewer;
    }

    /**
     * Gets the initial text.
     *
     * @return the initial text
     */
    protected abstract String getInitialText();

    /**
     * Sets the display title.
     *
     * @param newTitle
     *            the new display title
     */
    public void setDisplayTitle(final String newTitle) {
        this.DIALOG_TITLE = newTitle;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.jface.dialogs.Dialog#cancelPressed()
     */
    @Override
    protected void cancelPressed() {
        super.cancelPressed();
        result = null;
        resultText = "";
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        final Composite area = (Composite) super.createDialogArea(parent);
        final Composite container = new Composite(area, SWT.NONE);
        container.setLayout(new FillLayout());
        container.setLayoutData(new GridData(GridData.FILL_BOTH));

        this.setTitle(DIALOG_TITLE);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "org.palladiosimulator.pcm.help.stoexdialog");

        fAnnotationAccess = new AnnotationMarkerAccess();

        final Group editStochasticExpressionGroup = new Group(container, SWT.NONE);
        editStochasticExpressionGroup.setText("");
        editStochasticExpressionGroup.setLayout(new FillLayout());

        fAnnotationModel = new AnnotationModel();
        final CompositeRuler fCompositeRuler = new CompositeRuler();
        final AnnotationRulerColumn annotationRuler = new AnnotationRulerColumn(fAnnotationModel, 16, fAnnotationAccess);
        fCompositeRuler.setModel(fAnnotationModel);
        // annotation ruler is decorating our composite ruler
        fCompositeRuler.addDecorator(0, annotationRuler);

        // add what types are show on the different rulers
        annotationRuler.addAnnotationType(ERROR_TYPE);
        annotationRuler.addAnnotationType(WARNING_TYPE);

        // create SourceViewer
        textViewer = createSourceViewer(editStochasticExpressionGroup, fCompositeRuler, SWT.V_SCROLL | SWT.MULTI
                | SWT.H_SCROLL | SWT.RESIZE);

        final StyledText styledText = textViewer.getTextWidget();
        styledText.setWordWrap(true);
        final AbstractGrammarBasedViewerConfiguration config = new AbstractGrammarBasedViewerConfiguration(
                fAnnotationModel, context, getLexerClass(), getTokenMapper());
        styledText.setFont(SWTResourceManager.getFont("Courier New", 12, SWT.NONE));

        styledText.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(final KeyEvent event) {
            }

            @Override
            public void keyReleased(final KeyEvent event) { // CTRL + SPACE
                if (event.keyCode == 32 && (event.stateMask & SWT.CTRL) == SWT.CTRL) {
                    config.getContentAssistant(textViewer).showPossibleCompletions();
                }
            }

        });

        styledText.addVerifyKeyListener(new VerifyKeyListener() {

            @Override
            public void verifyKey(final VerifyEvent event) {
                if (event.keyCode == 13) { // ENTER
                    final ContentAssistant ca = (ContentAssistant) config.getContentAssistant(textViewer);
                    final boolean isAssistentFocus = ca.setFocus(textViewer);
                    if (!isAssistentFocus) {
                        if (getButton(OK).isEnabled()) {
                            okPressed();
                        }

                        event.doit = false;
                    }

                }
            }

        });

        textViewer.configure(config);

        textViewer.setDocument(new Document(newText), fAnnotationModel);
        textViewer.addTextListener(new ITextListener() {

            @Override
            public void textChanged(final TextEvent event) {
                if (event.getDocumentEvent() != null) {
                    parseInputAndRefreshAnnotations();
                }
            }

        });

        // to paint the annotations use the ViewerDecorationSupport
        fSourceViewerDecorationSupport = new SourceViewerDecorationSupport(textViewer, null, fAnnotationAccess,
                SHARED_COLORS);

        AnnotationPreference ap = new AnnotationPreference();
        ap.setAnnotationType(ERROR_TYPE);
        ap.setColorPreferenceKey(ERROR_COLOR_PREF);
        ap.setHighlightPreferenceKey(ERROR_HIGHLIGHT_PREF);
        ap.setTextPreferenceKey(ERROR_TEXT_PREF);
        ap.setTextStylePreferenceKey(ERROR_TEXT_STYLE);
        fSourceViewerDecorationSupport.setAnnotationPreference(ap);

        ap = new AnnotationPreference();
        ap.setAnnotationType(WARNING_TYPE);
        ap.setColorPreferenceKey(WARNING_COLOR_PREF);
        ap.setHighlightPreferenceKey(WARNING_HIGHLIGHT_PREF);
        ap.setTextPreferenceKey(WARNING_TEXT_PREF);
        ap.setTextStylePreferenceKey(WARNING_TEXT_STYLE);
        fSourceViewerDecorationSupport.setAnnotationPreference(ap);

        fSourceViewerDecorationSupport.install(EditorsPlugin.getDefault().getPreferenceStore());

        // GridData layoutData = new GridData(GridData.FILL_BOTH);
        // new GridData(GridData.FILL_BOTH
        // | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
        // layoutData.heightHint = 300;
        // layoutData.widthHint = 450;

        // textViewer.getControl().setLayoutData(layoutData);
        // editText.setText(newText);
        return textViewer.getControl();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.eclipse.jface.dialogs.TitleAreaDialog#createContents(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createContents(final Composite parent) {
        final Control result = super.createContents(parent);
        parseInputAndRefreshAnnotations();
        return result;
    }

    /**
     * Gets the token mapper.
     *
     * @return the token mapper
     */
    protected abstract ITokenMapper getTokenMapper();

    /**
     * Gets the lexer class.
     *
     * @return the lexer class
     */
    protected abstract Class<?> getLexerClass();

    /**
     * Gets the title.
     *
     * @return the title
     */
    protected abstract String getTitle();

    /**
     * Parses the input and refresh annotations.
     */
    protected void parseInputAndRefreshAnnotations() {
        EObject value = null;
        fAnnotationModel.removeAllAnnotations();
        Lexer lexer = null;
        boolean hasErrors = false, hasWarnings = false;
        try {
            final String text = this.textViewer.getDocument().get();
            lexer = getLexer(text);
            value = parse(lexer);
        } catch (final RecognitionException e) {
            showInputInvalidInfo(e);
            return;
        } catch (final StoExParserException e) {
            for (final IIssue ex : e.getIssuesList()) {
                if (ex instanceof ErrorEntry) {
                    showInputInvalidInfo((ErrorEntry) ex);
                    hasErrors = true;
                } else {
                    hasWarnings = true;
                    showInputWarning(ex);
                }
            }
            if (hasErrors) {
                return;
            } else if (hasWarnings) {
                this.setMessage(e.getIssuesList().get(0).getMessage(), IMessageProvider.WARNING);
            }
        } catch (final Exception e) {
            showInputInvalidInfo(e);
            return;
        }
        this.getButton(IDialogConstants.OK_ID).setEnabled(true);
        if (!hasErrors) {
            this.setErrorMessage(null);
        }
        if (!hasWarnings) {
            this.setMessage(null);
        }
        result = value;
        resultText = this.textViewer.getDocument().get();
    }

    /**
     * Show input warning.
     *
     * @param ex
     *            the ex
     */
    private void showInputWarning(final IIssue ex) {
        fAnnotationModel.addAnnotation(new Annotation(WARNING_TYPE, false, ex.getMessage()), new Position(0, textViewer
                .getDocument().getLength()));
    }

    /**
     * Gets the lexer.
     *
     * @param text
     *            the text
     * @return the lexer
     */
    protected abstract Lexer getLexer(String text);

    /**
     * Parses the.
     *
     * @param lexer
     *            the lexer
     * @return the e object
     * @throws RecognitionException
     *             the recognition exception
     * @throws StoExParserException
     *             the sto ex parser exception
     */
    protected abstract EObject parse(Lexer lexer) throws RecognitionException, StoExParserException;

    // private void showInputInvalidInfo(TokenStreamException e,CharScanner scanner) {
    // result = null;
    // fAnnotationModel.addAnnotation(
    // new Annotation(ERROR_TYPE, false,
    // e.getMessage()),
    // new Position(ParserHelper.positionToOffset(textViewer.getDocument(), scanner.getLine(),
    // scanner.getColumn()),1));
    // this.getButton(IDialogConstants.OK_ID).setEnabled(false);
    // this.setErrorMessage("Entered stochastic expression is invalid. Cause given: "+e.getMessage());
    // }

    /**
     * Show input invalid info.
     *
     * @param e
     *            the e
     */
    private void showInputInvalidInfo(final Exception e) {
        result = null;
        fAnnotationModel.addAnnotation(new Annotation(ERROR_TYPE, false, e.getMessage() == null ? e.getClass()
                .getName() : e.getMessage()), guessPosition(e));
        this.getButton(IDialogConstants.OK_ID).setEnabled(false);
        this.setErrorMessage("Entered stochastic expression is invalid. Cause given: " + e.getLocalizedMessage());
    }

    /**
     * Show input invalid info.
     *
     * @param e
     *            the e
     */
    private void showInputInvalidInfo(final ErrorEntry e) {
        result = null;
        fAnnotationModel.addAnnotation(new Annotation(ERROR_TYPE, false, e.getMessage()), guessPosition(e.getEx()));
        this.getButton(IDialogConstants.OK_ID).setEnabled(false);
        this.setErrorMessage("Entered stochastic expression is invalid. Cause given: " + e.getMessage());
    }

    /**
     * Guess position.
     *
     * @param e
     *            the e
     * @return the position
     */
    private Position guessPosition(final Exception e) {
        if (e instanceof RecognitionException) {
            final RecognitionException recException = (RecognitionException) e;
            final int col = recException.charPositionInLine;
            final int line = recException.line;
            int offset = ParserHelper.positionToOffset(textViewer.getDocument(), line, col);
            offset = offset < 0 ? 0 : offset;
            return new Position(offset, textViewer.getDocument().getLength() - offset);
        }
        return new Position(0, textViewer.getDocument().getLength());
    }

    /**
     * Gets the result.
     *
     * @return the result
     */
    protected Object getResult() {
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(getTitle());
    }

    /**
     * Gets the result text.
     *
     * @return the result text
     */
    public String getResultText() {
        return resultText;
    }
}

/**
 * The Class GrammarSharedColors.
 */
class GrammarSharedColors implements ISharedTextColors {

    /** The color map. */
    Map<RGB, Color> colorMap = new HashMap<RGB, Color>();

    @Override
    public Color getColor(final RGB rgb) {
        if (colorMap.containsKey(rgb)) {
            return colorMap.get(rgb);
        }
        final Color c = new Color(Display.getDefault(), rgb);
        colorMap.put(rgb, c);
        return c;
    }

    @Override
    public void dispose() {
        colorMap.clear();

    }
}

/**
 * The Class AnnotationMarkerAccess.
 */
class AnnotationMarkerAccess implements IAnnotationAccess, IAnnotationAccessExtension {

    /**
     * Gets the type.
     *
     * @param annotation
     *            the annotation
     * @return the type
     * @see org.eclipse.jface.text.source.IAnnotationAccess#getType(org.eclipse.jface.text.source.Annotation)
     */
    @Override
    public String getType(final Annotation annotation) {
        return annotation.getType();
    }

    /**
     * Checks if is multi line.
     *
     * @param annotation
     *            the annotation
     * @return true, if is multi line
     * @see org.eclipse.jface.text.source.IAnnotationAccess#isMultiLine(org.eclipse.jface.text.source.Annotation)
     */
    @Override
    public boolean isMultiLine(final Annotation annotation) {
        return true;
    }

    /**
     * Checks if is temporary.
     *
     * @param annotation
     *            the annotation
     * @return true, if is temporary
     * @see org.eclipse.jface.text.source.IAnnotationAccess#isTemporary(org.eclipse.jface.text.source.Annotation)
     */
    @Override
    public boolean isTemporary(final Annotation annotation) {
        return !annotation.isPersistent();
    }

    /**
     * Gets the type label.
     *
     * @param annotation
     *            the annotation
     * @return the type label
     * @see org.eclipse.jface.text.source.IAnnotationAccessExtension#getTypeLabel(org.eclipse.jface.text.source.Annotation)
     */
    @Override
    public String getTypeLabel(final Annotation annotation) {
        if (getType(annotation).equals(AbstractGrammerBasedEditDialog.ERROR_TYPE)) {
            return "Errors";
        }
        if (getType(annotation).equals(AbstractGrammerBasedEditDialog.WARNING_TYPE)) {
            return "Warnings";
        }
        return null;
    }

    /**
     * Gets the layer.
     *
     * @param annotation
     *            the annotation
     * @return the layer
     * @see org.eclipse.jface.text.source.IAnnotationAccessExtension#getLayer(org.eclipse.jface.text.source.Annotation)
     */
    @Override
    public int getLayer(final Annotation annotation) {
        return 0;
    }

    /**
     * Paint.
     *
     * @param annotation
     *            the annotation
     * @param gc
     *            the gc
     * @param canvas
     *            the canvas
     * @param bounds
     *            the bounds
     * @see org.eclipse.jface.text.source.IAnnotationAccessExtension#paint(org.eclipse.jface.text.source.Annotation,
     *      org.eclipse.swt.graphics.GC, org.eclipse.swt.widgets.Canvas,
     *      org.eclipse.swt.graphics.Rectangle)
     */
    @Override
    public void paint(final Annotation annotation, final GC gc, final Canvas canvas, final Rectangle bounds) {
        if (getType(annotation).equals(AbstractGrammerBasedEditDialog.ERROR_TYPE)) {
            ImageUtilities.drawImage(DialogsImages.imageRegistry.get(DialogsImages.ERROR), gc, canvas, bounds,
                    SWT.CENTER);
        } else if (getType(annotation).equals(AbstractGrammerBasedEditDialog.WARNING_TYPE)) {
            ImageUtilities.drawImage(DialogsImages.imageRegistry.get(DialogsImages.WARNING), gc, canvas, bounds,
                    SWT.CENTER);
        }
    }

    /**
     * Checks if is paintable.
     *
     * @param annotation
     *            the annotation
     * @return true, if is paintable
     * @see org.eclipse.jface.text.source.IAnnotationAccessExtension#isPaintable(org.eclipse.jface.text.source.Annotation)
     */
    @Override
    public boolean isPaintable(final Annotation annotation) {
        return true;
    }

    /**
     * Checks if is subtype.
     *
     * @param annotationType
     *            the annotation type
     * @param potentialSupertype
     *            the potential supertype
     * @return true, if is subtype
     * @see org.eclipse.jface.text.source.IAnnotationAccessExtension#isSubtype(java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public boolean isSubtype(final Object annotationType, final Object potentialSupertype) {
        if (annotationType.equals(potentialSupertype)) {
            return true;
        }

        return false;
    }

    /**
     * Gets the supertypes.
     *
     * @param annotationType
     *            the annotation type
     * @return the supertypes
     * @see org.eclipse.jface.text.source.IAnnotationAccessExtension#getSupertypes(java.lang.Object)
     */
    @Override
    public Object[] getSupertypes(final Object annotationType) {
        return new Object[0];
    }

}
