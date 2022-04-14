import {Component, Inject, Injectable, ViewChild} from "@angular/core";
import {NgForm} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ProbationStatuses} from "../enums/probation-statuses";
// import {DialogData} from "./point-info-modal/point-info-modal.component";

export interface DialogDataBase {
}

@Injectable()
export abstract class Modal <E extends DialogDataBase> {

  public data : E

  probationStatusesEnum = ProbationStatuses

  isClickedSave: boolean = false

  @ViewChild('myForm', {static: false})
  public MyForm: NgForm;


  protected constructor(@Inject(MAT_DIALOG_DATA) data: E ,
                        public dialogRef: MatDialogRef<Object>) {
    this.data = data
  }

  saveModal() {
    this.isClickedSave = true;
    if (this.isValid()) {
      this.dialogRef.close(this.data)
    } else {
      console.log("Не все обязательные поля заполнены")
    }
  }

  closeModal() {
    this.dialogRef.close(this.data)
  }

  isValid(): any {return true;}
}
