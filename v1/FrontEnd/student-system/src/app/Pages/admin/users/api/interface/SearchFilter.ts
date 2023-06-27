/**
 * Represents a search filter object.
 *
 * @interface SearchFilter
 */
export interface SearchFilter {
    /**
     * The type of search filter.
     *
     * @type {string}
     */
    type: string;
    /**
     * The data used for filtering.
     *
     * @type {string}
     */
    data: string;
  }
  