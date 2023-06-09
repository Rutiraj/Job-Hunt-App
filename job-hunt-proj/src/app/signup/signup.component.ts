import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../authentication.service';
import { User } from '../User';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent implements OnInit {
  user: User;
  disp_msg!: String;

  constructor(
    private AuthenticationService: AuthenticationService,
    private router: Router
  ) {
    this.user = new User();
  }

  
  ngOnInit(): void {}

  register() {
    console.log(this.user);
    this.AuthenticationService.registerUser(this.user).subscribe({
      next: (data) => {
        this.disp_msg =
          'Congratulations ' +
          this.user.userName +
          ' your account created successfully';
        this.router.navigate(['/login']);
      },
      error: (e) => {
        console.log(e);
        this.disp_msg =
          'Failed to create account ! Reason: User Already Exists with this Username';
      },
    });
  }
  
}

