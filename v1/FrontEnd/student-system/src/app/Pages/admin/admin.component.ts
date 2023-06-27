import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from 'src/app/app.service';

/**
 * LoginComponent for handling user login functionality.
 */
@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit{
  
  /**
   * The constructor for the AdminComponent class.
   *
   * @constructor
   * @param {AppService} appService - The appService instance.
   * @param {Router} router - The router instance.
   */
  constructor(private router: Router, private appService: AppService){}
  
  /**
   * Initializes the component.
   * When the admin page starts, it checks if the token has 5 minutes left.
   * It then navigates to the home page.
   */
  ngOnInit() {
    this.appService.checkExpirationDateHave5MinLeft();
    this.router.navigate(['Admin-Workstation/Home']);
  }

}
