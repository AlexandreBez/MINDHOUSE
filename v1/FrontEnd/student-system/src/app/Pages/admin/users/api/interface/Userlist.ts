/**
 * Represents a user in a user list.
 *
 * @interface Userlist
 */
export interface Userlist {
    /**
     * The ID of the user.
     *
     * @type {number}
     */
    user_id: number;
    /**
     * The name of the user.
     *
     * @type {string}
     */
    name: string;
    /**
     * The email of the user.
     *
     * @type {string}
     */
    email: string;
    /**
     * The role of the user.
     *
     * @type {string}
     */
    role: string;
  }
  