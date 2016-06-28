/**
 * 
 */
package org.palladiosimulator.editors.dialogs.stoex;

import org.antlr.runtime.Token;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.palladiosimulator.pcm.stochasticexpressions.parser.PCMStoExLexer;

// TODO: Auto-generated Javadoc
/**
 * The Class StoExTokenMapper.
 * 
 * @author Steffen Becker
 */
public class StoExTokenMapper implements ITokenMapper {

    /**
     * Instantiates a new sto ex token mapper.
     */
    public StoExTokenMapper() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Map color.
     * 
     * @param t
     *            the t
     * @return the object
     * @see de.uka.ipd.sdq.dialogs.selection.IColorMapper#mapColor(antlr.Token)
     */
    public Object mapColor(Token t) {
        switch (t.getType()) {
        case PCMStoExLexer.EQUAL:
        case PCMStoExLexer.PLUS:
        case PCMStoExLexer.MINUS:
        case PCMStoExLexer.MUL:
        case PCMStoExLexer.DIV:
        case PCMStoExLexer.POW:
        case PCMStoExLexer.LPAREN:
        case PCMStoExLexer.RPAREN:
        case PCMStoExLexer.AND:
        case PCMStoExLexer.OR:
        case PCMStoExLexer.XOR:
        case PCMStoExLexer.NOT:
            return new TextAttribute(new Color(null, new RGB(0, 0, 0)), null, SWT.BOLD);
        case PCMStoExLexer.DOUBLEPDF:
        case PCMStoExLexer.INTPMF:
        case PCMStoExLexer.ENUMPMF:
        case PCMStoExLexer.BOOLPMF:
        case PCMStoExLexer.DOUBLEPMF:
            return new TextAttribute(new Color(null, new RGB(255, 0, 0)), null, SWT.BOLD);
        case PCMStoExLexer.NUMBER:
        case PCMStoExLexer.STRING_LITERAL:
            return new TextAttribute(new Color(null, new RGB(0, 0, 255)), null, SWT.BOLD);
        case PCMStoExLexer.BYTESIZE:
        case PCMStoExLexer.NUMBER_OF_ELEMENTS:
        case PCMStoExLexer.STRUCTURE:
        case PCMStoExLexer.VALUE:
        case PCMStoExLexer.TYPE:
        case PCMStoExLexer.INNER:
        case PCMStoExLexer.FALSE:
        case PCMStoExLexer.TRUE:
            return new TextAttribute(new Color(null, new RGB(255, 0, 255)), null, SWT.ITALIC);
        case PCMStoExLexer.ID:
            return new TextAttribute(new Color(null, new RGB(255, 0, 255)), null, SWT.NONE);
        case PCMStoExLexer.LINE_COMMENT:
        case PCMStoExLexer.COMMENT:
            return new TextAttribute(new Color(null, new RGB(190, 190, 190)), null, SWT.NONE);
        default:
            return null;
        }
    }

}
