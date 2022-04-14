import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {User} from "../models/user";

// const AUTH_API = 'http://localhost:8080/api/auth/';
const AUTH_API = '/api/auth/';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private token: string | null = null

  constructor(private http: HttpClient) {
  }

  login(login: string, password: string): Observable<any> {
    return this.http.post<{ userName: string, userId: string, userRole: object, token: string }>(AUTH_API + 'login', {
      login: login,
      password
    }, httpOptions)
      .pipe(
        tap(
          (data) => {
            localStorage.setItem('auth-token', data.token)
            localStorage.setItem('auth-user', JSON.stringify(data))
            this.setToken(data.token)
          }
        )
      )
  }

  register(login: string, email: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      login: login,
      email,
      password
    }, httpOptions);
  }

  setToken(token: string | null) {
    this.token = token
  }

  getToken(): string | null {
    // return this.token
  return localStorage.getItem('auth-token')
  }

  isAuthenticated(): boolean {
    return !!this.getToken()
  }

  logout() {
    console.log("LOGOUT")
    this.setToken(null)
    localStorage.clear()
  }

  public getUser(): any {
    const user = localStorage.getItem('auth-user');
    if (user) {
      return JSON.parse(user);
    }
    return {};
  }
}
