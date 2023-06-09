import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { HomeComponent } from './home/home.component';
import { ChangePasswordComponent} from './change-password/change-password.component';
import {ProfileComponent} from './profile/profile.component';
import {AuthguardService} from './authguard.service';
import { Jobcard2Component } from './jobcard2/jobcard2.component';


const routes: Routes = [
  {path:'',redirectTo:'login',pathMatch:'full'},
  
  {path:'login',component:LoginComponent},
  {path:'signup',component:SignupComponent},
  {path:'home',component:HomeComponent,canActivate:[AuthguardService]},
  {path:'change-password',component:ChangePasswordComponent,canActivate:[AuthguardService]},
  {path:'profile',component:ProfileComponent,canActivate:[AuthguardService]},
  {path:'search/:searchTerm',component:HomeComponent},
  {path:'wishlist',component:Jobcard2Component,canActivate:[AuthguardService]}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
