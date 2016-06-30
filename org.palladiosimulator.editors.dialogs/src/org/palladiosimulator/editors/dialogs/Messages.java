package org.palladiosimulator.editors.dialogs;

import org.eclipse.osgi.util.NLS;

// TODO: Auto-generated Javadoc
/**
 * Common class for all message bundle classes.
 * 
 * @author Roman Andrej
 */
public class Messages extends NLS {

    /**
     * Initialize the class with the values from the specified message bundle.
     */
    static {
        NLS.initializeMessages("messages", Messages.class); //$NON-NLS-1$
    }

    /** Define the Title,Message and ErrorMassage of DataTypeDialog. */
    public static String DataTypeDialog_NewTitle;
    
    /** The Data type dialog_ edit title. */
    public static String DataTypeDialog_EditTitle;
    
    /** The Data type dialog_ shell new title. */
    public static String DataTypeDialog_ShellNewTitle;
    
    /** The Data type dialog_ shell edit title. */
    public static String DataTypeDialog_ShellEditTitle;
    
    /** The Data type dialog_ error msg name. */
    public static String DataTypeDialog_ErrorMsgName;
    
    /** The Data type dialog_ error msg inner. */
    public static String DataTypeDialog_ErrorMsgInner;
    
    /** The Data type dialog_ error msg inner name. */
    public static String DataTypeDialog_ErrorMsgInnerName;
    
    /** The Data type dialog_ error msg inner type. */
    public static String DataTypeDialog_ErrorMsgInnerType;

    /** Define the Title,Message and ErrorMassage of ParametersDialog. */
    public static String ParametersDialog_Title;
    
    /** The Parameters dialog_ error msg inner. */
    public static String ParametersDialog_ErrorMsgInner;
}
