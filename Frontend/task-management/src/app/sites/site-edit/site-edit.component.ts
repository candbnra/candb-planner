
import { Component, OnInit } from '@angular/core';

import { Site } from '../site';
import { ActivatedRoute, Router } from '@angular/router';
import { SiteService } from '../site-service';

@Component({
  selector: 'app-site-edit',
  templateUrl: './site-edit.component.html',
  styleUrls: ['./site-edit.component.css'],
})
export class SiteEditComponent implements OnInit {
  site: Site;
  errorMessage: string = ''; // server error message
  siteId: number = 0;
  constructor(
    private siteService: SiteService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.site = {} as Site;
  }

  ngOnInit() {
    const siteId = this.route.snapshot.params['id'];
    this.siteService.getSiteById(siteId).subscribe(
      (site) => (this.site = site),
      (error) => (this.errorMessage = error as any)
    );
  }

  onSubmit(site: Site) {
    const that = this;  
    const siteId = this.route.snapshot.params['id'];
    this.siteService.updateSite(siteId , site).subscribe(
      (res) => this.gotoSiteDetail(site),
      (error) => (this.errorMessage = error as any)
    );
  }

  gotoSiteDetail(site: Site) {
    this.errorMessage = '';
    this.router.navigate(['/sites', site.id]);
  }
}

