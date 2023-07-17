import { Component } from '@angular/core';
import {ProfileService} from "../profile/profile.service";
import {CartItemService} from "./cart.service";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent {


  constructor(private cartItemService:CartItemService){}



}