import { Component } from '@angular/core';
import {Product, StorefrontService} from "./storefront.service";

@Component({
  selector: 'app-storefront',
  templateUrl: './storefront.component.html',
  styleUrls: ['./storefront.component.css']
})
export class StorefrontComponent {
  products!: Product[];
  value!: string;

  constructor(private storefrontService: StorefrontService) {}

  ngOnInit() {
    this.storefrontService.findProducts().subscribe(data => {
      console.log(data);
      this.products = data;
    })
  }
}
