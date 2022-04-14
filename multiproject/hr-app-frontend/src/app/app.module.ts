import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {MatNativeDateModule} from "@angular/material/core";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {AppComponent} from './app.component';
import { UserComponent } from './user/user.component';
import { ColeguesTableComponent } from './colegues-table/colegues-table.component';
import {ColeguesTableService} from "./colegues-table/colegues-table.service";
import { LoginComponent } from './login/login.component';
import {MaterialExampleModule} from "../material.module";
import { PointInfoModalComponent } from './modals/point-info-modal/point-info-modal.component';
import {authInterceptorProviders} from "./_hekpers/auth.interceptor";
import { StatusProbationModalComponent } from './modals/status-probation-modal/status-probation-modal.component';
import {AppRoutingModule} from "./app-routing.module";

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    ColeguesTableComponent,
    UserComponent,
    LoginComponent,
    PointInfoModalComponent,
    StatusProbationModalComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatNativeDateModule,
    MaterialExampleModule,
    ReactiveFormsModule
  ],
  providers: [
    ColeguesTableService,
    authInterceptorProviders,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
