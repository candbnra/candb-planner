import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import { finalize } from 'rxjs/operators';
import { Site } from '../site';
import { SiteService } from '../site-service';
import { Observable } from 'rxjs';
import { Workstream } from '../../workstreams/workstream';

@Component({
  selector: 'app-site-list',
  templateUrl: './site-list.component.html',
  styleUrls: ['./site-list.component.css']
})
export class SiteListComponent implements OnInit {
  errorMessage: string = '';
  siteFilter: Site = new Site();
  sites: Site[] = [];
  listOfWorkstreams: Workstream[] = [];
  islistOfWorkstreamsReceived: boolean = false;
  listOfSitesWithName: Site[] = [];
  isSitesDataReceived: boolean = false;

  constructor(private router: Router, private siteService: SiteService) {
    
  }
  ngOnInit() {
    console.log("here init ..")
    this.siteService.getSites().pipe(
      finalize(() => {
        this.isSitesDataReceived = true;
      })
    ).subscribe(
      sites => this.sites = sites,
      error => this.errorMessage = error as any);
    }


    onSelect(site: Site) {
      console.log("site-list:onSelect...");
      this.router.navigate(['/sites', site.id]);
    }
    

  addSite() {
    this.router.navigate(['/sites/add']);
  }

  searchByName(name: string)
  {
      console.log('inside search by name starting with ' + (name));
      if (name === '')
      {
      this.siteService.getSites()
      .subscribe(
            (sites) => {
             this.sites = sites;
            });
      }
      if (name !== '')
      {
      this.siteService.searchSites(name)
      .subscribe(
      (sites) => {

       this.sites = sites;
       console.log('this.sites ' + this.sites);

       },
       (error) =>
       {
         this.sites = [];
       }
      );

      }
  }

  searchByParams(params: Site)
  {
    console.log("here ...");  
    this.siteService.searchSitesByParams(params)
      .subscribe(
        (sites) => {
          this.sites = sites;
        },
        (error) =>
        {
          this.sites = [];
        }
      );
  }
}
