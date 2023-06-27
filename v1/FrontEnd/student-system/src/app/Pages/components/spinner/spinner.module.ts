import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SpinnerComponent } from './spinner.component';

/**
 * The SpinnerModule is responsible for declaring, importing, and exporting the SpinnerComponent.
 * @summary This module is used to handle the loading animation in the application.
 * @since 1.0.0
 */
@NgModule({
  declarations: [
    SpinnerComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    SpinnerComponent
  ]
})
export class SpinnerModule { }
