import { Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { UsersService } from './api/users.service';
import { NgForm } from '@angular/forms';
import { User } from './api/interface/User';
import { UsersTableData } from './api/interface/UserTableData';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit{

  isDarkMode: boolean;
  theme: string;
  
  @ViewChild('createUserForm') createUserFormData: NgForm;

  errorModalMessage: any = null;
  successModalMessage: any = null;
  waitingActionResponse: boolean = false;

  tableData: UsersTableData[] = []
  isLoading: boolean = false;
  errorMessage: any = null;

  searchType: string = null;
  dataForSearch: string = null;

  idForAction: number;
  @ViewChild('closeButtonDeleteModal') closeButtonDelete!: ElementRef;

  userName: string;
  userEmail: string;
  userRole: string;
  @ViewChild('updateUserForm') updateUserFormData: NgForm;
  @ViewChild('closeButtonUpdateModal') closeButtonUpdate!: ElementRef;

  get_auth_data: any = localStorage.getItem('AUTH_COOKIE');
  decripted_data: any = atob(this.get_auth_data);
  parsed_data: any = JSON.parse(this.decripted_data);
  parsed_data_user_id = this.parsed_data.id

  constructor(
    private usersService: UsersService,
    private renderer: Renderer2,
  ) {
    this.getTheme();
  }
  ngOnInit() {
    this.getUsersTableData();
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
    this.usersService.createNewUser(user).subscribe(
      data => {
        this.successModalMessage = data.message;
        this.waitingActionResponse = false;
        console.info(data);
        setTimeout(() => {
          this.successModalMessage = null;
          this.getUsersTableData();
        }, 3500);
      },
      error => {
        console.warn(error)
        this.waitingActionResponse = false;
        this.errorModalMessage = "Oops... something went wrong";
      }
    )
  }

  getUsersTableData(){
    this.isLoading = true;
    this.errorMessage = null;
    this.usersService.userTableData()
    .subscribe(
      data => {
        this.tableData = data;
        this.isLoading = false;
      },
      error => {
        this.isLoading = false;
        this.tableData = [];
        console.warn(error);
        this.errorMessage = "Oops... something went wrong";
      }
    )

  }

  searchUserByFilter() {
    this.isLoading = true;
    this.errorMessage = null;

    if (this.dataForSearch == null || this.dataForSearch == '') {
      this.getUsersTableData();
    }

    this.usersService.filterSearch(this.searchType, this.dataForSearch).subscribe(
      data => {
        this.isLoading = false;
        this.tableData = data;
        console.info(data);
      },
      error => {
        this.isLoading = false;
        console.info(error);
        this.tableData = [];
      }
    );

    this.searchType = 'name';
    this.dataForSearch = '';
  }

  deleteUser(id: number){
    this.waitingActionResponse = true;
    this.errorModalMessage = null;

    if (this.parsed_data_user_id == id) {
      this.waitingActionResponse = false;
      this.errorModalMessage = "You can't delete your own user while logged in...";
    }else{
      this.usersService.deleteUser(id)
      .subscribe(
        data => {
          console.info(data);
          this.waitingActionResponse = false;
          this.successModalMessage = data.message;
          setTimeout(() => {
            this.getUsersTableData();
            this.successModalMessage = null;
            this.renderer.selectRootElement(this.closeButtonDelete.nativeElement).click();
          }, 2000);
        },
        error => {
          console.warn(error);
          this.waitingActionResponse = false;
          this.errorModalMessage = "Oops... something went wrong";
        }
      )
    }
  }

  updateUser(){

    let user: User = {
      user_id: this.idForAction,
      name: this.updateUserFormData.value.updateNameData.trim(),
      email: this.updateUserFormData.value.updateEmailData.trim(),
      password: null,
      role: this.updateUserFormData.value.updateRoleData,
      is_temp_password: true,
      token_reset_password: null,
      expiration_time_reset_password: null,
      created_on: null
    }

    this.waitingActionResponse = true;
    this.errorModalMessage = null;
    this.usersService.updateUser(user)
    .subscribe(
      data => {
        console.info(data);
        this.waitingActionResponse = false;
        this.successModalMessage = data.message;
        setTimeout(() => {
          this.getUsersTableData();
          this.successModalMessage = null;
          this.renderer.selectRootElement(this.closeButtonUpdate.nativeElement).click();
        }, 2000);
      },
      error => {
        console.warn(error);
        this.waitingActionResponse = false;
        this.errorModalMessage = "Oops... something went wrong";
      }
    )
  }

}
