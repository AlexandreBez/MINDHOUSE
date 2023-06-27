package com.businessIntelligenceServer.objects;

import java.math.BigInteger;

/**
 * The UsersQtdRolesData class represents data for user role quantities.
 */
public class UsersQtdRolesData {
    
    private String role;
    private BigInteger qtd;
    
    /**
     * Constructs an empty UsersQtdRolesData object.
     */
    public UsersQtdRolesData() {}
    
    /**
     * Constructs a UsersQtdRolesData object with the specified role and quantity.
     *
     * @param role the role name
     * @param qtd  the quantity associated with the role
     */
    public UsersQtdRolesData(String role, BigInteger qtd) {
        this.role = role;
        this.qtd = qtd;
    }
    
    /**
     * Retrieves the role name.
     *
     * @return the role name
     */
    public String getRole() {
        return role;
    }
    
    /**
     * Sets the role name.
     *
     * @param role the role name to set
     */
    public void setRole(String role) {
        this.role = role;
    }
    
    /**
     * Retrieves the quantity associated with the role.
     *
     * @return the quantity
     */
    public BigInteger getQtd() {
        return qtd;
    }
    
    /**
     * Sets the quantity associated with the role.
     *
     * @param qtd the quantity to set
     */
    public void setQtd(BigInteger qtd) {
        this.qtd = qtd;
    }
}
