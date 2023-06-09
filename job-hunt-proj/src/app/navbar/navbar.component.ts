import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  

  constructor(private router: Router) {
    
   }

  ngOnInit(): void {
    
  }
  //To remove token from localstorage
  logout() {
    localStorage.removeItem('name');
    localStorage.clear();
    localStorage.removeItem('token');
    localStorage.clear();
    console.log('logged out');
    this.router.navigate(['/login'])
  }
  

}
