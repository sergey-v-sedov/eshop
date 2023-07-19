import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, share} from "rxjs";
import {AuthService} from "../login/auth.service";

class OrderItem {
    constructor(public productId: string) {}
}

export class Order {
    constructor(public profileId: string, public productOrderItems: OrderItem[], public creditCardNumber: string, public shippingAddress: string) {}
}

@Injectable({
    providedIn: 'root'
})
export class OrderService {
    public baseUrl = "/api/v1";

    constructor(private httpClient: HttpClient, private authService: AuthService) {}

    get(): Observable<any> {
        return this.httpClient.get(this.baseUrl + "/orders", { headers: new HttpHeaders({'Content-Type': 'application/json'})}).pipe(share());
    }

    order(order:Order): Observable<any> {
        order.profileId = this.authService.getProfileId(); // A01:2021 – Broken Access Control
        return this.httpClient.put(this.baseUrl + "/orders", order, { headers: new HttpHeaders({'Content-Type': 'application/json'})}).pipe(share());
    }
}