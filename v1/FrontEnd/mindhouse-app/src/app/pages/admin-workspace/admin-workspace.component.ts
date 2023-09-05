import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-workspace',
  templateUrl: './admin-workspace.component.html',
  styleUrls: ['./admin-workspace.component.scss']
})
export class AdminWorkspaceComponent implements OnInit{

  isDarkMode: boolean;
  theme: string;

  constructor(
    private router: Router
  ) {
    this.theme = localStorage.getItem("theme") || "light"; // Default to light theme if theme not found
    this.isDarkMode = this.theme === "dark";
  }

  ngOnInit() {
    this.router.navigate(['/admin-workspace/Home']);
  }

  change_theme() {
    if (this.isDarkMode) {
      this.theme = "light";
    } else {
      this.theme = "dark";
    }
    localStorage.setItem("theme", this.theme);
    this.isDarkMode = !this.isDarkMode;
  }

  onLogout(){
    localStorage.clear();
    localStorage.setItem("theme", this.theme);
    this.router.navigate(['login']);
  }
  
}
