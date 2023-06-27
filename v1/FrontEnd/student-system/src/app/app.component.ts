import { Component, OnInit } from '@angular/core';
import { AppService } from './app.service';

/**
 * The root component of the Angular application.
 *
 * @remarks
 * This component serves as the main entry point of the application and is
 * responsible for bootstrapping the entire Angular platform.
 *
 * @example
 * ```
 * <app-root></app-root>
 * ```
 */
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  
  /**
   * The constructor for the AppComponent class.
   *
   * @constructor
   * @param {AppService} appService - The AppService instance.
   */
  constructor(private appService: AppService) {}

  /**
   * Initializes the component.
   *
   * @remarks
   * This method is called automatically by Angular when the component is being initialized.
   * It is used to check the user's role and token expiration date.
   *
   * @returns {void}
   */
  ngOnInit(): void {
    this.appService.checkRole();
    this.appService.checkExpirationDateHave5MinLeft();
  }
}
