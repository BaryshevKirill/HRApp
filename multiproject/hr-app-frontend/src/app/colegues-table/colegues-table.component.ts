import {Component, OnInit} from '@angular/core';
import {ColeguesTableService} from "./colegues-table.service";
import {ColeguesTable} from "../models/colegue-table/colegues-table";
import {ColeguesControlPoints, ControlPoints} from "../models/controlPoints/colegues-control-points";
import {combineLatest} from "rxjs";
import {PointsDef} from "../models/colegue-table/points-info";
import {ControlPointStatuses} from "../enums/control-point-statuses";
import {MatDialog} from "@angular/material/dialog";
import {PointInfoModalComponent} from "../modals/point-info-modal/point-info-modal.component";
import {authInterceptorProviders} from "../_hekpers/auth.interceptor";
import {User} from "../models/user";
import {AppService} from "../app.service";
import {StatusProbationModalComponent} from "../modals/status-probation-modal/status-probation-modal.component";
import {SearchTextService} from "./search-text.service";


@Component({
  selector: 'app-colegues-table',
  templateUrl: './colegues-table.component.html',
  styleUrls: ['./colegues-table.component.css'],
  providers: [
    ColeguesTableService,
    authInterceptorProviders
  ],
})
export class ColeguesTableComponent implements OnInit {

  colegues: ColeguesTable[] = [];

  coleguesControlPoint: ColeguesControlPoints[] = []

  controlPointsDef: PointsDef[] = []

  choosenControlPoint: ControlPoints = new ControlPoints();

  choosenColeguesControlPoint: ColeguesControlPoints = new ColeguesControlPoints()

  controlPointStatusesEnum = ControlPointStatuses

  user: User = new User()

  click = {
    "PASSED" : false,
    "FAILED" : false,
    "IN_PROGRESS" : false
  }

  selectedStates: string = ""

  allUsers : User[];

  choosenUserId : string


  constructor(private coleguesTableService: ColeguesTableService,
              public dialog: MatDialog,
              private appService: AppService,
              private searchTextService : SearchTextService) {
  }

  ngOnInit() {

    this.appService.currentUser.subscribe(user => this.user = user)

    console.log("User from nginit", this.user)

    this.coleguesTableService.getAllUsers().subscribe((allUsers)=> {
      this.allUsers = allUsers
    })

    this.choosenUserId = this.user.userId

    // TODO тут разобраться как это рабоатет и как убрать депрекейтет
    combineLatest(this.coleguesTableService.getColegues(this.user.userId, this.selectedStates))
      .subscribe(([colegues]) => {
        this.colegues = colegues;
        if(colegues == null) {
          return;
        }
        let coleguesIds = colegues.map(item => {
          return item.id
        })

        for (const colegueIdKey in coleguesIds) {
          this.coleguesTableService.getControlPointsByColegue(coleguesIds[colegueIdKey].toString()).subscribe(data => {
            this.coleguesControlPoint[this.coleguesControlPoint.length] = data != null
              ? data
              : new ColeguesControlPoints().coleguesControlPoints(coleguesIds[colegueIdKey], [])
          })
        }
      });

    if (this.controlPointsDef.length == 0) {
      this.coleguesTableService.getControlPointsDef()
        .subscribe(data => {
          this.controlPointsDef = data
        })
    }
  }



  getColorByStatus(colegueId: bigint, pointId: bigint): any {

    let value = this.coleguesControlPoint.find((it) =>
      colegueId === it.colegueId
    ) as ColeguesControlPoints;

    //TODO надо разобраться почему ошибки undefined
    if (value == undefined) {
      console.warn("Value is undefined in getColorByStatus. Exit from getColorByStatus")
      return;
    }

    let controlPoints = value.controlPoints.find(it => pointId == it.controlPointInfo.id) as ControlPoints;

    if (controlPoints == null) {
      controlPoints = new ControlPoints();
    }

    switch (controlPoints.status) {
      case ControlPointStatuses[this.controlPointStatusesEnum.FAILED] : {
        return '1px groove #fa0202'
      }
      case ControlPointStatuses[this.controlPointStatusesEnum.PASSED] : {
        return '1px groove #51d219'
      }
      default : {
        return '1px groove #fff'
      }
    }
  }

