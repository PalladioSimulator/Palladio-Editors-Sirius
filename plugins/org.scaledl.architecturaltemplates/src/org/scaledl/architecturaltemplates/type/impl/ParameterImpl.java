/**
 */
package org.scaledl.architecturaltemplates.type.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.scaledl.architecturaltemplates.type.Parameter;
import org.scaledl.architecturaltemplates.type.Role;
import org.scaledl.architecturaltemplates.type.TypePackage;

import de.uka.ipd.sdq.pcm.core.entity.impl.EntityImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Parameter</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.type.impl.ParameterImpl#getRole <em>Role</em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.type.impl.ParameterImpl#getDataType <em>Data Type
 * </em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ParameterImpl extends EntityImpl implements Parameter {

    /**
     * The cached value of the '{@link #getDataType() <em>Data Type</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDataType()
     * @generated
     * @ordered
     */
    protected EDataType dataType;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ParameterImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TypePackage.Literals.PARAMETER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Role getRole() {
        if (this.eContainerFeatureID() != TypePackage.PARAMETER__ROLE) {
            return null;
        }
        return (Role) this.eInternalContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetRole(final Role newRole, NotificationChain msgs) {
        msgs = this.eBasicSetContainer((InternalEObject) newRole, TypePackage.PARAMETER__ROLE, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setRole(final Role newRole) {
        if (newRole != this.eInternalContainer()
                || (this.eContainerFeatureID() != TypePackage.PARAMETER__ROLE && newRole != null)) {
            if (EcoreUtil.isAncestor(this, newRole)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + this.toString());
            }
            NotificationChain msgs = null;
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            if (newRole != null) {
                msgs = ((InternalEObject) newRole).eInverseAdd(this, TypePackage.ROLE__PARAMETERS, Role.class, msgs);
            }
            msgs = this.basicSetRole(newRole, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, TypePackage.PARAMETER__ROLE, newRole, newRole));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EDataType getDataType() {
        if (this.dataType != null && this.dataType.eIsProxy()) {
            final InternalEObject oldDataType = (InternalEObject) this.dataType;
            this.dataType = (EDataType) this.eResolveProxy(oldDataType);
            if (this.dataType != oldDataType) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE, TypePackage.PARAMETER__DATA_TYPE,
                            oldDataType, this.dataType));
                }
            }
        }
        return this.dataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EDataType basicGetDataType() {
        return this.dataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDataType(final EDataType newDataType) {
        final EDataType oldDataType = this.dataType;
        this.dataType = newDataType;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, TypePackage.PARAMETER__DATA_TYPE, oldDataType,
                    this.dataType));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TypePackage.PARAMETER__ROLE:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetRole((Role) otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(final InternalEObject otherEnd, final int featureID,
            final NotificationChain msgs) {
        switch (featureID) {
        case TypePackage.PARAMETER__ROLE:
            return this.basicSetRole(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(final NotificationChain msgs) {
        switch (this.eContainerFeatureID()) {
        case TypePackage.PARAMETER__ROLE:
            return this.eInternalContainer().eInverseRemove(this, TypePackage.ROLE__PARAMETERS, Role.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case TypePackage.PARAMETER__ROLE:
            return this.getRole();
        case TypePackage.PARAMETER__DATA_TYPE:
            if (resolve) {
                return this.getDataType();
            }
            return this.basicGetDataType();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID) {
        case TypePackage.PARAMETER__ROLE:
            this.setRole((Role) newValue);
            return;
        case TypePackage.PARAMETER__DATA_TYPE:
            this.setDataType((EDataType) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eUnset(final int featureID) {
        switch (featureID) {
        case TypePackage.PARAMETER__ROLE:
            this.setRole((Role) null);
            return;
        case TypePackage.PARAMETER__DATA_TYPE:
            this.setDataType((EDataType) null);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean eIsSet(final int featureID) {
        switch (featureID) {
        case TypePackage.PARAMETER__ROLE:
            return this.getRole() != null;
        case TypePackage.PARAMETER__DATA_TYPE:
            return this.dataType != null;
        }
        return super.eIsSet(featureID);
    }

} // ParameterImpl
