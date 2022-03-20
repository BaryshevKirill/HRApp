import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {ControlPointStatuses} from "../../enums/control-point-statuses";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DialogDataBase, Modal} from "../modal";

export interface PointInfoDialogData extends DialogDataBase{
  colegueId: bigint
  userId: bigint
  comment: string
  status: String
}

@Component({
  selector: 'app-point-info-modal',
  templateUrl: './point-info-modal.component.html',
  styleUrls: ['./point-info-modal.component.css']
})
export class PointInfoModalComponent extends Modal<PointInfoDialogData> implements OnInit {

  controlPointStatusesEnum = ControlPointStatuses
  isCommentEmpty: boolean = true
  // isClickedSave: boolean = false

  // @ViewChild('myForm', {static: false})
  // public MyForm: NgForm;


  // constructor(@Inject(MAT_DIALOG_DATA) data: DialogData,
  //             dialogRef: MatDialogRef<PointInfoModalComponent>) {
  //   super( data, dialogRef)
  // }

  constructor(@Inject(MAT_DIALOG_DATA) data: PointInfoDialogData,
              dialogRef: MatDialogRef<PointInfoModalComponent>) {
    super(data, dialogRef)
    // this.data = this.data as PointInfoDialogData
    console.log("constructor PointInfoModalComponent = ", data)

  }

  ngOnInit(): void {
  }

  override isValid(): any {
    // return true;
    // let val = this.data as PointInfoDialogData
    // return !(val.comment == null || val.comment == "" || val.status == ControlPointStatuses[this.controlPointStatusesEnum.UNDEFINED]);
    return !(this.data.comment == null || this.data.comment == "" || this.data.status == ControlPointStatuses[this.controlPointStatusesEnum.UNDEFINED]);
  }
}
