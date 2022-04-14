import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "../_services/auth.service";
import {Subscription} from "rxjs";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {AppService} from "../app.service";
import {User} from "../models/user";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {

  form: any = {
    login: null,
    password: null
  };

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  errorStatus = '';
  roles: string[] = [];

  submited = false

  aSub: Subscription

  constructor(private authService: AuthService,
              // private tokenStorage: TokenStorageService,
              private router: Router,
              private route: ActivatedRoute,
              private appService: AppService) {
  }

  ngOnInit(): void {

    this.appService.isLoggedIn.subscribe(it => this.isLoggedIn = it)
    // this.appService.currentUser.subscribe(user => this.user = user);

    if (this.authService.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.authService.getUser().roles;
    }

    this.route.queryParams.subscribe((params: Params) => {
      if(params['reg']) {
      //  blablabla
      } else if(params['accessDenied']) {
      //  blablabla
      }
    })
  }

  ngOnDestroy() {
    if(this.aSub) {
      this.aSub.unsubscribe()
    }
  }

  onSubmit(): void {
    this.submited = true;

    const {login, password} = this.form;
    if (login == null || password == null) {
      return;
    }
    this.aSub = this.authService.login(login, password).subscribe(
      data => {
        console.log("внутри после полуения токена с бека", data)
        // this.authService.saveToken(data.token);
        // this.authService.saveUser(data);
        this.appService.setIsLoggedIn(true);
        this.isLoginFailed = false;
        // this.isLoggedIn = true;
        console.log("data user in login", data)
        this.roles = this.authService.getUser().roles;

        let user = new User()
        user.setAll(data.userId, data.userName, data.userRole.name);
        this.appService.addUser(user)

        // this.appService.addUser(data)
        this.router.navigate(['/coleguesTable'])
        // console.log("this.isLoggedIn = ", this.isLoggedIn)
        // this.reloadPage()
        // this.redirectToTable();
      },
      err => {
        console.warn(err)
        this.errorMessage = err.error;
        this.isLoginFailed = true;
        this.errorStatus = err.status
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }

  // redirectToTable(): void {
  //   window.location.href = 'coleguesTable';
  // }
}
