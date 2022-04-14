import {Injectable} from "@angular/core";
import {BehaviorSubject} from "rxjs";
import {User} from "./models/user";

@Injectable({
  providedIn: 'root'
})
export class AppService {

  private userSource = new BehaviorSubject<User>(new User())
  currentUser = this.userSource.asObservable();

  private isLoginSource = new BehaviorSubject(false)
  isLoggedIn = this.isLoginSource.asObservable()

  private static _logged = false

  constructor() {
  }

  static get logged(): boolean {
    return this._logged;
  }

  static set logged(value: boolean) {
    this._logged = value;
  }

  addUser(user : User) {
    this.userSource.next(user)
  }

  setIsLoggedIn(isLogged : boolean) {
    this.isLoginSource.next(isLogged)
  }
}
