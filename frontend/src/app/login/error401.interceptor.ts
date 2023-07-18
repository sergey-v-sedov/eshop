import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Router} from "@angular/router";
import {Observable, tap} from "rxjs";
import {AuthService} from "./auth.service";

@Injectable()
export class Error401Interceptor implements HttpInterceptor {

    constructor(private router: Router, private authService:AuthService) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe( tap(() => {},
            (err: any) => {
                if (err instanceof HttpErrorResponse) {
                    if (err.status !== 401) {
                        return;
                    }
                    this.authService.logout();
                    this.router.navigate(['login']);
                }
            }));
    }
}