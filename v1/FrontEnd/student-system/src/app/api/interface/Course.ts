/**
 * Represents a course object.
 * @interface
 */
export interface Course {
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
}
