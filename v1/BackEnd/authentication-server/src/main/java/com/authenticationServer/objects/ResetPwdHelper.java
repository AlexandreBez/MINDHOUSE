package com.authenticationServer.objects;

/**
 * The ResetPwdHelper class represents a helper object used for password reset operations.
 * It contains the token, password, and email associated with the password reset request.
 */
public class ResetPwdHelper {

    private String token;
    private String password;
    private String email;

    /**
     * Default constructor for the ResetPwdHelper class.
     */
    public ResetPwdHelper() {}

    /**
     * Constructs a new ResetPwdHelper object with the specified token, password, and email.
     *
     * @param token    the reset password token
     * @param password the new password
     * @param email    the email associated with the password reset request
     */
    public ResetPwdHelper(String token, String password, String email) {
        this.token = token;
        this.password = password;
        this.email = email;
    }

    /**
     * Returns the reset password token.
     *
     * @return the reset password token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the reset password token.
     *
     * @param token the reset password token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Returns the new password.
     *
     * @return the new password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the new password.
     *
     * @param password the new password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the email associated with the password reset request.
     *
     * @return the email associated with the password reset request
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email associated with the password reset request.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
