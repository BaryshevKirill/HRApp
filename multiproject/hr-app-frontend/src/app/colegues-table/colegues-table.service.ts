import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {map, Observable} from "rxjs";
import {ColeguesTable} from '../models/colegue-table/colegues-table';
import {
  ColeguesControlPoints,
  ControlPoints,
  SaveControlPointInfo
} from "../models/controlPoints/colegues-control-points";
import {PointsDef} from "../models/colegue-table/points-info";
import {ControlPointStatuses} from "../enums/control-point-statuses";
import {AppComponent} from "../app.component";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class ColeguesTableService {


  private readonly getColeguesUrl: string
  private readonly getControlPointUrl: string
  private readonly getPointDefUrl: string
  private readonly saveControlPointUrl: string
  private readonly getAllUsersUrl: string

  constructor(private httpClient: HttpClient) {
    this.getColeguesUrl = '/api/InterviewInfo/findByUserId/';
    this.getControlPointUrl = '/api/ControlPoints/findByColegueId/';
    this.getPointDefUrl = '/api/ControlPoints/getAllControlPointsDef';
    this.saveControlPointUrl = '/api/ControlPoints/saveControlPointInfo';
    this.getAllUsersUrl = '/api/users/findAllUsers';
  }

  public getColegues(userId: string, selectedStates : string): Observable<ColeguesTable[]> {
    console.log("База дай мне сил")
    console.log(selectedStates)
    let queryParams = new HttpParams();
    // queryParams = queryParams.append("page",1);
    queryParams = queryParams.append("selectedStates", selectedStates)
    return this.httpClient.get<ColeguesTable[]>(this.getColeguesUrl + userId,{params : queryParams});
  }

  public getControlPointsDef(): Observable<PointsDef[]> {
    console.log("База дай мне сил")
    return this.httpClient.get<PointsDef[]>(this.getPointDefUrl);
  }

  public getControlPointsByColegue(coleguesId: string): Observable<ColeguesControlPoints> {
    return this.httpClient.get<ColeguesControlPoints>(this.getControlPointUrl + coleguesId);
    // return this.httpClient.get<ColeguesControlPoints>(this.getControlPointUrl, {
    //   params: new HttpParams().set('id', coleguesId)
    // });
  }


  public saveComment(choosenControlPoint: ControlPoints, colegueId: bigint, userId: string): Observable<SaveControlPointInfo> {
    let saveControlPoint = new SaveControlPointInfo();

    saveControlPoint.comment = choosenControlPoint.comment;
    saveControlPoint.controlPointId = choosenControlPoint.controlPointInfo.id;
    saveControlPoint.colegueId = colegueId;
    saveControlPoint.userId = +userId;
    saveControlPoint.status = choosenControlPoint.status
    console.log(saveControlPoint)
    return this.httpClient.post<SaveControlPointInfo>(this.saveControlPointUrl, saveControlPoint);
  }

  public saveProbationStatus(): Observable<SaveControlPointInfo> {
    let saveControlPoint = new SaveControlPointInfo();

    // saveControlPoint.comment = choosenControlPoint.comment;
    // saveControlPoint.controlPointId = choosenControlPoint.controlPointInfo.id;
    // saveControlPoint.colegueId = colegueId;
    // saveControlPoint.userId = 2;
    // // saveControlPoint.status = ControlPointStatuses[radioBtn]
    // saveControlPoint.status = choosenControlPoint.status
    // console.log(saveControlPoint)
    return this.httpClient.post<SaveControlPointInfo>(this.saveControlPointUrl, saveControlPoint);
  }

  public getAllUsers() : Observable<User[]>{
    return this.httpClient.get<User[]>(this.getAllUsersUrl)
  }
}
