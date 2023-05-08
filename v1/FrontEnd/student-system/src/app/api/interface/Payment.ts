/**
 * Represents a payment object.
 * @interface
 */
export interface Payment {
  /**
   * ID of the payment.
   * @type {number}
   * @memberof Payment
   */
  payment_id?: number;

  /**
   * ID of the student who made the payment.
   * @type {Student}
   * @memberof Payment
   */
  fk_student?: number;

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

  /**
   * ID of the course that was paid for.
   * @type {Course}
   * @memberof Payment
   */
  fk_course?: number;

   /**
   * ID of the course.
   * @type {number}
   * @memberof Course
   */
   course_id?: number;

   /**
    * Name of the course.
    * @type {string}
    * @memberof Course
    */
   course_name?: string;
 
   /**
    * Field of the course.
    * @type {string}
    * @memberof Course
    */
   course_field?: string;
 
   /**
    * Price of the course.
    * @type {number}
    * @memberof Course
    */
   course_price?: number;
 
   /**
    * Modality of the course.
    * @type {string}
    * @memberof Course
    */
   course_modality?: string;
 
   /**
    * Date when the course was added to the system.
    * @type {string}
    * @memberof Course
    */
   course_creation_date?: string;

  /**
   * ID of the promotion used (if any).
   * @type {Promotion}
   * @memberof Payment
   */
  fk_promotion?: number;

  /**
   * ID of the promotion.
   * @type {number}
   * @memberof Promotion
   */
  promotion_id?: number;

  /**
   * Name of the promotion.
   * @type {string}
   * @memberof Promotion
   */
  promotion_name?: string;

  /**
   * Discount amount of the promotion.
   * @type {number}
   * @memberof Promotion
   */
  promotion_discount_amount?: number;

  /**
   * Description of the promotion.
   * @type {string}
   * @memberof Promotion
   */
  promotion_description?: string;

  /**
   * Date when the promotion was created.
   * @type {string}
   * @memberof Promotion
   */
  promotion_creation_date?: string;

  /**
   * Total amount of the payment.
   * @type {number}
   * @memberof Payment
   */
  payment_total_amount?: number;

  /**
   * Method used to make the payment.
   * @type {string}
   * @memberof Payment
   */
  payment_method?: string;

  /**
   * Status of the payment (e.g. "paid", "pending").
   * @type {string}
   * @memberof Payment
   */
  payment_status?: string;

  /**
   * Date when the payment was created.
   * @type {string}
   * @memberof Payment
   */
  payment_creation_date?: string;
}
