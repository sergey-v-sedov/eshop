import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {share} from "rxjs";
import jwt_decode from 'jwt-decode';


@Injectable()
export class AuthService {
  public baseUrl = "/api/v1";

  constructor(private http: HttpClient) {}

  login(email:string, password:string ) {
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa(email+':'+password),
      "X-Requested-With": "XMLHttpRequest"
    };

    let observable = this.http.post<any>(this.baseUrl+'/auth', null, { headers, withCredentials: true }).pipe(share());

    observable.subscribe(
      (value) => {
        const jwt = value['jwt'];
        localStorage.setItem('jwt', jwt);
      });

    return observable;
  }

  logout() : void {
    localStorage.removeItem('jwt');
  }

  isAuth() : boolean {
    const jwt = this.getToken();

    return jwt != null;
  }

  getToken() : string | null {
    return localStorage.getItem('jwt');
  }

  getProfileId() : string {
    const jwt = this.getToken();
    const token = jwt_decode(jwt!);
    // @ts-ignore
    return token.jti;
  }
}