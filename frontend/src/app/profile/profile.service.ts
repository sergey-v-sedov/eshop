import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable, share} from 'rxjs';
import {RegistrationProfile} from "../registration/registration.service";

export class Profile {
    id!: string;
    username!: string;
    email!: string;
    password!: string;
}

@Injectable({
    providedIn: 'root'
})
export class ProfileService {
    public baseUrl = "http://localhost:8080/api/v1";

    constructor(private httpClient: HttpClient) {}

    getCurrentProfile(): Observable<any> {
        return this.httpClient.get(this.baseUrl + "/profiles/my");
    }

    update(profile:Profile): Observable<any> {
        let observable = this.httpClient.put(this.baseUrl + "/profiles/my", profile).pipe(share());

        return observable;
    }
}