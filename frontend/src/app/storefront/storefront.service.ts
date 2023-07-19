import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export class Product {
  id!: string;
  title!: string;
  description!: string;
  image!: string;
  price!: number;
}

@Injectable({
  providedIn: 'root'
})
export class StorefrontService {
  public baseUrl = "/api/v1";

  constructor(private httpClient: HttpClient) {}

  findProducts(query: string): Observable<any> {
    return this.httpClient.get(this.baseUrl + "/products?q="+query, { headers: new HttpHeaders({'Content-Type': 'application/json'})});
  }
}