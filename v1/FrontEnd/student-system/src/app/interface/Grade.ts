import { Classroom } from './Classroom';
import { Student } from './Student';

/**
 * Represents a grade object.
 * @interface
 */
export interface Grade {
  /**
   * ID of the grade.
   * @type {number}
   * @memberof Grade
   */
  grade_id?: number;

  /**
   * ID of the classroom.
   * @type {Classroom}
   * @memberof Grade
   */
  fk_classroom?: number;

  /**
   * ID of the classroom.
   * @type {number}
   * @memberof Classroom
   */
  classroom_id?: number;

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
   * ID of the student.
   * @type {Student}
   * @memberof Grade
   */
  fk_student?: Student;

  /**
   * First note of the grade.
   * @type {number}
   * @memberof Grade
   */
  first_note?: number;

  /**
   * Second note of the grade.
   * @type {number}
   * @memberof Grade
   */
  second_note?: number;

  /**
   * Third note of the grade.
   * @type {number}
   * @memberof Grade
   */
  third_note?: number;

  /**
   * Additional note of the grade.
   * @type {number}
   * @memberof Grade
   */
  additional_note?: number;

  /**
   * Final note of the grade.
   * @type {number}
   * @memberof Grade
   */
  final_note?: number;

  /**
   * Status of the grade.
   * @type {string}
   * @memberof Grade
   */
  status?: string;

  /**
   * Date when the grade was created.
   * @type {string}
   * @memberof Grade
   */
  grade_creation_date?: string;
}