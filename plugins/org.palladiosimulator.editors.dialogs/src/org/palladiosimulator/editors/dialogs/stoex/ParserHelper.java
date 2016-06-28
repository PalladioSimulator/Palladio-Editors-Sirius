package org.palladiosimulator.editors.dialogs.stoex;

import org.eclipse.jface.text.IDocument;

// TODO: Auto-generated Javadoc
/**
 * The Class ParserHelper.
 */
public class ParserHelper {
    
    /** The Constant EOL. */
    private static final String EOL = "\r\n";

    /**
     * Position to offset.
     *
     * @param document the document
     * @param line the line
     * @param column the column
     * @return the int
     */
    private static int positionToOffset(String document, int line, int column) {
        int offset = 0;
        String[] lines = document.split(EOL);
        for (int i = 0; i < line - 1; i++) {
            offset += lines[i].length() + EOL.length();
        }
        offset += column;
        return offset;
    }

    /**
     * Position to offset.
     *
     * @param document the document
     * @param line the line
     * @param column the column
     * @return the int
     */
    public static int positionToOffset(IDocument document, int line, int column) {
        return positionToOffset(document.get(), line, column);
    }
}
