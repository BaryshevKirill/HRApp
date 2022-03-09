import {Component, OnInit} from '@angular/core';
import {ColeguesTableService} from "./colegues-table.service";
import {ColeguesTable} from "../models/colegue-table/colegues-table";
import {ColeguesControlPoints, ControlPoints} from "../models/controlPoints/colegues-control-points";
import {combineLatest} from "rxjs";
import {PointsDef} from "../models/colegue-table/points-info";
import {ControlPointCardComponent} from "../control-point-card/control-point-card.component";
// import { DialogService } from './dialog/dialog.service';
import {OverlayModule} from '@angular/cdk/overlay';
import {ModalService} from "../modal/modal.service";
import {ControlPointStatuses} from "../enums/control-point-statuses";


@Component({
  selector: 'app-colegues-table',
  templateUrl: './colegues-table.component.html',
  styleUrls: ['./colegues-table.component.css'],
  providers: [ColeguesTableService],
})
export class ColeguesTableComponent implements OnInit {

  colegues: ColeguesTable[] = [];

  coleguesControlPoint: ColeguesControlPoints[] = []

  controlPointsDef: PointsDef[] = []

  choosenControlPoint: ControlPoints = new ControlPoints();

  choosenColeguesControlPoint: ColeguesControlPoints = new ColeguesControlPoints()

  radioBtn: any

  coleguesTableService: ColeguesTableService

  controlPointStatusesEnum = ControlPointStatuses

  constructor(private modalService: ModalService, coleguesTableService: ColeguesTableService) {
    // constructor(coleguesTableService: ColeguesTableService) {
    this.coleguesTableService = coleguesTableService
  }

  ngOnInit() {
    console.log("INIT")

    //TODO тут разобраться как это рабоатет и как убрать депрекейтет
    combineLatest(this.coleguesTableService.getColegues())
      .subscribe(([colegues]) => {
        this.colegues = colegues;
        let coleguesIds = colegues.map(item => {
          return item.id
        })
        console.log("ids:" + coleguesIds)

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

  openModal(id: string, colegueId: bigint, pointId: bigint) {
    // console.log("коллега айди:", colegueId)
    // console.log("before start opening", this.coleguesControlPoint)

    let value = this.coleguesControlPoint.find((it) =>
      colegueId === it.colegueId
    ) as ColeguesControlPoints;

    // console.log("after filter by colegueId", this.coleguesControlPoint)
    // console.log("Валуе после фильтров:", value)

    this.choosenColeguesControlPoint = value;
    this.choosenControlPoint = value.controlPoints.find(it => pointId == it.controlPointInfo.id) as ControlPoints;
    if (this.choosenControlPoint == null) {
      this.choosenControlPoint = new ControlPoints();
      this.choosenControlPoint.controlPointInfo.id = pointId;
    }
    // console.log("choosen: ",this.choosenControlPoint)

    this.choosenColeguesControlPoint.controlPoints[this.choosenColeguesControlPoint.controlPoints.length] = this.choosenControlPoint;
    this.modalService.open(id);
  }

  closeModal(id: string) {
    // this.choosenControlPoint = new ControlPoints();
    this.modalService.close(id);
  }

  saveModal(id: string) {
    this.coleguesTableService.saveComment(this.choosenControlPoint, this.choosenColeguesControlPoint.colegueId)
      .subscribe();
    console.log("Log save: ", this.choosenControlPoint);
    this.modalService.close(id);
  }
}
