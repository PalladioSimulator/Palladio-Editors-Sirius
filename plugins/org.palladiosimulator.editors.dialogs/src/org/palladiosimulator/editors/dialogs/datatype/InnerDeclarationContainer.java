package org.palladiosimulator.editors.dialogs.datatype;

import org.palladiosimulator.pcm.repository.impl.InnerDeclarationImpl;

// TODO: Auto-generated Javadoc
/**
 * Container class for InnerDeclaration with parent object.
 *
 * @author Roman Andrej
 */
public class InnerDeclarationContainer extends InnerDeclarationImpl {

    /** The parent. */
    private Object parent;
    
    /** The object. */
    private Object object;

    /**
     * Instantiates a new inner declaration container.
     *
     * @param object the object
     * @param parent the parent
     */
    public InnerDeclarationContainer(Object object, Object parent) {
        this.parent = parent;
        this.object = object;
    }

    /**
     * Gets the parent.
     *
     * @return the parent
     */
    public Object getParent() {
        return parent;
    }

    /**
     * Sets the parent.
     *
     * @param parent the new parent
     */
    public void setParent(Object parent) {
        this.parent = parent;
    }

    /**
     * Gets the object.
     *
     * @return the object
     */
    public Object getObject() {
        return object;
    }

    /**
     * Sets the object.
     *
     * @param object the new object
     */
    public void setObject(Object object) {
        this.object = object;
    }
}
