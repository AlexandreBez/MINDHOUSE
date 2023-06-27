import { Component, OnInit } from '@angular/core';
import { HomeService } from './services/home.service';
import { HomeApiService } from './api/home-api.service';

/**
 * HomeComponent for handling Admin Homepage functionality.
 */
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  
  /**
   * The name of the user.
   */
  name:string = localStorage.getItem('name');
  /**
   * Indicates whether the data is being loaded.
   */
  isLoading: boolean = false;
  /**
   * Error message to display.
   */
  errorMsg: string = null;
  /**
   * Success message to display.
   */
  successMsg: string = null;

  /**
   * Constructs a new instance of the HomeComponent.
   * @param {HomeService} homeService - The HomeService for generating the users' role quantity graphic.
   * @param {HomeApiService} homeApiService - The HomeApiService for sending email with roles quantity data.
   */
  constructor(
    private homeService: HomeService,
    private homeApiService: HomeApiService
  ){}
  
  /**
   * Initializes the component.
   */
  ngOnInit(){
    this.homeService.generateUsersRoleQtdGraphic('users-role-qtd-graphic');
  }

  /**
   * Generates a file and sends it via email.
   */
  onGenerateFileAndSend(){

    this.isLoading = true;
    this.successMsg = null;
    this.errorMsg = null;

    this.homeApiService.sendEmailWithRolesQtdData(Number.parseInt(localStorage.getItem('id')))
    .subscribe(
      data => {
        this.isLoading = false;
        this.successMsg = data.message;
      },
      error => {
        this.isLoading = false;
        console.log(error);
        this.errorMsg = "Oops... something went wrong";
      }
    );
  }

  
}
