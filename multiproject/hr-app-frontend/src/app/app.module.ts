import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
//для роутинга
import {Routes, RouterModule} from '@angular/router';


import {AppComponent} from './app.component';
import { UserComponent } from './user/user.component';
import { ColeguesTableComponent } from './colegues-table/colegues-table.component';
import {ColeguesTableService} from "./colegues-table/colegues-table.service";
import { LoginComponent } from './login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatNativeDateModule} from "@angular/material/core";
import {MaterialExampleModule} from "../material.module";
import { PointInfoModalComponent } from './modals/point-info-modal/point-info-modal.component';
import {authInterceptorProviders} from "./_hekpers/auth.interceptor";
import {TokenStorageService} from "./_services/token-storage.service";
import {AppService} from "./app.service";
import { StatusProbationModalComponent } from './modals/status-probation-modal/status-probation-modal.component';
// import {Modal} from "./modals/modal";

const appRoutes: Routes = [
  // {path: '', component: CardComponent},
  {path: 'coleguesTable', component: ColeguesTableComponent},
  // {path: '', component: ColeguesTableComponent},
  {path: 'login', component: LoginComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    ColeguesTableComponent,
    UserComponent,
    LoginComponent,
    PointInfoModalComponent,
    StatusProbationModalComponent,
    // Modal
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    BrowserAnimationsModule,
    MatNativeDateModule,
    MaterialExampleModule,
    ReactiveFormsModule
  ],
  providers: [
    ColeguesTableService,
    authInterceptorProviders,
    // AppService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
