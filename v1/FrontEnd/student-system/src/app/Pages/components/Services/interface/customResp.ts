/**
 * Custom response interface for requests like add or delete.
 *
 * @example
 * ```
 * public deleteUser(id: number): Observable<CustomResp> { 
 *   return this.http.delete<CustomResp>(this.url + 'deleteUser/' + id, {});
 * }
 * ```
 * Response:
 * - message: User deleted with success
 * - statusCode: 200
 * - timestamp: 2023-06-16 10:00:00
 */
export interface CustomResp {
    /**
     * The message of the custom response.
     */
    message: string;
  
    /**
     * The status code of the custom response.
     */
    statusCode: number;
  
    /**
     * The timestamp of the custom response.
     */
    timestamp: string;
  }
  