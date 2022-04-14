import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {ColeguesTableComponent} from "./colegues-table/colegues-table.component";
import {LoginComponent} from "./login/login.component";
import {AuthGuard} from "./_hekpers/auth.guard";

const routes: Routes = [
  {path: 'coleguesTable', component: ColeguesTableComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent}
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
