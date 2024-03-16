
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Site } from '../site';
import { SiteService } from '../site-service';

@Component({
  selector: 'app-site-add',
  templateUrl: './site-add.component.html',
  styleUrls: ['./site-add.component.css']
})
export class SiteAddComponent implements OnInit {

  site: Site = new Site();
  errorMessage: string= '';

  constructor(private siteService: SiteService, private router: Router) {
    this.site = {} as Site;
  }

  ngOnInit() {
  }

  onSubmit(site: Site) {
    site.id = 0;
    console.log("addSite");
    console.log(site);
    this.siteService.addSite(site).subscribe(
      newSite => {
        this.site = newSite;
        this.gotoSitesList();
      },
      error => this.errorMessage = error as any
    );
  }

  gotoSitesList() {
    this.router.navigate(['/sites']);
  }

}

