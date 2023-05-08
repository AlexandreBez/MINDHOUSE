/**
 * Represents a teacher object.
 * @interface
 */
export interface Teacher {
  
  /**
   * ID of the teacher.
   * @type {number}
   * @memberof Teacher
   */
  teacher_id?: number;

  /**
   * Full name of the teacher.
   * @type {string}
   * @memberof Teacher
   */
  teacher_name?: string;

  /**
   * Document (e.g. ID card, passport) number of the teacher.
   * @type {string}
   * @memberof Teacher
   */
  teacher_document?: string;

  /**
   * Age of the teacher.
   * @type {string}
   * @memberof Teacher
   */
  teacher_age?: string;

  /**
   * Date of birth of the teacher.
   * @type {string}
   * @memberof Teacher
   */
  teacher_birthdate?: string;

  /**
   * Country of origin of the teacher.
   * @type {string}
   * @memberof Teacher
   */
  teacher_country?: string;

  /**
   * State of origin of the teacher.
   * @type {string}
   * @memberof Teacher
   */
  teacher_state?: string;

  /**
   * City of origin of the teacher.
   * @type {string}
   * @memberof Teacher
   */
  teacher_city?: string;

  /**
   * Address of the teacher.
   * @type {string}
   * @memberof Teacher
   */
  teacher_address?: string;

  /**
   * Postal code of the teacher's address.
   * @type {string}
   * @memberof Teacher
   */
  teacher_zipcode?: string;

  /**
   * Email address of the teacher.
   * @type {string}
   * @memberof Teacher
   */
  teacher_email?: string;

  /**
   * Phone number of the teacher.
   * @type {string}
   * @memberof Teacher
   */
  teacher_phone?: string;

  /**
   * Alternative phone number of the teacher.
   * @type {string}
   * @memberof Teacher
   */
  teacher_phone_2?: string;

  /**
   * Date when the teacher was added to the system.
   * @type {string}
   * @memberof Teacher
   */
  teacher_creation_date?: string;
}
