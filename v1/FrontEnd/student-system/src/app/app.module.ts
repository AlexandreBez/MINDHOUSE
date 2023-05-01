import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderModule } from './shared/header/header.module';
import { RoutesModule } from './routes/routing.modules';

/**
 * The main application module.
 *
 * This module is responsible for bootstrapping the Angular application and
 * defining the application-wide dependencies and settings.
 *
 * @remarks
 * This module imports other feature modules, such as the `HeaderModule` and
 * `RoutesModule`, and defines the root component of the application, which is
 * the `AppComponent`.
 */
@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule, RoutesModule, HeaderModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
