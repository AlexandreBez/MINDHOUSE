import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';

/**
 * RoleAdminCheck is a guard method for the routes that verify the role
 */
@Injectable({
  providedIn: 'root',
})
export class RoleAdminCheck implements CanActivate {

  /**
   * canActivate is a internal Angular function to check if the logic is true will activate the route otherwise will not
   * If ROLE not attend the condition will return true and allow the navigation for the router
   * otherwise will be false and Angular will not allow 
   */
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    if (localStorage.getItem('role') == 'ADMIN') {
      return true;
    } else {
      return false;
    }
  }
}
