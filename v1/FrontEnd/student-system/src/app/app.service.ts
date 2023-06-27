import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import * as moment from 'moment';

/**
 * AppService is a service method for implements on the childrens modules and components
 */
@Injectable({
  providedIn: 'root',
})
export class AppService {
  /**
   * Constructor for the AppService class.
   *
   * @constructor
   * @param {Router} router - The Router instance.
   */
  constructor(private router: Router) {}

  /**
   *  Check on the localstorage  for the item role and verify the role to redirect to the right module when the app start
   *  and if is null or don't have the right Role value then will be re send to login page
   */
  checkRole() {
    switch (localStorage.getItem('role')) {
      case 'ADMIN':
        return this.router.navigate(['Admin-Workstation']);
      default:
        localStorage.clear();
        return this.router.navigate(['login']);
    }
  }

  /**
   *  Check the expiration of the token have 5 minutes or less and send alert if the condition is true
   *  We use [moment()]{@link https://momentjs.com/docs/#/parsing/} to convert the date then we use
   *  [diff()]{@link https://momentjs.com/docs/#/displaying/difference/} to get the now date and transform in  minutes
   *  to use on the if condition.
   */
  checkExpirationDateHave5MinLeft() {
    const date = moment(
      localStorage.getItem('expiration'),
      'ddd MMM DD HH:mm:ss zzz YYYY'
    );
    const diffInMinutes = date.diff(moment(), 'minutes', true);

    if (diffInMinutes <= 5) {
      alert('Your token will expire in 5 minutes');
    }
  }
}
