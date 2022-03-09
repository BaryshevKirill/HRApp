import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";
import { User } from '../models/user';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersUrl: string;

  public response: any;

  public responseUsers: any;

  constructor(private httpClient: HttpClient) {
    this.usersUrl = 'http://localhost:8080/users/findByName/Пукапука';
  }

  public getColegues() : Observable<User[]> {
          console.log("База дай мне сил")
    return this.httpClient.get<User[]>(this.usersUrl);
      // .subscribe((response) => {
      //   this.response = response;
      //   this.responseUsers = response
      //       console.log(this.response)
      //       console.log(this.responseUsers)
      //
      // })
    // this.httpClient.get("http://localhost:8080/users/findByName/Пукапука")
    //   .subscribe((response) => {
    //     this.response = response;
    //     console.log(this.response)
    //   });
  }

//   this.coleguesTableService.get('http://anyurl.com').subscibe(value =>{
//   // value - результат
// },
// error => {
//   // error - объект ошибки
// });

}
