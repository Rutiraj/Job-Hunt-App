import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {
  wishListEndPoint: string;

  constructor(private httpClient:HttpClient) {
    this.wishListEndPoint='http://localhost:8080/wishlist'

   }

  /*addToWishlist(Job:any) {
    const url=this.wishListEndPoint
    return this.httpClient.post(url,id)
  }
  removeFromWishlist(){
    return this.httpClient.delete(url+)
  }*/
}
