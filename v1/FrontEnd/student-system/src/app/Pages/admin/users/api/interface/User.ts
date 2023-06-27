/**
 * Represents a user object.
 *
 * @interface User
 */
export interface User {
    /**
     * The user ID.
     *
     * @type {number}
     * @optional
     */
    user_id?: number;
    /**
     * The user's name.
     *
     * @type {string}
     */
    name: string;
    /**
     * The user's email.
     *
     * @type {string}
     */
    email: string;
    /**
     * The user's password.
     *
     * @type {string}
     * @optional
     */
    password?: string;
    /**
     * The user's role.
     *
     * @type {string}
     */
    role: string;
    /**
     * The creation date of the user.
     *
     * @type {string}
     * @optional
     */
    creation_date?: string;
  }
  