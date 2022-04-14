import {HTTP_INTERCEPTORS, HttpErrorResponse, HttpEvent, HttpResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {HttpInterceptor, HttpHandler, HttpRequest} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {map} from 'rxjs/operators';
import {AuthService} from "../_services/auth.service";
import {Router} from "@angular/router";
import {AppService} from "../app.service";

const TOKEN_HEADER_KEY = 'Authorization';       // for Spring Boot back-end

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService,
              private router: Router,
              private appService: AppService) {
  }

//   intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
//     let authReq = req;
//     const token = this.token.getToken();
//     if (token != null) {
//       authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, token)});
//       }
//     return next.handle(authReq);
//   }
// }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    const token = this.authService.getToken();
    if (token != null) {
      authReq = req.clone({headers: req.headers.set(TOKEN_HEADER_KEY, token)});
    }
    return next.handle(authReq).pipe(
      catchError(
        (err: HttpErrorResponse) => this.handleAuthErrors(err)
      )


      // map((event: HttpEvent<any>) => {
      //   if (event instanceof HttpResponse) {
      //     console.log('event--->>>', event);
      //     // this.errorDialogService.openDialog(event);
      //   }
      //   return event;
      // }),
      // catchError((error: HttpErrorResponse) => {
      //   let data = {};
      //   data = {
      //     reason: error && error.error && error.error.reason ? error.error.reason : '',
      //     status: error.status
      //   };
      //
      //   // this.errorDialogService.openDialog(data);
      //   console.log('Error event--->>>', data)
      //   return throwError(error);
      // }
      // )
    );
  }

  private handleAuthErrors(err: HttpErrorResponse): Observable<any> {
    if(err.status === 401) {
      this.authService.logout()
      this.appService.setIsLoggedIn(false);
      this.router.navigate(['/login'])
    }
    return throwError(err)
  }
}


export const authInterceptorProviders = [
  {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
];
