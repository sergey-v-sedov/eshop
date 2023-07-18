import { Component, inject } from '@angular/core';
import {Order, OrderService} from "./order.service";
import {Product, StorefrontService} from "../storefront/storefront.service";
import {BehaviorSubject, Observable} from "rxjs";
import {DataSource} from "@angular/cdk/collections";

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent {
  products = new Map<string, Product>();
  dataSource!: OrdersDataSource;

  constructor(private ordersService: OrderService, private storefrontService: StorefrontService) {}
  ngOnInit() {
    this.ordersService.get().subscribe(data => this.dataSource = new OrdersDataSource(data));
    // @ts-ignore
    this.storefrontService.findProducts('').subscribe(data => data.forEach((product) => {
      this.products.set(product.id, product);
    }));
  }
}

export class OrdersDataSource extends DataSource<Order> {

  constructor(private orders: Order[]) {
    super();
  }

  data = new BehaviorSubject<Order[]>(this.orders);
  connect(): Observable<Order[]> {
    return this.data;
  }

  disconnect() {}
}