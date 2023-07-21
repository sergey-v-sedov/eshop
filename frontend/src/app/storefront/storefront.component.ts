import {Component, ElementRef, Renderer2, ViewChild} from '@angular/core';
import {Product, StorefrontService} from "./storefront.service";
import {CartItemService} from "../cart/cart.service";
import {AuthService} from "../login/auth.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-storefront',
  templateUrl: './storefront.component.html',
  styleUrls: ['./storefront.component.css']
})
export class StorefrontComponent {
  products: Product[] = [];
  productIdsInCart = new Set<string>();
  query: string = '';
  itlRate: number = 1;

  @ViewChild('searchQuery', {static: false}) searchQuery! : ElementRef;

  constructor(private storefrontService: StorefrontService, private cartItemService:CartItemService, private authService:AuthService, private activatedRoute : ActivatedRoute) {}

  ngOnInit() {
    let queryParams = this.activatedRoute.snapshot.queryParamMap;
    this.query = queryParams.has('q') ? queryParams.get("q")! : '';
    this.storefrontService.getCurencyRate('ITL').subscribe(data => {
      this.itlRate = data.rates.ITL;
      console.log('itlRate = ' + this.itlRate);
    });
  }

  ngAfterViewInit() {
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

    // A03:2021 – Injection. DOM XSS. Fix: don't work with DOM via native elements
    if(this.query.length > 0) this.searchQuery.nativeElement.innerHTML = 'Your search request: ' + this.query;
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