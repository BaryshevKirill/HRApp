import { Component, OnInit } from '@angular/core';
import {AuthService} from "../_services/auth.service";
import {TokenStorageService} from "../_services/token-storage.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

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

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService) {
  }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      console.log("token",this.tokenStorage.getToken() )
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  onSubmit(): void {
    this.submited = true;

    const { login, password } = this.form;
    if(login == null || password == null) {
      return;
    }
    this.authService.login(login, password).subscribe(
      data => {
        console.log("внутри после полуения токена с бека", data)
        this.tokenStorage.saveToken(data.token);
        this.tokenStorage.saveUser(data);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.redirectToTable();
      },
      err => {
        this.errorMessage = err.error;
        this.isLoginFailed = true;
        this.errorStatus = err.status
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }

  redirectToTable(): void {
    window.location.href = 'coleguesTable';
  }
  //
  // unSubmit() : void {
  //   this.submited = false;
  // }
}
