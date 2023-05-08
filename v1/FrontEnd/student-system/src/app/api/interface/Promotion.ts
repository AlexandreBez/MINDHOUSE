/**
 * Represents a promotion object.
 * @interface
 */
export interface Promotion {
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
}
