import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Site} from '../site';
import { SiteService } from '../site-service';
import { Workstream } from '../../workstreams/workstream';

@Component({
  selector: 'app-site-detail',
  templateUrl: './site-detail.component.html',
  styleUrls: ['./site-detail.component.css']
})
export class SiteDetailComponent implements OnInit {
  errorMessage: string = '';
  site: Site;
  workstreams: Workstream[] = [];
  constructor(private route: ActivatedRoute, private router: Router, private siteService: SiteService) {
    this.site = {} as Site;
  }
  ngOnInit() {
    const siteId = this.route.snapshot.params['id'];
    this.siteService.getSiteById(siteId).subscribe(
      site => {
        console.log("site-detail:ngOnInit -> siteService.getSiteById ...");
        console.log(site);
        console.log(site.workstreams)
        this.site = site
        this.workstreams = site.workstreams;
      },
      error => this.errorMessage = error as any);
  }
  
  gotoSitesList() {
    this.router.navigate(['/sites']);
  }

  editSite() {
    this.router.navigate(['/sites', this.site.id, 'edit']);
  }

  addTeam(site: Site) {
    this.router.navigate(['/sites', site.id, 'sitesÒ', 'add']);
  }
}

