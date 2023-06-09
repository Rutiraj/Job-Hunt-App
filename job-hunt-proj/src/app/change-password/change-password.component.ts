import { Component, OnInit } from '@angular/core';
import { UserHelper } from '../UserHelper'
import { Router } from '@angular/router';
import { AuthenticationService } from '../authentication.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  userHelper:UserHelper;
  disp_msg!:string;

  constructor( private authenticationService: AuthenticationService,
    private router: Router
  ) {
    this.userHelper = new UserHelper();
  }

  ngOnInit(): void {
  }
  changePassword(){
    console.log(this.userHelper);
    this.authenticationService.changePasswordUser(this.userHelper).subscribe({
      next: (data) => {
        this.disp_msg =
          'Congratulations ' +
          this.userHelper.userName +
          ' password updated';
        ;
      },
      error: (e) => {
        console.log(e);
        this.disp_msg =
          'Failed to update!please provide valid username or password';
      },
    });

  }
}
