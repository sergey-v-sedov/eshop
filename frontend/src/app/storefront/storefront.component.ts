import { Component } from '@angular/core';
import {Product, StorefrontService} from "./storefront.service";
import {CartItemService} from "../cart/cart.service";
import {AuthService} from "../login/auth.service";

@Component({
  selector: 'app-storefront',
  templateUrl: './storefront.component.html',
  styleUrls: ['./storefront.component.css']
})
export class StorefrontComponent {
  products!: Product[];
  productIdsInCart = new Set<string>();
  query!: string;

  constructor(private storefrontService: StorefrontService, private cartItemService:CartItemService, private authService:AuthService) {}

  ngOnInit() {
    this.query='';
    this.search();
  }

  search() {
    this.storefrontService.findProducts(this.query).subscribe(data => {
      console.log(data);
      this.products = data;
    });

    if(this.authService.isAuth()) {
      this.cartItemService.getAll().subscribe(data => {
        console.log(data);

        data.forEach((item) => {
          this.productIdsInCart.add(item.productId);
        });
      });
    }
  }

  add(productId: string) {
    this.cartItemService.add(productId).subscribe(data => {
      console.log(data);
      this.productIdsInCart.add(productId);
    });
  }

  remove(productId: string) {
    this.cartItemService.remove(productId).subscribe(data => {
      console.log(data);
      this.productIdsInCart.delete(productId);
    });
  }
}