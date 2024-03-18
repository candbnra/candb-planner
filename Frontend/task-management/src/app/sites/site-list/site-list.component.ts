import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Site } from '../site';
import { SiteService } from '../site-service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ThemePalette } from '@angular/material/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-site-list',
  templateUrl: './site-list.component.html',
  styleUrls: ['./site-list.component.css']
})


export class SiteListComponent implements OnInit {
  color: ThemePalette = 'primary';
  errorMessage: string = '';
  public site: Site = new Site();
  sites: Site[] = [];
  isSitesDataReceived: boolean = false;

  public searchForm!: FormGroup;


  constructor(
    private formBuilder: FormBuilder,
    private siteService: SiteService,
    private router: Router
  ) { }
  

  ngOnInit() {
    this.searchForm = this.formBuilder.group({
      code: ['', []],
      name: ['', []],
      area: ['', []],
      region: ['', []],
      zone: ['', []],
      etat: ['', []],
      moeProjet: ['', []],
    });
    this.searchSites();
  }


  onSelect(site: Site) {
    console.log("onSelect() : "+  site.id)
    this.router.navigate(['/sites', site.id]);
  }


  addSite() {
    this.router.navigate(['/sites/add']);
  }

  searchSites() {
    console.log("searchSites() called");
    console.log("searchForm:" + this.searchForm);
    let searchParams = new Map<string, string>([]);
    if (this.site.code !== undefined) {
      searchParams.set("code", this.site.code);
    }
    if (this.site.name !== undefined) {
      searchParams.set("name", this.site.name);
    }
    if (this.site.region !== undefined) {
      searchParams.set("region", this.site.region);
    }
    if (this.site.zone !== undefined) {
      searchParams.set("zone", this.site.zone);
    }
    if (this.site.area !== undefined) {
      searchParams.set("area", this.site.area);
    }
    if (this.site.moeProjet !== undefined) {
      searchParams.set("moeProjet", this.site.moeProjet);
    }
    if (this.site.state !== undefined) {
      searchParams.set("state", this.site.state);
    }
    if (this.site.porteurProspection !== undefined) {
      searchParams.set("porteurProspection", this.site.porteurProspection);
    }
    this.siteService.searchSites(searchParams)
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
