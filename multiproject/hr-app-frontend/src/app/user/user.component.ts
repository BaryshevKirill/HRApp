import {Component, OnInit} from '@angular/core';
import {UserService} from "./user.service";
import {User} from "../models/user";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  users: User[] = [];

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getColegues().subscribe(data => {
      this.users = data;
      console.log(this.users);
    })
    console.log(this.users);
  }

}
