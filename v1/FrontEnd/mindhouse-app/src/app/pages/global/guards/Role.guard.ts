import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Role implements CanActivate {

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    const get_auth_cookie_data = localStorage.getItem('AUTH_COOKIE');
    const decripted_data = atob(get_auth_cookie_data);
    const parsed_data = JSON.parse(decripted_data);

    switch (localStorage.getItem('temp_password')) {
      case 'true':
        return false;
      case 'false':
        if (
          state.url.includes('admin-workspace') &&
          parsed_data.role === 'ADMIN'
        ) {
          return true;
        } else if (
          state.url.includes('business-workspace') &&
          (parsed_data.role === 'BUSINESS' || parsed_data.role === 'ADMIN')
        ) {
          return true;
        } else if (
          state.url.includes('sales-workspace') &&
          (parsed_data.role === 'SALES' || parsed_data.role === 'ADMIN')
        ) {
          return true;
        } else if (
          state.url.includes('support-workspace') &&
          (parsed_data.role === 'SUPPORT' || parsed_data.role === 'ADMIN')
        ) {
          return true;
        } else {
          return false;
        }
      default:
        return false;
    }
  }
}
