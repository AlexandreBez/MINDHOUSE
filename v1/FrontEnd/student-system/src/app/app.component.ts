import { Component, OnInit } from '@angular/core';
import { AppService } from './app.service';

/**
 * The root component of the Angular application.
 *
 * @remarks
 * This component serves as the main entry point of the application, and is
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

  constructor(private appService: AppService) {}

  ngOnInit() {
    this.appService.checkTokenAndRelogin();
  }
}
