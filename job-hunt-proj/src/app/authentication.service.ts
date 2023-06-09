import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from './User';
import {UserHelper} from './UserHelper';
import { LoginComponent } from './login/login.component';
import { Jobs } from './Jobs';
const USER_NAME ="name"
@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  id: string;
  deleteJobFromWishlist(job: Jobs) {
    throw new Error('Method not implemented.');
  }
  // deleteCountryFromFavouriteList(job: Jobs) {
  //   throw new Error('Method not implemented.');
  // }
  registerEndPoint: string;
  loginendpoint: string;
  changePasswordEndPoint:string;
  updateProfileEndPoint:string;
  getProfileEndPoint:string;
  wishlistendpoint:string;
  user2: User;
  username=localStorage.getItem(USER_NAME)

  
 

  constructor(private httpClient: HttpClient, private router: Router) {
    this.registerEndPoint = 'http://localhost:8080/auth/register';
      
    this.loginendpoint = 'http://localhost:8080/auth/login';

    this.changePasswordEndPoint='http://localhost:8080/auth/changePassword';

    this.updateProfileEndPoint="http://localhost:8080/auth/update"

    this.getProfileEndPoint="http://localhost:8080/auth/get";

    this.wishlistendpoint= "http://localhost:8080/wishlist";
  }

  

  registerUser(newUser: any) {
    this.user2 = new User();
    this.user2.userName = newUser.userName;
    this.user2.password = newUser.password;
    this.user2.email = newUser.email;
    this.user2.qualification = newUser.qualification;
    //console.log(newUser + 'before');
    const url = this.registerEndPoint;
    //console.log(newUser + 'after');
    /*const user1 = {
      userName: newUser.username,
      password: newUser.password,
      email: newUser.email,
      qualification: newUser.qualification,
    };*/
    //console.log(this.user2 + 'abcd');
    return this.httpClient.post(url, this.user2);
  }

  public getToken(User: any) {
    console.log('Generating token from service');
    const url = this.loginendpoint;
    console.log(url);
    localStorage.setItem(USER_NAME, User.userName);
    return this.httpClient.post(url, User);
  }


  public isLoggedIn()
  {
    let token=localStorage.getItem("token");
    if(token==undefined || token==='' || token==null)
    {
      return false;
    }
    else
  {
    return true;
  }
}


  public changePasswordUser(UserHelper1:any){

   
    return this.httpClient.put(this.changePasswordEndPoint,UserHelper1,{responseType:'text'});
  }

  public updateProfile(userData:User){
    const username=localStorage.getItem(USER_NAME)
    
    return this.httpClient.put(this.updateProfileEndPoint+"/"+username,userData);

  }

  public getProfile(){
    const username=localStorage.getItem(USER_NAME)
    return this.httpClient.get(this.getProfileEndPoint+"/"+username);
  }
  
  addJobToFavoriteList(job: Jobs) {
    console.log("Calling addjobToFavourite from service class");
    this.username = localStorage.getItem(USER_NAME);
    console.log(this.username);
    if(this.username==null){
      const url = "";
    return this.httpClient.post(url, job)
    }
    else{
      const url = this.wishlistendpoint + "/" + this.username ;
    return this.httpClient.post(url, job)
       }
  }

  getFavoriteList(): Observable<Jobs[]> {
    console.log("getFavourite from service class");
    this.username = localStorage.getItem(USER_NAME);
    const url = this.wishlistendpoint + "/" + this.username;
    return this.httpClient.get<Jobs[]>(url);

  }


  deleteJobFavouriteList(job:Jobs){
   
    console.log("Calling deletejobfromFavouritelist from service class");
    this.username=localStorage.getItem(USER_NAME);
    this.id=job.id;
    const url = this.wishlistendpoint + "/" + this.username +"/"+this.id;
    console.log("delete url:-"+url);
    return this.httpClient.delete(url)

  }
  
}
