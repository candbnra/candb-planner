import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';


import { WorkstreamAddComponent } from './workstream-add/workstream-add.component';
import { WorkstreamDetailComponent } from './workstream-detail/workstream-detail.component';
import { WorkstreamEditComponent } from './workstream-edit/workstream-edit.component';
import { WorkstreamListComponent } from './workstream-list/workstream-list.component';
import { WorkstreamRoutingModule } from './workstream-routing.module';
import { WorkstreamService } from './workstream.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    WorkstreamRoutingModule
  ],
  declarations: [
    WorkstreamListComponent,
    WorkstreamAddComponent,
    WorkstreamDetailComponent,
    WorkstreamEditComponent
  ],
  providers: [WorkstreamService]
})
export class WorkstreamsModule {
}
