import { Component, ViewChild } from '@angular/core';
import { UsersService } from './api/users.service';
import { NgForm } from '@angular/forms';
import { User } from './api/interface/User';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent {

  isDarkMode: boolean;
  theme: string;
  waitingActionResponse: boolean = false;
  errorModalMessage: any = null;
  successModalMessage: any = null;
  @ViewChild('createUserForm') createUserFormData: NgForm;

  constructor(
    private usersService: UsersService
  ) {
    this.getTheme();
  }
  
  getTheme() {
    this.theme = localStorage.getItem('theme') || 'light'; // Default to light theme if theme not found
    this.isDarkMode = this.theme === 'dark';
  }

  createNewUser(){

    let user: User = {
      name: this.createUserFormData.value.nameData.trim(),
      email: this.createUserFormData.value.emailData.trim(),
      password: null,
      role: this.createUserFormData.value.roleData,
      is_temp_password: true,
      token_reset_password: null,
      expiration_time_reset_password: null,
      created_on: null
    }

    this.waitingActionResponse = true;
    this.errorModalMessage = null;
    this.usersService.createNewUSer(user).subscribe(
      data => {
        this.successModalMessage = data.message;
        this.waitingActionResponse = false;
        console.info(data);
        setTimeout(() => {
          this.successModalMessage = null;
        }, 3500);
      },
      error => {
        console.warn(error)
        this.waitingActionResponse = false;
        this.errorModalMessage = "Oops... something went wrong";
      }
    )
  }
}
