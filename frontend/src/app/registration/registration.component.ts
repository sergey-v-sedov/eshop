import { Component } from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {RegistrationProfile, RegistrationService} from "./registration.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  username = new FormControl('', [Validators.required]);
  email = new FormControl('', [Validators.required, Validators.email]);
  password = new FormControl('', [Validators.required]);
  hide = true;
  message!:string;

  constructor(private registrationService:RegistrationService, private router: Router) {}

  getUsernameErrorMessage() {
    if (this.username.hasError('required')) {
      return 'You must enter a value';
    }

    return this.username.hasError('username') ? 'Not a valid username' : '';
  }
  getEmailErrorMessage() {
    if (this.email.hasError('required')) {
      return 'You must enter a value';
    }

    return this.email.hasError('email') ? 'Not a valid email' : '';
  }

  register() {
    this.registrationService.register(new RegistrationProfile(this.username.value!, this.email.value!, this.password.value!)).subscribe((data)=> {this.message=''; this.router.navigateByUrl('/');}, (error)=>{this.message=error.error['title'];})
  }
}
