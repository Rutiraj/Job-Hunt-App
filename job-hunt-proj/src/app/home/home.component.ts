import { isNgTemplate } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import{ForseekerService} from '../forseeker.service';



@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

p:any=1
jobs:any[] = [];
totaljobs:any;
page:number = 1;
isEndPage:boolean = false;
search:string=''


dropdownList:any = [];
disabled=false;
showFilter=false;
selectedItems:any = [];
dropdownSettings:IDropdownSettings
constructor(private seekerservice:ForseekerService) { }

  ngOnInit(): void {

    this.getjobs(this.page);

    this.dropdownList = [
      { item_id: 1, item_text: 'Accounting' },
      { item_id: 2, item_text: 'Arts' },
      { item_id: 3, item_text: 'Business Operations' },
      { item_id: 4, item_text: 'Computer and IT' },
      { item_id: 5, item_text: 'Data Science' },
      { item_id: 6, item_text: 'Healthcare' },
      
      { item_id: 7, item_text: 'Real Estate' },
      { item_id: 8, item_text: 'Software Engineering' },
     
    ];
    this.selectedItems=[
      
    ]
   
    this.dropdownSettings = {
      singleSelection: true,
      idField: 'item_id',
      textField: 'item_text',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      enableCheckAll:true,
      allowSearchFilter: true
    };
   
  }

 onItemSelect(item: any) {
 this.selectedItems.push(item)
  console.log(item);

}


onItemDeSelect(item:any){
  this.selectedItems.pop(item)
}


 goprev(){
  console.log(this.search)
   if(this.selectedItems.length==0){
    this.getjobs(this.page-1);
    console.log("go prev if")

   }
  else{
    this.searchByCategory(this.page-1);
    this.isEndPage=false
    console.log("go prev else")
  }
  
 }
 goNext(){
  console.log(this.search)
  if(this.selectedItems.length==0){
    this.getjobs(this.page+1);
    console.log("go next if")

   }
  else{
    this.searchByCategory(this.page+1);
    console.log("go next else")
  }
  
  
 }
 getjobs(page:number)
  {
    this.seekerservice.getjobs(page).subscribe(
      (response:any)=> {
        console.log(response['results']);
        this.jobs = response['results'];
        if (response['results'].length < 20) {
          this.totaljobs = response.length;
          this.isEndPage = true;
        }
        this.page = this.page <= page ? this.page<page ? this.page+1 : this.page : this.page-=1;
      },
    (error)=>{
      console.log(error.msg);
    }
    );

  }
  searchByCategory(page:number){
    console.log(this.selectedItems)
    this.seekerservice.searchByCategory(page,this.selectedItems).subscribe(
      (response:any)=> {
        console.log(response['results']);
        this.jobs = response['results'];
        if (response['results'].length < 20) {
          this.totaljobs = response.length;
          this.isEndPage = true;
        }
        this.page = this.page <= page ? this.page<page ? this.page+1 : this.page : this.page-=1;
      },
    (error)=>{
      console.log(error.msg);
    }
    );


  }
}
