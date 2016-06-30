package org.palladiosimulator.editors.dialogs.stoex;

import org.antlr.runtime.Token;
import org.eclipse.jface.text.rules.IToken;

// TODO: Auto-generated Javadoc
/**
 * The Class ANTLRTokenWrapper.
 */
public class ANTLRTokenWrapper implements IToken {

    /** The token. */
    private Token token;

    /** The is undefined. */
    private boolean isUndefined;

    /** The mapper. */
    private ITokenMapper mapper;

    /** The is whitespace. */
    private boolean isWhitespace;

    /**
     * Instantiates a new aNTLR token wrapper.
     * 
     * @param t
     *            the t
     * @param myMapper
     *            the my mapper
     */
    public ANTLRTokenWrapper(Token t, ITokenMapper myMapper) {
        this.token = t;
        this.isUndefined = false;
        this.isWhitespace = false;
        this.mapper = myMapper;
    }

    /**
     * Gets the data.
     *
     * @return the data
     * @see org.eclipse.jface.text.rules.IToken#getData()
     */
    public Object getData() {
        if (mapper == null) {
            return null;
        }
        return mapper.mapColor(token);
    }

    /**
     * Checks if is eof.
     *
     * @return true, if is eof
     * @see org.eclipse.jface.text.rules.IToken#isEOF()
     */
    public boolean isEOF() {
        if (token == null) {
            return false;
        }
        return token == Token.EOF_TOKEN;
    }

    /**
     * Checks if is other.
     * 
     * @return true, if is other
     * @see org.eclipse.jface.text.rules.IToken#isOther()
     */
    public boolean isOther() {
        return false;
    }

    /**
     * Checks if is undefined.
     * 
     * @return true, if is undefined
     * @see org.eclipse.jface.text.rules.IToken#isUndefined()
     */
    public boolean isUndefined() {
        return isUndefined;
    }

    /**
     * Checks if is whitespace.
     * 
     * @return true, if is whitespace
     * @see org.eclipse.jface.text.rules.IToken#isWhitespace()
     */
    public boolean isWhitespace() {
        return isWhitespace;
    }

    /**
     * Sets the checks if is whitespace.
     * 
     * @param isWhitespace
     *            the new checks if is whitespace
     */
    public void setIsWhitespace(boolean isWhitespace) {
        this.isWhitespace = isWhitespace;
    }

    /**
     * Gets the token.
     * 
     * @return the token
     */
    public Token getToken() {
        return token;
    }
}
