import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export class Product {
  id!: String;
  title!: String;
}

@Injectable({
  providedIn: 'root'
})
export class StorefrontService {
  public baseUrl = "http://localhost:8080/api/v1";

  constructor(private httpClient: HttpClient) {}

  findProducts(): Observable<any> {
    return this.httpClient.get(this.baseUrl + "/products");
  }
}
