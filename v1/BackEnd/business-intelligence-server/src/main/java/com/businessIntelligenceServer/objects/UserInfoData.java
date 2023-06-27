package com.businessIntelligenceServer.objects;

/**
 * The UserInfoData class represents user information data.
 */
public class UserInfoData {

    private String name;
    private String email;
    private String role;
    private String creation_date;

    /**
     * Constructs an empty UserInfoData object.
     */
    public UserInfoData() {
    }

    /**
     * Constructs a UserInfoData object with the specified name, email, role, and creation date.
     *
     * @param name          the name of the user
     * @param email         the email address of the user
     * @param role          the role of the user
     * @param creation_date the creation date of the user
     */
    public UserInfoData(String name, String email, String role, String creation_date) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.creation_date = creation_date;
    }

    /**
     * Retrieves the name of the user.
     *
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the user's name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the email address of the user.
     *
     * @return the user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email the user's email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the role of the user.
     *
     * @return the user's role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role the user's role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Retrieves the creation date of the user.
     *
     * @return the user's creation date
     */
    public String getCreation_date() {
        return creation_date;
    }

    /**
     * Sets the creation date of the user.
     *
     * @param creation_date the user's creation date to set
     */
    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }
}
