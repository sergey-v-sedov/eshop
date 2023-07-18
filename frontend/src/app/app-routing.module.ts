import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {StorefrontComponent} from "./storefront/storefront.component";
import {OrdersComponent} from "./orders/orders.component";
import {LoginComponent} from "./login/login.component";
import {AuthGuard} from "./login/auth-guard.service";
import {RegistrationComponent} from "./registration/registration.component";
import {ProfileComponent} from "./profile/profile.component";
import {CartComponent} from "./cart/cart.component";

const routes: Routes = [
  {path:"login", component:LoginComponent},
  {path:"registration", component:RegistrationComponent},
  {path:"profiles/my", component:ProfileComponent, canActivate: [AuthGuard]},
  {path:"cart", component:CartComponent, canActivate: [AuthGuard]},
  {path:"orders", component:OrdersComponent, canActivate: [AuthGuard]},
  {path:"**", component:StorefrontComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
