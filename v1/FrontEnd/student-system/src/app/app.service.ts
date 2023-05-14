import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import * as moment from "moment";

@Injectable({
    providedIn: "root"
})
export class AppService {

    constructor(private router: Router){}

    checkTokenAndRelogin(){
        const token: string = localStorage.getItem('token');
        const expiration: string = localStorage.getItem('expiration');
        const role: string = localStorage.getItem('role');

        if (token == null || this.checkExpirationDate(expiration) == true) {
            this.router.navigate(['/login']);
        }

        switch (role) {
            case 'ADMIN':
              this.router.navigate(['admin']);
              break;
            case 'SUPPORT':
              this.router.navigate(['support']);
              break;
            case 'SUPPORT_MANAGER':
                this.router.navigate(['support_manager']);
                break;
            case 'SALES':
                this.router.navigate(['sales']);
                break;
            default:
              alert("No role defined...please, login again");
              localStorage.clear();
              this.router.navigate(['login']);
              break;
          }
    }

    checkExpirationDate(expirationDate: string){
        const format = 'ddd MMM DD HH:mm:ss zzz YYYY';
        const date = moment(expirationDate, format);
        const now = moment();
        return now.isAfter(date);
    }

    checkTokenIsValid(){
        const token: string = localStorage.getItem('token');
        const expiration: string = localStorage.getItem('expiration');

        if (token == null || this.checkExpirationDate(expiration) == true) {
            return false;
        }

        return true;
    }

}