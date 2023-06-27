import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { AppService } from '../app.service';
import * as moment from 'moment';

/**
 * JWTTokenVerify is a guard method for the routes that verify the Token expiration date
 */
@Injectable({
  providedIn: 'root',
})
export class JWTTokenVerify implements CanActivate {
  /**
   * Constructor for the AppService
   * @param {AppService} appService - Service import
   */
  constructor(private appService: AppService) {}

  /**
   * canActivate is a internal Angular function to check if the logic is true will activate the route otherwise will not
   * token - The token saved on the web localstorage.
   * expiration - The expiration date saved on the web localstorage.
   * dateFormated - The formate date from the expiration variable using [moment()]{@link https://momentjs.com/docs/#/parsing/}.
   * now - variable that handles the actual date using [moment()]{@link https://momentjs.com/docs/#/parsing/}.
   * The funtion on the if condition we will check if the token is null or if is expired with [isAfter()]{@link https://momentjs.com/docs/#/query/is-after/}
   * function from MomentJS. If not attend the condition will return true and allow the navigation for the router
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
    const token: string = localStorage.getItem('token');
    const expiration: string = localStorage.getItem('expiration');
    const dateFormated = moment(expiration, 'ddd MMM DD HH:mm:ss zzz YYYY');
    const now = moment();

    if (token != null || !now.isAfter(dateFormated)) {
      return true;
    } else {
      return false;
    }
  }
}