  openPointDialog(colegueId: bigint, pointId: bigint) {
    console.log(this.colegues)
    this.choosenColeguesControlPoint = this.coleguesControlPoint.find((it) =>
      colegueId === it.colegueId
    ) as ColeguesControlPoints;

    this.choosenControlPoint = this.choosenColeguesControlPoint.controlPoints.find(it => pointId == it.controlPointInfo.id) as ControlPoints;
    if (this.choosenControlPoint == null) {
      this.choosenControlPoint = new ControlPoints();
      this.choosenControlPoint.controlPointInfo.id = pointId;
      this.choosenColeguesControlPoint.controlPoints[this.choosenColeguesControlPoint.controlPoints.length] = this.choosenControlPoint;
    }

    let matDialogRef = this.dialog.open(PointInfoModalComponent, {
      data: {
        comment: this.choosenControlPoint.comment,
        status: this.choosenControlPoint.status,
        colegueId: this.choosenColeguesControlPoint.colegueId,
        probationStatus : this.colegues.find((it) => it.id == colegueId)?.probationStatusId.id
      },
    });

    matDialogRef.afterClosed().subscribe((result) => {
      console.log("result: ", result)
      if (result != null) {
        this.choosenControlPoint.comment = result.comment
        this.choosenControlPoint.status = result.status
        this.coleguesTableService.saveComment(this.choosenControlPoint, this.choosenColeguesControlPoint.colegueId, this.user.userId)
          .subscribe();
      }
    })
  }

  openProbationStatusDialog(colegueId: bigint) {
    let colegueInfo = this.colegues.find((it) => {
      return colegueId == it.id
    });

    console.log("asdadas", this.colegues)
    console.log("adasdad", colegueInfo)

    let matDialogRef = this.dialog.open(StatusProbationModalComponent, {
      data: {
        status: colegueInfo?.probationStatusId,
        colegueId: colegueId
      },
    });

    matDialogRef.afterClosed().subscribe((result) => {
      console.log("result probation: ", result)
      if (result != null) {

        console.log("result after closing", result)

        // colegueInfo.probationStatusId =
        // this.choosenControlPoint.comment = result.comment
        // this.choosenControlPoint.status = result.status
        // this.coleguesTableService.saveComment(this.choosenControlPoint, this.choosenColeguesControlPoint.colegueId)
        //   .subscribe();
      }
    })
  }

  useFilter() {
    this.selectedStates = ""
    for (let clickKey in this.click) {
      if(this.click[clickKey]) {
        this.selectedStates += this.selectedStates == "" ? clickKey : "," + clickKey
      }
    }

    // TODO тут разобраться как это рабоатет и как убрать депрекейтет
    combineLatest(this.coleguesTableService.getColegues(this.choosenUserId, this.selectedStates))
      .subscribe(([colegues]) => {
        this.colegues = colegues;
        if(colegues == null) {
          return;
        }
        let coleguesIds = colegues.map(item => {
          return item.id
        })

        for (const colegueIdKey in coleguesIds) {
          this.coleguesTableService.getControlPointsByColegue(coleguesIds[colegueIdKey].toString()).subscribe(data => {
            this.coleguesControlPoint[this.coleguesControlPoint.length] = data != null
              ? data
              : new ColeguesControlPoints().coleguesControlPoints(coleguesIds[colegueIdKey], [])
          })
        }
      });
  }

  getAdminPanel() : any {
    // console.log("get admin panel - ", this.user.userRole)
    if(this.user.userRole != "ROLE_ADMIN") {
      // console.log("Not a admin ", this.user)
      return "none";
    }
  // console.log(this.allUsers)
    return "flex"
  }

  aaaa() {
    console.log("aaaaaaaaaaaaaaaaaaaaaaaaaa")
    console.log(this.choosenUserId)
    // TODO тут разобраться как это рабоатет и как убрать депрекейтет
    combineLatest(this.coleguesTableService.getColegues(this.choosenUserId, this.selectedStates))
      .subscribe(([colegues]) => {
        this.colegues = colegues;
        if(colegues == null) {
          return;
        }
        let coleguesIds = colegues.map(item => {
          return item.id
        })

        for (const colegueIdKey in coleguesIds) {
          this.coleguesTableService.getControlPointsByColegue(coleguesIds[colegueIdKey].toString()).subscribe(data => {
            this.coleguesControlPoint[this.coleguesControlPoint.length] = data != null
              ? data
              : new ColeguesControlPoints().coleguesControlPoints(coleguesIds[colegueIdKey], [])
          })
        }
      });
  }

  updateSearchText(serachText : string) {
    // console.log("Вводим текст поиска")
    // this.searchTextService.updateSearch(serachText)
  }
}
