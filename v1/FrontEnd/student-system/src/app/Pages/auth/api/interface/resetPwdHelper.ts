/**
 * Represents the response model for a login request.
 */
export interface ResetPwdHelper{
    
    /**
     * The user token.
     */
    token: string;

    /**
     * The new user password.
     */
    password: string;

    /**
     * The new user email.
     */
    email: string;
}