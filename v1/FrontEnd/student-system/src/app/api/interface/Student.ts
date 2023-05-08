/**
 * Represents a student object.
 * @interface
 */
export interface Student {
  /**
   * ID of the student.
   * @type {number}
   */
  student_id?: number;

  /**
   * Full name of the student.
   * @type {string}
   */
  student_name?: string;

  /**
   * Document (e.g. ID card, passport) number of the student.
   * @type {string}
   */
  student_document?: string;

  /**
   * Age of the student.
   * @type {string}
   */
  student_age?: string;

  /**
   * Date of birth of the student.
   * @type {string}
   */
  student_birthdate?: string;

  /**
   * Country of origin of the student.
   * @type {string}
   */
  student_country?: string;

  /**
   * State of origin of the student.
   * @type {string}
   */
  student_state?: string;

  /**
   * City of origin of the student.
   * @type {string}
   */
  student_city?: string;

  /**
   * Address of the student.
   * @type {string}
   */
  student_adress?: string;

  /**
   * Postal code of the student's address.
   * @type {string}
   */
  student_zipcode?: string;

  /**
   * Email address of the student.
   * @type {string}
   */
  student_email?: string;

  /**
   * Phone number of the student.
   * @type {string}
   */
  student_phone?: string;

  /**
   * Alternative phone number of the student.
   * @type {string}
   */
  student_phone_2?: string;

  /**
   * Flag indicating whether the student has agreed to the terms of the contract.
   * @type {boolean}
   */
  student_contract_term?: boolean;

  /**
   * Date when the student was added to the system.
   * @type {string}
   */
  student_creation_date?: string;
}
