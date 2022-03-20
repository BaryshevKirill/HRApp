import {Component} from '@angular/core';
import {TokenStorageService} from "./_services/token-storage.service";
import {Router} from "@angular/router";
import {User} from "./models/user";
import {AppService} from "./app.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'hr-app-frontend';

  private role: string = "";
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;

  user : User;

  constructor(private tokenStorageService: TokenStorageService, router: Router, private appService : AppService) {
    this.user = new User()
      if (tokenStorageService.getToken()) {
        router.navigate(['coleguesTable']);
      } else {
        router.navigate(['login']);
      }
  }

  ngOnInit(): void {

    this.appService.currentUser.subscribe(user => this.user = user);

    console.log("INIT app.component")

    this.isLoggedIn = !!this.tokenStorageService.getToken();
    console.log("LOgged?", this.isLoggedIn)
    if (this.isLoggedIn) {
      const userFromStorage = this.tokenStorageService.getUser();
      console.log("from token storage", userFromStorage)
      let user = new User()
      user.setAll(userFromStorage.userId, userFromStorage.userName, userFromStorage.userRole.name);
      this.appService.addUser(user)
      this.showAdminBoard = "ROLE_ADMIN" == userFromStorage.userRole
    }
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}




