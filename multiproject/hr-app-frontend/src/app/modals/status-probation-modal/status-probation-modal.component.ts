import {Component, Inject, OnInit} from '@angular/core';
import {DialogDataBase, Modal} from "../modal";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ProbationStatusId} from "../../models/colegue-table/colegues-table";

export interface StatusProbationDialogData extends DialogDataBase{
  colegueId: bigint
  status: ProbationStatusId
}

@Component({
  selector: 'app-status-probation-modal',
  templateUrl: './status-probation-modal.component.html',
  styleUrls: ['./status-probation-modal.component.css']
})
export class StatusProbationModalComponent extends Modal<StatusProbationDialogData> implements OnInit {

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

  getDefStatus() : string {
    return this.data.status.def
  }
}
