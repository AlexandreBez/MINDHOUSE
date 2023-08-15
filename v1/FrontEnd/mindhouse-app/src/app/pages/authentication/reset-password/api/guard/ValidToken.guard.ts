import { CanActivateFn } from '@angular/router';

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
export class ValidToken implements CanActivate {
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    const get_reset_pwd_data = localStorage.getItem('RESET_PWD');
    const decripted_data = atob(get_reset_pwd_data);
    const parsed_data = JSON.parse(decripted_data);

    if (parsed_data != null || parsed_data.is_valid == 'true') {
      return true;
    }

    return false;
  }
}
