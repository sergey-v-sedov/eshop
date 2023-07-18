import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StorefrontComponent } from './storefront/storefront.component';
import { OrdersComponent } from './orders/orders.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatCardModule} from "@angular/material/card";
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import {MatMenuModule} from "@angular/material/menu";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { LoginComponent } from './login/login.component';
import {AuthService} from "./login/auth.service";
import {AuthGuard} from "./login/auth-guard.service";
import { RegistrationComponent } from './registration/registration.component';
import {AuthHttpHeadersInterceptor} from "./login/auth-http-requests.interceptor";
import {RegistrationService} from "./registration/registration.service";
import { ProfileComponent } from './profile/profile.component';
import {ProfileService} from "./profile/profile.service";
import {NgOptimizedImage} from "@angular/common";
import { CartComponent } from './cart/cart.component';
import {CartItemService} from "./cart/cart.service";
import {Error401Interceptor} from "./login/error401.interceptor";
import {OrderService} from "./orders/order.service";
import {CdkTableModule} from "@angular/cdk/table";

@NgModule({
  declarations: [
    AppComponent,
    StorefrontComponent,
    OrdersComponent,
    LoginComponent,
    RegistrationComponent,
    ProfileComponent,
    CartComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        MatToolbarModule,
        MatIconModule,
        MatButtonModule,
        MatGridListModule,
        MatFormFieldModule,
        MatInputModule,
        MatCardModule,
        MatSidenavModule,
        MatListModule,
        MatMenuModule,
        FormsModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        NgOptimizedImage,
        CdkTableModule
    ],
  providers: [
    AuthService,
    AuthGuard,
    {provide: HTTP_INTERCEPTORS,
      useClass: AuthHttpHeadersInterceptor,
      multi: true,},
    {provide: HTTP_INTERCEPTORS,
      useClass: Error401Interceptor,
      multi: true,},
    RegistrationService,
    ProfileService,
    CartItemService,
    OrderService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}