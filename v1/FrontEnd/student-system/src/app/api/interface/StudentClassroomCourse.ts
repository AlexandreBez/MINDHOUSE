/**
 * Represents a student enrollment in a classroom course.
 * @interface
 */
export interface StudentClassroomCourse {
  /**
   * ID of the enrollment.
   * @type {number}
   */
  payment_id?: number;

  /**
   * ID of the enrollment.
   * @type {number}
   */
  grade_id?: number;

  /**
   * ID of the enrollment.
   * @type {number}
   */
  student_classroom_course_id?: number;

  /**
   * ID of the student enrolled in the course.
   * @type {Student}
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
   * ID of the classroom the student is enrolled in.
   * @type {Classroom}
   */
  fk_classroom?: number;

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

  /**
   * ID of the course the student is enrolled in.
   * @type {Course}
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
