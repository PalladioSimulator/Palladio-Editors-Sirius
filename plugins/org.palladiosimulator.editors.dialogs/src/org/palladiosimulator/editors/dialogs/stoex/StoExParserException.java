package org.palladiosimulator.editors.dialogs.stoex;

import java.util.ArrayList;

import de.uka.ipd.sdq.errorhandling.IIssue;

// TODO: Auto-generated Javadoc
/**
 * The Class StoExParserException.
 */
public class StoExParserException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7815748573923222680L;

    /** The error list. */
    private ArrayList<IIssue> errorList;

    /**
     * Instantiates a new sto ex parser exception.
     *
     * @param errorList2 the error list2
     */
    public StoExParserException(ArrayList<IIssue> errorList2) {
        this.errorList = errorList2;
    }

    /**
     * Gets the issues list.
     *
     * @return the issues list
     */
    public ArrayList<IIssue> getIssuesList() {
        return errorList;
    }

}
