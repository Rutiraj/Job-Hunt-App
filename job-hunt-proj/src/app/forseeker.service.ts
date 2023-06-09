import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';


const LINK='http://localhost:8080/jobs/job?';
//const LINK="https://www.themuse.com/api/public/jobs?"


@Injectable({
  providedIn: 'root'
})
export class ForseekerService {

  constructor(private httpCli:HttpClient) { }
  getjobs(page:number) {
    return this.httpCli.get(`${LINK}`+'page='+page);
  }
  searchByCategory(page:number,selectedItems:[]){
    let url=LINK;
    for(let i of selectedItems){
      url+="category="+i['item_text']+"&"
    }
   
    return this.httpCli.get(url+'page='+page);

  }
  
}

