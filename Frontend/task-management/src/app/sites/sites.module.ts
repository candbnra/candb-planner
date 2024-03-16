import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';

import {SiteService} from './site-service';
import { SiteAddComponent } from './site-add/site-add.component';
import { SiteDetailComponent } from './site-detail/site-detail.component';
import { SiteEditComponent } from './site-edit/site-edit.component';
import { SiteListComponent } from './site-list/site-list.component';
import { SitesRoutingModule } from './sites-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    SitesRoutingModule
  ],
  declarations: [
    SiteListComponent,
    SiteAddComponent,
    SiteDetailComponent,
    SiteEditComponent
  ],
  providers: [SiteService]
})
export class SitesModule {
}
