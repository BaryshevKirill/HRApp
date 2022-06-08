import {Component, Inject, OnInit} from '@angular/core';
import {DialogDataBase, Modal} from "../modal";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ProbationStatusId} from "../../models/colegue-table/colegues-table";

// export interface StatusProbationDialogData extends DialogDataBase{
export interface StatusProbationDialogData extends DialogDataBase{
  colegueId: bigint
  status: ProbationStatusId
}

interface RusStatus {
  id: number;
  rusDef: string;
}

@Component({
  selector: 'app-status-probation-modal',
  templateUrl: './status-probation-modal.component.html',
  styleUrls: ['./status-probation-modal.component.css']
})
export class StatusProbationModalComponent extends Modal<StatusProbationDialogData> implements OnInit {


  rusStatus : RusStatus[] = [
  ]

  constructor(@Inject(MAT_DIALOG_DATA) data: StatusProbationDialogData,
              dialogRef: MatDialogRef<StatusProbationModalComponent>) {
    super( data, dialogRef)
    console.log("constructor StatusProbationModalComponent = ", data)
  }

  ngOnInit(): void {
  }

  override isValid(): any {
    return true;
  }

  getRusStatus(id : number) : string {
    switch (id) {
      case this.probationStatusesEnum.FAILED : return "Не пройден"
      case this.probationStatusesEnum.PASSED : return "Пройден"
      case this.probationStatusesEnum.IN_PROGRESS : return "В процессе"
      default : return "Неизвестный статус"
    }
  }
}
