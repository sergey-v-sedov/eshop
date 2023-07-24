import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
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
  public baseUrl = "http://localhost/api/v1";

  constructor(private httpClient: HttpClient) {}

  findProducts(query: string): Observable<any> {
    return this.httpClient.get(this.baseUrl + "/products?q="+query, { headers: new HttpHeaders({'Content-Type': 'application/json'})});
  }

  getCurencyRate(code: string): Observable<string> {
    let params = new HttpParams().append('provider', "https://v2.api.forex/rates/latest.json?key=demo&to="+code);
    return this.httpClient.get<string>(this.baseUrl + "/currency-rates", {
          headers: new HttpHeaders({'Content-Type': 'application/json'}),
          params: params});
  }
}