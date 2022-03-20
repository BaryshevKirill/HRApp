import {Component, Inject, Injectable, ViewChild} from "@angular/core";
import {NgForm} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
// import {DialogData} from "./point-info-modal/point-info-modal.component";

export interface DialogDataBase {
}

@Injectable()
export abstract class Modal <E extends DialogDataBase> {

  public data : E
  //
  // protected constructor(@Inject(MAT_DIALOG_DATA) public data: DialogDataBase,
  //                       public dialogRef: MatDialogRef<Object>) {
  // }

  protected constructor(@Inject(MAT_DIALOG_DATA) data: E ,
                        public dialogRef: MatDialogRef<Object>) {
    this.data = data
    // console.log("constructor Modal = ", data)
    // console.log("typeof = ", typeof this.data)
  }


  @ViewChild('myForm', {static: false})
  public MyForm: NgForm;

  isClickedSave: boolean = false

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
