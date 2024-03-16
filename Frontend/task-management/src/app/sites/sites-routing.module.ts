import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SiteListComponent} from './site-list/site-list.component';
import {SiteAddComponent} from './site-add/site-add.component';
import { SiteEditComponent } from './site-edit/site-edit.component';
import { SiteDetailComponent } from './site-detail/site-detail.component';

const siteRoutes: Routes = [
  {path: 'sites', component: SiteListComponent},
  {path: 'sites/add', component: SiteAddComponent},
  {path: 'sites/:id', component: SiteDetailComponent},
  {path: 'sites/:id/edit', component: SiteEditComponent}
];

@NgModule({
  imports: [RouterModule.forChild(siteRoutes)],
  exports: [RouterModule]
})

export class SitesRoutingModule {
}