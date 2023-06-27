import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from '../app.component';

/**
 * The routing configuration for the application.
 */
const appRoutes: Routes = [
  { 
    /**
     * The default app route that redirects to the app page.
     */
    path: '', 
    redirectTo: 'app',
    pathMatch: 'full'
  },
  { 
    /**
     * The default route that redirects to the app page.
     */
    path: 'app', 
    component: AppComponent
  },
  {
    /**
     * The lazy-loaded module for the Login page.
     */
    path: '',
    loadChildren: () => import('../Pages/auth/login/login.module').then((m) => m.LoginModule),
  },
  {
    /**
     * The lazy-loaded module for the send token page.
     */
    path: '',
    loadChildren: () => import('../Pages/auth/send-token/send-token.module').then((m) => m.SendTokenModule),
  },
  {
    /**
     * The lazy-loaded module for the validate tken page.
     */
    path: '',
    loadChildren: () => import('../Pages/auth/validate-token/validate-token.module').then((m) => m.ValidateTokenModule),
  },
  {
    /**
     * The lazy-loaded module for the Admin page.
     */
    path: '',
    loadChildren: () => import('../Pages/admin/admin.module').then((m) => m.AdminModule),
  },
  {
    /**
     * The lazy-loaded module for the not found page.
     */
    path: '',
    loadChildren: () => import('../Pages/not-found/not-found.module').then((m) => m.NotFoundModule),
  },
  { 
    /**
     * The fallback route that redirects to the Not Found page.
     */
    path: '**', 
    redirectTo: '404' 
  }
];

/**
 * The module that handles routing for the application.
 */
@NgModule({
  imports: [RouterModule.forRoot(appRoutes, { useHash: true })],
  exports: [RouterModule],
})
export class RoutesModule {}