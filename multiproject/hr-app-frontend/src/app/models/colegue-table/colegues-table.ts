export class ColeguesTable {
  id : bigint
  userId : UserId
  colleagueName : string
  // position : string
  positionId : PositionId
  // department : string
  departmentId : DepartmentId
  // dateIS : string

  probationaryPeriodFrom : Date
  probationaryPeriodTo : Date
  // points : PointsInfo
}

export class DepartmentId {
  id : number
  def : string
}

export class PositionId {
  id : number
  def : string
}

export class UserId {
  userId : number
  userName : string
  userLogin : string;
}


