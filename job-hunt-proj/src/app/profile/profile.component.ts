import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {User} from '../User';
import { AuthenticationService } from '../authentication.service';



@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user:User;
  disp_msg:string;
  

  constructor(private authenticationService: AuthenticationService,private router:Router) { 
    this.user=new User();
  }

  ngOnInit(): void {
    this.getProfile();
   
    
  }
  updateProfile(){
    console.log(this.user);
    this.authenticationService.updateProfile(this.user).subscribe({
      
      next: (data) => {
        console.log(data)
        this.disp_msg =
          'Congratulations ' +
          this.user.userName +
          ' profile updated';
        ;
      },
      error: (e) => {
        console.log(e);
        this.disp_msg =
          'Failed to update!';
      },
    });

  }

  getProfile(){
    this.authenticationService.getProfile().subscribe({
      
      next: (data) => {
        // @ts-ignore
        this.user.userName=data['userName'];
        // @ts-ignore
        this.user.email=data['email'];
        // @ts-ignore
        this.user.qualification=data['qualification'];
        console.log(data)
        this.disp_msg =
        
          '';
        ;
      },
      error: (e) => {
        console.log(e);
        this.disp_msg =
          'data not got';
      },
    });


  }


}
