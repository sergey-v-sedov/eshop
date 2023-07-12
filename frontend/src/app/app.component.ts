import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "./login/auth.service";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(public router: Router, public authService: AuthService) {}

  logout() {
    this.authService.logout();
    this.router.navigateByUrl('/');
  }
}
