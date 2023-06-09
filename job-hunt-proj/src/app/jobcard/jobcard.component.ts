import { Component, OnInit } from '@angular/core';
import {Input} from '@angular/core';
import { Router } from '@angular/router';
import {AuthenticationService} from '../authentication.service';
import { Jobs } from '../Jobs';

@Component({
  selector: 'app-jobcard',
  templateUrl: './jobcard.component.html',
  styleUrls: ['./jobcard.component.css']
})
export class JobcardComponent implements OnInit {
  @Input('data') job:any;
  name:string = ''
  location:string=''
  company:string=''
  landing_page:string=''
  role:string=''
  id:string=''

  jobs: Array<Jobs> = [];
  jobObj: Jobs;
  errorStatus: string;
  statusCode: number;
  


  constructor(
    private auth:AuthenticationService, private router:Router) { }
  ngOnInit(): void {

    this.jobObj = new Jobs();

    console.log(this.job);
    if(this.job["categories"].length>0 && this.job["categories"][0]["name"] != undefined){
      this.name = this.job["categories"][0]["name"];
      this.jobObj.name=this.name
    }
    if(this.job["locations"].length>0 && this.job["locations"][0]["name"] != undefined){
      this.location = this.job["locations"][0]["name"];
      this.jobObj.location=this.location
    }
    if(this.job["company"]["name"]!= undefined){
      this.company = this.job["company"]["name"];
      this.jobObj.company=this.company
    }
    if(this.job["refs"]["landing_page"]!= undefined){
      this.landing_page = this.job["refs"]["landing_page"];
      this.jobObj.landing_page=this.landing_page
    }
    if(this.job["id"]!= undefined){
      this.id = this.job["id"]
      this.jobObj.id=this.id
    }
    if(this.job['name']!=undefined){
      this.role=this.job["name"]
      this.jobObj.role=this.role
    }

    this.jobs.push(this.jobObj)
  }

  addToFavorite(job: Jobs) {
    this.addToFavoriteList(job);
    console.log("clicked add to wishlist...");

  }

  addToFavoriteList(job: Jobs) {
    console.log(job);
    
    this.auth.addJobToFavoriteList(job).subscribe(
      {next:(data: any)=>
        {console.log(data);

    },
    error:(e: { status: any; error: { message: any; }; })=>
    {this.errorStatus = `${e.status}`;
    
    const errorMsg = `${e.error.message}`;
    this.statusCode = parseInt(this.errorStatus, 10);}})
  }

  

  }



