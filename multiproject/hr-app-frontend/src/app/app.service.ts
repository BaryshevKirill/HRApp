import {Injectable} from "@angular/core";
import {BehaviorSubject} from "rxjs";
import {User} from "./models/user";

@Injectable({
  providedIn: 'root'
})
export class AppService {

  private userSource = new BehaviorSubject<User>(new User())
  currentUser = this.userSource.asObservable();

  constructor() {
  }

  addUser(user : User) {
    this.userSource.next(user)
  }
}
