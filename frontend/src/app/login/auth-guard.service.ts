import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import {AuthService} from "./auth.service";
@Injectable()
export class AuthGuard implements CanActivate {
  constructor(public router: Router, public authService : AuthService) {}
  canActivate(): boolean {
    let isAuth = this.authService.isAuth();
    if (isAuth) return true;

    this.router.navigate(['login']);
    return false;
  }
}
