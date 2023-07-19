import { Component } from '@angular/core';
import {ProfileService} from "../profile/profile.service";
import {CartItem, CartItemService} from "./cart.service";
import {Product, StorefrontService} from "../storefront/storefront.service";
import {FormControl, Validators} from "@angular/forms";
import {Order, OrderService} from "../orders/order.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent {
  products = new Map<string, Product>();
  cartItems = new Set<CartItem>();

  creditCard = new FormControl('', [Validators.required]);
  shippingAddress = new FormControl('', [Validators.required]);

  message!:string;

  constructor(private cartItemService:CartItemService, private storefrontService: StorefrontService, private orderService: OrderService, private router: Router){}

  ngOnInit() {
    this.storefrontService.findProducts('').subscribe(data => {
      console.log(data);
      // @ts-ignore
      data.forEach((product) => {
        this.products.set(product.id, product);
      });
    });

      this.cartItemService.getAll().subscribe(data => {
        console.log(data);
        data.forEach((item) => {
          this.cartItems.add(item);
        });
      });
  }

  remove(cartItem: CartItem) {
    this.cartItemService.remove(cartItem.productId).subscribe(data => {
      console.log(data);
      this.cartItems.delete(cartItem);
    });
  }

  getCreditCardErrorMessage() {
    if (this.creditCard.hasError('required')) {
      return 'You must enter a value';
    }

    return this.creditCard.hasError('creditCard') ? 'Not a valid creditCard' : '';
  }

  getShippingAddressErrorMessage() {
    if (this.shippingAddress.hasError('required')) {
      return 'You must enter a value';
    }

    return this.shippingAddress.hasError('shippingAddress') ? 'Not a valid shippingAddress' : '';
  }

  order() {
    this.orderService.order(new Order('', Array.from(this.cartItems), this.creditCard.value!, this.shippingAddress.value!)).subscribe((data)=> {this.message=''; this.router.navigateByUrl('/');}, (error)=>{this.message=error.error['title'];});
  }
}