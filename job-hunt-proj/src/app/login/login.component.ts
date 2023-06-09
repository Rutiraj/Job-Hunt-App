import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../authentication.service';
import { User } from '../User';
export const TOKEN_NAME = "token";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User;
  dataa: any;
  disp_msg!:string;
 
  constructor(private httpClient: HttpClient,private authservice: AuthenticationService, private router: Router) {
    this.user=new User();
    
   }

  ngOnInit(): void {
  }
  login(LoginForm: NgForm) {
    console.log("User name is: "+this.user.userName);
    this.authservice.getToken(this.user).subscribe(
      (data=>{
        this.dataa=data;
        console.log(this.dataa);
        localStorage.setItem(TOKEN_NAME, this.dataa['token']);
        //localStorage.setItem(USER_NAME, this.user.userName);
        console.log("token is:"+this.dataa['token']) ;
        console.log("Login Successfull!");
        this.router.navigate(["/home"],{queryParamsHandling:"preserve"});
      }),
      (error=>{console.log("Error!--Token not generated because of Invalid credentials");
      this.disp_msg="Login Failed! Please provide valid credentials";
      })

    )
    
  }
  

}