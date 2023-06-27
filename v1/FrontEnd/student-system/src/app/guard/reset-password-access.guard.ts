import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateChildFn, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

/**
 * Guard for preventing access to the reset password page if the token is valid.
 * @implements {CanActivate}
 */
@Injectable({
  providedIn: 'root'
})
export class ResetPasswordAccessGuard implements CanActivate{

  /**
   * Determines whether the route can be activated.
   * @param {ActivatedRouteSnapshot} route - The route being activated.
   * @param {RouterStateSnapshot} state - The router state snapshot.
   * @returns {Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree} A boolean, an observable of a boolean, a promise of a boolean, or a UrlTree.
   */
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    
    if (localStorage.getItem('tokenIsValid') !== null || localStorage.getItem('token') !== null || localStorage.getItem('email') !== null) {
      return true;
    }else{
      return false;
    }
    
  }
  
}
