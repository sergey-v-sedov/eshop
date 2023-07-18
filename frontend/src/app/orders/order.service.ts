import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, share} from "rxjs";

class OrderItem {
    constructor(public productId: string) {}
}

export class Order {
    constructor(public productOrderItems: OrderItem[], public creditCardNumber: string, public shippingAddress: string) {}
}

@Injectable({
    providedIn: 'root'
})
export class OrderService {
    public baseUrl = "http://localhost:8080/api/v1";

    constructor(private httpClient: HttpClient) {}

    get(): Observable<any> {
        return this.httpClient.get(this.baseUrl + "/orders", { headers: new HttpHeaders({'Content-Type': 'application/json'})}).pipe(share());
    }

    order(order:Order): Observable<any> {
        return this.httpClient.put(this.baseUrl + "/orders", order, { headers: new HttpHeaders({'Content-Type': 'application/json'})}).pipe(share());
    }
}