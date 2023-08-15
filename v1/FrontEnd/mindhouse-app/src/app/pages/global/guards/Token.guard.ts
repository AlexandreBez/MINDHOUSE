import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import * as moment from 'moment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Token implements CanActivate {
  constructor(private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    const get_auth_cookie_data = localStorage.getItem('AUTH_COOKIE');

    if (!get_auth_cookie_data) {
      this.router.navigate(['login']);
      return false;
    }

    const decripted_data = atob(get_auth_cookie_data);
    const parsed_data = JSON.parse(decripted_data);
    const is_expired = moment().isSameOrBefore(
      moment(parsed_data.expiration, 'ddd MMM DD HH:mm:ss zzz YYYY'),
      'second'
    );
    const minutesLeft = moment(
      parsed_data.expiration,
      'ddd MMM DD HH:mm:ss zzz YYYY'
    ).diff(moment(), 'minutes');

    if (parsed_data.token == null || is_expired == false) {
      localStorage.clear();
      this.router.navigate(['login']);
      return false;
    } else {
      if (minutesLeft <= 5) {
        alert('Your session will expire in 5 minutes');
      }
      return true;
    }
  }
}
