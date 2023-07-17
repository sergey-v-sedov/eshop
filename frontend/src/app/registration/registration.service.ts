import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable, share} from 'rxjs';
import {AuthService} from "../login/auth.service";

export class RegistrationProfile {
  constructor(public username: string, public email: string, public password: string) {}
}

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  public baseUrl = "http://localhost:8080/api/v1";

  constructor(private httpClient: HttpClient, private authService:AuthService) {}

  register(profile:RegistrationProfile): Observable<any> {
    let observable = this.httpClient.post(this.baseUrl + "/registrations", profile).pipe(share());
    observable.subscribe((data)=> {
      this.authService.login(profile.email, profile.password);
    });

    return observable;
  }
}