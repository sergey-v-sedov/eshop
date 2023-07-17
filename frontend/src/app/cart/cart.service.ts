import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../login/auth.service";
import {Observable, share} from "rxjs";

export class CartItem {
    constructor(public productId: string) {}
}

@Injectable({
    providedIn: 'root'
})
export class CartItemService {
    public baseUrl = "http://localhost:8080/api/v1";

    constructor(private httpClient: HttpClient, private authService:AuthService) {}

    getAll(): Observable<CartItem[]> {
        // @ts-ignore
        return this.httpClient.get(this.baseUrl + "/carts").pipe(share());
    }

    add(productId:string): Observable<any> {
        return this.httpClient.put(this.baseUrl + "/carts/items/" + productId, null).pipe(share());
    }

    remove(productId:string): Observable<any> {
        return this.httpClient.delete(this.baseUrl + "/carts/items/" + productId).pipe(share());
    }
}