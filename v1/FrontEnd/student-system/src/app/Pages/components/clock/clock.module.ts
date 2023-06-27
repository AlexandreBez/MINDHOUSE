import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClockComponent } from './clock.component';

/**
 * The ClockModule is responsible for declaring, importing, and exporting the ClockComponent.
 * @summary This module is used to handle the clock animation in the application.
 * @since 1.0.0
 */
@NgModule({
  declarations: [
    ClockComponent
  ],
  imports: [
    CommonModule
  ],
  exports:[
    ClockComponent
  ]
})
export class ClockModule { }
