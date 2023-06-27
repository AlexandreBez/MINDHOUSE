/**
 * Represents the response model for a login request.
 */
export interface LoginResModel {
    /**
     * The user ID.
     */
    id: string;
  
    /**
     * The user's name.
     */
    name: string;
  
    /**
     * The user's role.
     */
    role: string;
  
    /**
     * The expiration date of the user's token.
     */
    expiration: string;
  
    /**
     * The authentication token.
     */
    token: string;
  }
  