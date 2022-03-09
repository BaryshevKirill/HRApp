import {ControlPointStatuses} from "../../enums/control-point-statuses";

export class ColeguesControlPoints {

  colegueId : bigint

  controlPoints : ControlPoints[]

  // constructor(colegueId : bigint, controlPoints : ControlPoints[]){
  //   this.colegueId = colegueId;
  //   this.controlPoints = controlPoints
  // }

  public coleguesControlPoints(colegueId : bigint, controlPoints : ControlPoints[]) : any {
    this.colegueId = colegueId;
    this.controlPoints = controlPoints
    return this;
  }

}

export class ControlPoints {

  status : string
  // status : string = "UNDEFINED_STATUS"

  // comment : string = ""
  comment : string

  controlPointInfo : ControlPointInfo = new ControlPointInfo()
  // controlPointInfo : ControlPointInfo  = new ControlPointInfo()

  constructor() {
    this.status = ControlPointStatuses[ControlPointStatuses.UNDEFINED];
    this.comment  = "";
    this.controlPointInfo  = new ControlPointInfo();
  }
}

class ControlPointInfo {

  id: bigint

  time_def: string

  def: string

  daysBeforeInterview: number
}

export class SaveControlPointInfo {

  userId: number;

  colegueId: bigint;

  status: string;

  comment: string;

  controlPointId: bigint;
}
