import { Component, ViewChild } from '@angular/core';
import { UsersService } from './api/users.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent {

  specialCharRegex = /(?=.*[@])/;
  isDarkMode: boolean;
  theme: string;
  waitingActionResponse: boolean = false;
  errorModalMessage: any = null;
  successModalMessage: any = null;
  @ViewChild('addUserForm') addNewUserFormData: NgForm;

  constructor(
    private homeService: UsersService
  ) {
    this.getTheme();
  }

  ngOnInit() {
  }
  
  getTheme() {
    this.theme = localStorage.getItem('theme') || 'light'; // Default to light theme if theme not found
    this.isDarkMode = this.theme === 'dark';
  }

  createNewUser(){

  }
}
