import { Teacher } from './Teacher';
import { Course } from './Course';

/**
 * Represents a classroom.
 * @interface
 */
export interface Classroom {
  /**
   * ID of the classroom.
   * @type {number}
   * @memberof Classroom
   */
  classroom_id?: number;

  /**
   * ID of the teacher.
   * @type {Teacher}
   * @memberof Classroom
   */
  fk_teacher?: number;

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

  /**
   * ID of the course.
   * @type {Course}
   * @memberof Classroom
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
   * Period of the classroom (e.g. morning, afternoon, evening).
   * @type {string}
   * @memberof Classroom
   */
  classroom_period?: string;

  /**
   * Start time of the classroom.
   * @type {string}
   * @memberof Classroom
   */
  classroom_start_time?: string;

  /**
   * End time of the classroom.
   * @type {string}
   * @memberof Classroom
   */
  classroom_end_time?: string;

  /**
   * Start date of the classroom.
   * @type {string}
   * @memberof Classroom
   */
  classroom_start_date?: string;

  /**
   * End date of the classroom.
   * @type {string}
   * @memberof Classroom
   */
  classroom_end_date?: string;

  /**
   * Limit of students in the classroom.
   * @type {number}
   * @memberof Classroom
   */
  classroom_limit?: number;

  /**
   * Quantity of students currently enrolled in the classroom.
   * @type {number}
   * @memberof Classroom
   */
  classroom_qtd_students?: number;

  /**
   * Date when the classroom was created.
   * @type {string}
   * @memberof Classroom
   */
  classroom_creation_date?: string;

}
