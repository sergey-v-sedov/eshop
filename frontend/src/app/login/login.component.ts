import { Component } from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {AuthService} from "./auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email = new FormControl('', [Validators.required, Validators.email]);
  password = new FormControl('', [Validators.required]);
  hide = true;
  message!:string;

  constructor(private authService:AuthService, private router: Router) {}

  getErrorMessage() {
    if (this.email.hasError('required')) {
      return 'You must enter a value';
    }

    return this.email.hasError('email') ? 'Not a valid email' : '';
  }

  login() {
    this.authService.login(this.email.value!, this.password.value!).subscribe(
      (value) => {
        this.message='';
        this.router.navigateByUrl('/');
        },
      (error) => {
        this.message='Not valid an email or a password.';
        console.log(error.message);
      }
    );
  }
}
