import { Component, OnInit } from '@angular/core';
import { HomeService } from './api/home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  newEmail: string;
  selectedEmails: string[] = [];
  specialCharRegex = /(?=.*[@])/;
  isDarkMode: boolean;
  theme: string;
  waitingActionResponse: boolean = false;
  errorMessage: any = null;
  successMessage: any = null;
  errorEmailMessage: any = null;
  successEmailMessage: any = null;
  chartData: any[] = [];
  user_name: string;
  loadedEmails: any;

  constructor(private homeService: HomeService) {
    this.getTheme();
    let get_auth_data = localStorage.getItem('AUTH_COOKIE');
    let decripted_data = atob(get_auth_data);
    let parsed_data = JSON.parse(decripted_data);

    this.user_name = parsed_data.name;
  }

  ngOnInit() {
    this.getAmountOfRolesGraphicData();
  }
  
  getTheme() {
    this.theme = localStorage.getItem('theme') || 'light'; // Default to light theme if theme not found
    this.isDarkMode = this.theme === 'dark';
  }

  getAmountOfRolesGraphicData() {
    this.homeService.getAmountOfRolesGraphicData()
    .subscribe(
      (data: any) => {
        this.chartData = data.map((item: any) => {
          return { name: item.role, value: item.qtd };
        });
        console.info(data);
      },
      (error) => {
        console.warn(error.message);
      }
    );
  }

  loadEmails(){
    this.homeService.getListOfEmails()
    .subscribe(
      (data: any) => {
        this.loadedEmails = data;
        console.info(data);
      },
      (error) => {
        console.warn(error.message);
      }
    );
  }

  addEmail() {
    if (this.newEmail && !this.selectedEmails.includes(this.newEmail)) {
      this.selectedEmails.push(this.newEmail);
      this.newEmail = '';
    }
  }

  removeEmail(index: number) {
    this.selectedEmails.splice(index, 1);
  }

  downloadExcelUserReport() {
    this.errorMessage = null;
    this.homeService.downloadExcelUserReport().subscribe(
      (response: Blob) => {
        const blob = new Blob([response], {
          type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
        });
  
        // Create a link element
        const link = document.createElement('a');
        link.href = URL.createObjectURL(blob);
  
        // Set the download attribute and filename
        link.download = 'user_report.xlsx'; // You can set your desired filename here
        // Simulate a click event to trigger the download
        link.click();
        this.successMessage = "Download with success...";
        // Cleanup the URL object after the download is started
        console.info(response);
        setTimeout(() => {
          URL.revokeObjectURL(link.href);
          this.successMessage = null;
        }, 3500);
      },
      (error) => {
        console.warn(error)
        this.errorMessage = "Oops... something went wrong";
        setTimeout(() => {
          this.errorMessage = null;
        }, 3500);
      }
    );
  }
  
  sendExcelUserReportForEmails() {
    this.waitingActionResponse = true;
    this.errorEmailMessage = null;
    this.homeService.sendExcelUserReportForEmails(this.selectedEmails).subscribe(
      (data) => {
        this.successEmailMessage = data.message;
        this.waitingActionResponse = false;
        console.info(data);
        setTimeout(() => {
          this.successEmailMessage = null;
        }, 3500);
      },
      (error) => {
        console.warn(error)
        this.waitingActionResponse = false;
        this.errorEmailMessage = "Oops... something went wrong";
        setTimeout(() => {
          this.errorEmailMessage = null;
        }, 3500);
      }
    );
    this.selectedEmails = [];
  }
  
}
