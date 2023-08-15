import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { Token } from './pages/global/guards/Token.guard';

const routes: Routes = [
  { 
    path: '', 
    redirectTo: 'app',
    pathMatch: 'full'
  },
  { 
    path: 'app', 
    component: AppComponent,
    canActivate: [Token]
  },
  {
    path: '',
    loadChildren: () => import('./pages/authentication/temporary-password/temporary-password.module').then((m) => m.TemporaryPasswordModule),
  },
  {
    path: '',
    loadChildren: () => import('./pages/admin-workspace/admin-workspace.module').then((m) => m.AdminWorkspaceModule),
  },
  {
    path: '',
    loadChildren: () => import('./pages/authentication/login/login.module').then((m) => m.LoginModule),
  },
  {
    path: '',
    loadChildren: () => import('./pages/authentication/send-token/send-token.module').then((m) => m.SendTokenModule),
  },
  {
    path: '',
    loadChildren: () => import('./pages/authentication/validate-token/validate-token.module').then((m) => m.ValidateTokenModule),
  },
  {
    path: '',
    loadChildren: () => import('./pages/authentication/reset-password/reset-password.module').then((m) => m.ResetPasswordModule),
  },
  {
    path: '',
    loadChildren: () => import('./pages/global/404/not-found.module').then((m) => m.NotFoundModule),
  },
  { 
    path: '**', 
    redirectTo: '404' 
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
