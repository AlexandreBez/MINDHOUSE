import { Component, OnInit, ViewChild } from '@angular/core';
import { SearchFilter } from './api/interface/SearchFilter';
import { NgForm } from '@angular/forms';
import { User } from './api/interface/User';
import { UserApiService } from './api/user-api.service';
import { Router } from '@angular/router';

/**
 * UsersComponent for handling user login functionality.
 */
@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
})
export class UsersComponent implements OnInit {

  /**
   * Flag indicating whether the login operation is in progress.
   */
  isLoading: boolean = false;
  /**
   * variable to handle and displayed a loaded users data.
   */
  loadedData: any;
  /**
   * Error message displayed in case of action failure.
   */
  errorMsg: string = null;
  /**
   * Success message displayed in case of action success.
   */
  successMsg: string = null;
  /**
   * variable to handle and help in actions using the user ID.
   */
  idForActions: number;
  /**
   * variable to handle the filter type of data.
   */
  searchType: string = null;
  /**
   * variable to handle the value to be searched with the filter.
   */
  dataForSearch: string = null;
  /**
   * Reference to the update form.
   */
  @ViewChild('updateForm') updateUserForm: NgForm;
  /**
   * Flag indicating whether the password will be visible.
   */
  showPassword: boolean = false;
  /**
   * variable to handle the user name.
   */
  loadedName: string;
  /**
   * variable to handle the user email.
   */
  loadedEmail: string;
  /**
   * variable to handle the user password.
   */
  password_input: string = null;
  /**
   * variable to handle the password equality.
   */
  confirmPassword: string = null;
  /**
   * variable to handle the user role.
   */
  loadedRole: string;

  /**
   * Reference to the add user form.
   */
  @ViewChild('addForm') addUserForm: NgForm;

  /**
   * The constructor for the UsersComponent class.
   *
   * @constructor
   * @param {UserApiService} userApiService - The appService instance.
   * @param {Router} router - The router instance.
   */
  constructor(private userApiService: UserApiService, private router: Router){}

  /**
   * Initializes the component.
   */
  ngOnInit() {
    this.getAllUsers();
    this.searchType = 'name';
  }

  /**
   * Toggles the visibility of the password field.
   */
  changePwdVisibility() {
    this.showPassword = !this.showPassword;
    this.isLoading = false;
  }

  /**
   * Selects a user by their ID.
   * @param id The ID of the user to select.
   */
  selectUserById(id: number) {
    this.isLoading = true;
    this.userApiService.getUserById(id).subscribe(
      (data) => {
        this.loadedName = data.name;
        this.loadedEmail = data.email;
        this.loadedRole = data.role;
        this.isLoading = false;
      },
      (error) => {
        // Handle the error
        this.loadedName = 'Error';
        this.loadedEmail = 'Error';
        console.log(error);
        this.isLoading = false;
      }
    );
  }

  /**
   * Retrieves all users.
   */
  getAllUsers() {
    this.isLoading = true;
    this.successMsg = null;
    this.errorMsg = null;

    this.userApiService.getAllUsers().subscribe(
      (data) => {
        this.searchType = 'name';
        this.dataForSearch = '';

        this.isLoading = false;
        this.loadedData = data;
      },
      (error) => {
        this.searchType = 'name';
        this.dataForSearch = '';
        this.isLoading = false;
        
        if(error.status == 404){
          this.loadedData = null;
        }else{
          this.isLoading = false;
          this.errorMsg = 'Oops... something went wrong';
        }
        console.log(error);
      }
    );
  }

  /**
   * Searches for users based on a filter.
   */
  searchUserByFilter() {
    this.isLoading = true;
    this.successMsg = null;
    this.errorMsg = null;

    if (this.dataForSearch == null || this.dataForSearch == '') {
      this.getAllUsers();
    }

    const searchConfig: SearchFilter = {
      type: this.searchType,
      data: this.dataForSearch.trim(),
    };

    this.userApiService.searchUserByFilter(searchConfig).subscribe(
      (data) => {
        this.isLoading = false;
        this.loadedData = data;
      },
      (error) => {
        this.isLoading = false;
        console.log(error);
        switch (error.status) {
          case 404:
            return (this.loadedData = null);
          default:
            this.loadedData = null;
            return (this.errorMsg = 'Oops... something went wrong');
        }
      }
    );

    this.searchType = 'name';
    this.dataForSearch = '';
  }

  /**
   * Updates a user.
   */
  updateUser() {
    this.isLoading = true;

    let userData: User = {
      name: this.updateUserForm.value.updateNameInput.trim(),
      email: this.updateUserForm.value.updateEmailInput.trim(),
      password: this.password_input.trim(),
      role: this.updateUserForm.value.updateRoleInput,
    };

    this.userApiService.updateUser(this.idForActions, userData).subscribe(
      (data) => {
        console.log(data);
        this.isLoading = false;
        
        this.getAllUsers();
        this.successMsg = 'User updated with success...';
        
        setTimeout(() => {
          this.successMsg = null;
          if (this.idForActions.toString() == localStorage.getItem('id')) {
            localStorage.clear();
            this.router.navigate(['login']);
          }
          this.idForActions = null;
        }, 3000);
      },
      (error) => {
        console.log(error);
        this.isLoading = false;
        this.idForActions = null;

        this.getAllUsers();
        this.errorMsg = 'Oops... something went wrong';

        setTimeout(() => {
          this.errorMsg = null;
        }, 3000);
      }
    );
    this.addUserForm.reset();
  }

  /**
   * Creates a new user.
   */
  createUser(){
    this.isLoading = true;
    this.successMsg = null;
    this.errorMsg = null;
    this.showPassword = false;

    if (this.password_input !== this.confirmPassword) {
      this.isLoading = false;
      this.errorMsg = "Passwords are not equal...";
    }else{
      const userData: User = {
        name: this.addUserForm.value.nameAddInput.trim(),
        email: this.addUserForm.value.emailAddInput.trim(),
        password: this.addUserForm.value.passwordAddInput.trim(),
        role: this.addUserForm.value.roleAddInput
      }
  
      this.userApiService.createNewUser(userData)
      .subscribe(
        data => {
          console.log(data);
          this.isLoading = false;
  
          this.getAllUsers();
          this.successMsg = data.message;
  
          setTimeout(() => {
            this.successMsg = null;
          }, 3000);
        },
        error => {
          console.log(error);
          this.isLoading = false;

          if (error.error.status == 400) {
            this.errorMsg = error.error.message;
          }else{
            this.errorMsg = "Oops... something went wrong";
          }
          setTimeout(() => {
            this.errorMsg = null;
          }, 3000);
        }
      )
      this.addUserForm.reset();
    }
  }

  /**
   * Deletes a user.
   */
  deleteUser(){
    this.isLoading = true;
    this.successMsg = null;
    this.errorMsg = null;

    this.userApiService.deleteUser(this.idForActions)
    .subscribe(
      data => {
        console.log(data);
        this.isLoading = false;

        this.getAllUsers();
        this.successMsg = "User created with success...";

        setTimeout(() => {
          this.successMsg = null;
        }, 3000);
      },
      error => {
        console.log(error);
        this.isLoading = false;

        this.errorMsg = "Oops... something went wrong";

        setTimeout(() => {
          this.errorMsg = null;
        }, 3000);
      }
    )
  }

}
