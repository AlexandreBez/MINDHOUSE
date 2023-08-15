import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginResponse } from './pages/authentication/login/api/Interface/LoginResponse';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  constructor(private router: Router) {}

  ngOnInit() {
    const get_auth_cookie_data = localStorage.getItem('AUTH_COOKIE');

    if (get_auth_cookie_data != null) {
      const decripted_data = atob(get_auth_cookie_data);
      const parsed_data = JSON.parse(decripted_data);

      switch (localStorage.getItem('temp_password')) {
        case 'true':
          return this.router.navigate(['tempPassword']);
        case 'false':
          if (parsed_data.role === 'ADMIN') {
            return this.router.navigate(['admin-workspace']);
          } else if (parsed_data.role === 'BUSINESS') {
            return this.router.navigate(['business-workspace']);
          } else if (parsed_data.role === 'SALES') {
            return this.router.navigate(['sales-workspace']);
          } else if (parsed_data.role === 'SUPPORT') {
            return this.router.navigate(['support-workspace']);
          } else {
            localStorage.clear();
            return this.router.navigate(['login']);
          }
        default:
          localStorage.clear();
          return this.router.navigate(['login']);
      }

    } else {
      return console.warn('AUTH_COOKIE data not found.');
    }
  }
}
