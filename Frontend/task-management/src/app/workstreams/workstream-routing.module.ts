import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {WorkstreamListComponent} from './workstream-list/workstream-list.component';
import {WorkstreamAddComponent} from './workstream-add/workstream-add.component';
import { WorkstreamEditComponent } from './workstream-edit/workstream-edit.component';
import { WorkstreamDetailComponent } from './workstream-detail/workstream-detail.component';

const workstreamRoutes: Routes = [
  {path: 'workstreams', component: WorkstreamListComponent},
  {path: 'workstreams/add', component: WorkstreamAddComponent},
  {path: 'workstreams/:id', component: WorkstreamDetailComponent},
  {path: 'workstreams/:id/edit', component: WorkstreamEditComponent}
];

@NgModule({
  imports: [RouterModule.forChild(workstreamRoutes)],
  exports: [RouterModule]
})

export class WorkstreamRoutingModule {
}




export class CompaniesRoutingModule {
}


