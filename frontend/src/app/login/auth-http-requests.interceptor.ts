import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import {AuthService} from "./auth.service";

@Injectable()
export class AuthHttpHeadersInterceptor implements HttpInterceptor {

  constructor(private authService:AuthService) {}
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let token = this.authService.getToken();

    if(token != null) {
      const clonedRequest = req.clone({headers: req.headers
            .append('Authorization', 'Bearer ' + token)});
      return next.handle(clonedRequest);
    }

    return next.handle(req);
  }
}