import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {NgbPaginationModule, NgbAlertModule, NgbModule} from '@ng-bootstrap/ng-bootstrap';
//для роутинга
import {Routes, RouterModule} from '@angular/router';
import { OverlayModule } from '@angular/cdk/overlay';



import {AppComponent} from './app.component';
import { FormComponent } from './form/form.component';
import { UserComponent } from './user/user.component';
import { ColeguesTableComponent } from './colegues-table/colegues-table.component';
import {ColeguesTableService} from "./colegues-table/colegues-table.service";
import { LoginComponent } from './login/login.component';
import { ControlPointCardComponent } from './control-point-card/control-point-card.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {ModalComponent} from "./modal/modal.component";

const appRoutes: Routes = [
  // {path: '', component: CardComponent},
  // {path: 'coleguesTable', component: ColeguesTableComponent},
  {path: '', component: ColeguesTableComponent},
  {path: 'login', component: LoginComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    FormComponent,
    UserComponent,
    ColeguesTableComponent,
    UserComponent,
    LoginComponent,
    ControlPointCardComponent,
    ModalComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    NgbPaginationModule,
    NgbAlertModule,
    NgbModule,
    RouterModule.forRoot(appRoutes),
    BrowserAnimationsModule
  ],
  providers: [ColeguesTableService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
