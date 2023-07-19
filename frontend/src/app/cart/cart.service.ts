import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, share} from "rxjs";

export class CartItem {
    constructor(public productId: string) {}
}

@Injectable({
    providedIn: 'root'
})
export class CartItemService {
    public baseUrl = "/api/v1";

    constructor(private httpClient: HttpClient) {}

    getAll(): Observable<CartItem[]> {
        // @ts-ignore
        return this.httpClient.get(this.baseUrl + "/carts", { headers: new HttpHeaders({'Content-Type': 'application/json'})}).pipe(share());
    }

    add(productId:string): Observable<any> {
        return this.httpClient.put(this.baseUrl + "/carts/items/" + productId, null, { headers: new HttpHeaders({'Content-Type': 'application/json'})}).pipe(share());
    }

    remove(productId:string): Observable<any> {
        return this.httpClient.delete(this.baseUrl + "/carts/items/" + productId, { headers: new HttpHeaders({'Content-Type': 'application/json'})}).pipe(share());
    }
}