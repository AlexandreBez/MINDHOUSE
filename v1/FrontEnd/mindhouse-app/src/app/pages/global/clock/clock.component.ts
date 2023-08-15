import { Component } from '@angular/core';

/**
 * Clock component for displaying a real-time hour.
 */
@Component({
  selector: 'app-clock',
  templateUrl: './clock.component.html',
  styleUrls: ['./clock.component.scss'],
})
export class ClockComponent {


  /**
   * The current date string to be displayed in the HTML template.
   */
  date: string;

  /**
   * Constructs the ClockComponent and keeps the time updated on the page with setInterval.
   */
  constructor() {
    setInterval(() => {
      const currentDate = new Date();
      this.date = currentDate.toLocaleTimeString();
    }, 1000);
  }
}
