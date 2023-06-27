import { Component } from '@angular/core';
import { Router } from '@angular/router';

/**
 * HeaderComponent represents the header component of the application.
 */
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent {

  /**
   * Creates an instance of HeaderComponent.
   * @param {Router} router - The router service.
   */
  constructor(private router: Router) {}

  /**
   * Handles the logout action.
   * Clears the local storage and navigates to the login page.
   */
  onLogout() {
    localStorage.clear();
    this.router.navigate(['login']);
  }

}
