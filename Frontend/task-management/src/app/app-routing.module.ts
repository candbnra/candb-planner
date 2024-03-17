import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserLoginComponent } from './pages/user-login/user-login.component';
import { UserRegisterComponent } from './pages/user-register/user-register.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { HomeComponent } from './pages/home/home.component';
import { AuthGuard } from './services/guard/auth-guard.guard';
import { AddTaskComponent } from './pages/add-task/add-task.component';
import { UserAuthGuard } from './services/guard/user-auth.guard';
import { SiteListComponent } from './sites/site-list/site-list.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent, canActivate: [] },
  { path: 'login', component: UserLoginComponent, canActivate: [] },
  { path: 'register', component: UserRegisterComponent, canActivate: [] },
  { path: 'dashboard', component: DashboardComponent, canActivate: [] },
  { path: "add-task", component: AddTaskComponent, canActivate: [] },
  { path: "update-task/:id", component: AddTaskComponent, canActivate: [] },
  { path: 'sites', component: SiteListComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
