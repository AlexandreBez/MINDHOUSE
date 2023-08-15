/**
 * Represents the request model for a login operation.
 */
export interface LoginRequest {
    /**
     * The username provided for login.
     */
    username: string;
  
    /**
     * The password provided for login.
     */
    password: string;
  }