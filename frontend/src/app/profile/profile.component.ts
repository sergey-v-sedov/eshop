import { Component } from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {Profile, ProfileService} from "./profile.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  userId!:string;
  username = new FormControl('', [Validators.required]);
  email = new FormControl('', [Validators.required, Validators.email]);
  password = new FormControl('', [Validators.required]);
  hide = true;
  message!:string;

  constructor(private profileService:ProfileService, private router: Router) {}

  getUsernameErrorMessage() {
    if (this.email.hasError('required')) {
      return 'You must enter a value';
    }

    return this.email.hasError('email') ? 'Not a valid email' : '';
  }
  getEmailErrorMessage() {
    if (this.email.hasError('required')) {
      return 'You must enter a value';
    }

    return this.email.hasError('email') ? 'Not a valid email' : '';
  }

  ngOnInit(): void {
    this.getProfile();
  }

  getProfile() {
    this.profileService.getCurrentProfile().subscribe((data)=> {this.userId=data.id; this.username.setValue(data.username); this.email.setValue(data.email); this.message='';}, (error)=>{this.message=error.message;});
  }

  save() {
    let newProfile = new Profile();
    newProfile.id = this.userId;
    newProfile.username = this.username.getRawValue()!;
    newProfile.email = this.email.getRawValue()!;
    if(this.password.getRawValue() != null && this.password.getRawValue()!.length > 0) {
      newProfile.password = this.password.getRawValue()!;
    }

    this.profileService.update(newProfile).subscribe((value)=>{}, (error)=>{this.message = error.message});
  }
}