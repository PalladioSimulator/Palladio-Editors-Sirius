/**
 * 
 */
package org.palladiosimulator.editors.dialogs.stoex;

import java.lang.reflect.InvocationTargetException;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.Lexer;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.ITokenScanner;

// TODO: Auto-generated Javadoc
/**
 * The Class ANTLRTokenScannerAdapter.
 * 
 * @author Steffen Becker
 */
public class ANTLRTokenScannerAdapter implements ITokenScanner {

    /** The last token length. */
    int lastTokenLength = 0;

    /** The token offset. */
    private int tokenOffset;

    /** The scanner class. */
    private Class<?> scannerClass;

    /** The scanner. */
    private Lexer scanner;

    /** The current input. */
    private String currentInput;

    /** The current document. */
    private IDocument currentDocument;

    /** The base offset. */
    private int baseOffset;

    /** The my mapper. */
    private ITokenMapper myMapper;

    /** The last token offset. */
    private int lastTokenOffset;

    /**
     * Instantiates a new aNTLR token scanner adapter.
     * 
     * @param scannerClass
     *            the scanner class
     * @param mapper
     *            the mapper
     */
    public ANTLRTokenScannerAdapter(Class<?> scannerClass, ITokenMapper mapper) {
        this.scannerClass = scannerClass;
        this.myMapper = mapper;
    }

    /**
     * Gets the token length.
     *
     * @return the token length
     * @see org.eclipse.jface.text.rules.ITokenScanner#getTokenLength()
     */
    public int getTokenLength() {
        return lastTokenLength;
    }

    /**
     * Gets the token offset.
     *
     * @return the token offset
     * @see org.eclipse.jface.text.rules.ITokenScanner#getTokenOffset()
     */
    public int getTokenOffset() {
        return tokenOffset;
    }

    /**
     * Next token.
     *
     * @return the i token
     * @see org.eclipse.jface.text.rules.ITokenScanner#nextToken()
     */
    public IToken nextToken() {
        tokenOffset = baseOffset + scanner.getCharIndex(); // Token starts at point where lexer is
        ANTLRTokenWrapper wrapper = new ANTLRTokenWrapper(scanner.nextToken(), myMapper);
        lastTokenLength = wrapper.getToken().getText() == null ? 0 : wrapper.getToken().getText().length();
        tokenOffset += (scanner.getCharIndex() + baseOffset) - tokenOffset - lastTokenLength; // Correct
                                                                                              // the
                                                                                              // position
                                                                                              // in
                                                                                              // case
                                                                                              // of
                                                                                              // recognition
                                                                                              // exceptions

        wrapper.setIsWhitespace(wrapper.getToken().getChannel() == BaseRecognizer.HIDDEN); // Token
                                                                                           // is a
                                                                                           // Whitespace
        return wrapper;
    }

    /**
     * Sets the range.
     *
     * @param document the document
     * @param offset the offset
     * @param length the length
     * @see org.eclipse.jface.text.rules.ITokenScanner#setRange(org.eclipse.jface.text.IDocument,
     * int, int)
     */
    public void setRange(IDocument document, int offset, int length) {
        currentInput = "";
        currentDocument = document;
        try {
            currentInput = document.get(offset, length);
            lastTokenOffset = 0;
            tokenOffset = offset;
            baseOffset = offset;
        } catch (BadLocationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        ANTLRStringStream reader = new ANTLRStringStream(currentInput);
        try {
            scanner = (Lexer) scannerClass.getConstructor(CharStream.class).newInstance(new Object[] { reader });
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
