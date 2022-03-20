export class User {

  private _userId = ""
  private _userName = ""
  private _userRole = ""

  constructor() {
  }

  get userId(): string {
    return this._userId;
  }

  set userId(value: string) {
    this._userId = value;
  }

  get userName(): string {
    return this._userName;
  }

  set userName(value: string) {
    this._userName = value;
  }

  get userRole(): string {
    return this._userRole;
  }

  set userRole(value: string) {
    this._userRole = value;
  }

  setAll(userId : string, userName : string, userRole : string) {
    this._userId = userId;
    this._userName = userName;
    this._userRole = userRole
  }
}
