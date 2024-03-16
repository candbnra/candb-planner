import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Site } from './site';
import { HandleError, HttpErrorHandler } from '../error.service';


@Injectable()
export class SiteService {
  REST_API_URL = 'http://localhost:8080/candb/api/v1/'
  entityUrl = this.REST_API_URL + 'sites_g2r';
  workstreamEntityUrl = this.REST_API_URL + 'workstreams';

  constructor(private http: HttpClient) { 
    this.http = http;
  }

  getSites(): Observable<Site[]> {
    console.log("getSites");
    console.log("URL:" + this.entityUrl);
    return this.http
      .get<Site[]>(this.entityUrl);
  }

  getSiteById(siteId: number): Observable<Site> {
    return this.http
      .get<Site>(this.entityUrl + '/' + siteId);
  }

  addSite(site: Site): Observable<Site> {
    return this.http
      .post<Site>(this.entityUrl, site);
  }


  updateSite(siteId: string, site: Site): Observable<{}> {
    return this.http
      .put<Site>(this.entityUrl + '/' + siteId, site);
  }

  deleteSite(siteId: string): Observable<{}> {
    return this.http
      .delete<Site>(this.entityUrl + '/' + siteId);
  }
  searchSites(name: string): Observable<Site[]> {
    let url = this.entityUrl + '/searchByName';
    console.log("URL: "+ url);
    if (name !== undefined) {
      url += '?name=' + name;
    }
    console.log("searchSites by name=");
    console.log(url);
    return this.http
      .get<Site[]>(url);
  }

  searchSitesByParams(filterParams: Site): Observable<Site[]> {
    console.log("searchSitesByParams ...");
    console.log(filterParams);
    let url = this.entityUrl + '/searchByParams';
    let filterParamsAsMap = new Map<string, string>([]);
    if (filterParams.code !== undefined) {
      filterParamsAsMap.set("code", filterParams.code);
    }
    if (filterParams.name !== undefined) {
      filterParamsAsMap.set("name", filterParams.name);
    }
    if (filterParams.region !== undefined) {
      filterParamsAsMap.set("region", filterParams.region);
    }
    if (filterParams.zone !== undefined) {
      filterParamsAsMap.set("zone", filterParams.zone);
    }
    if (filterParams.area !== undefined) {
      filterParamsAsMap.set("area", filterParams.area);
    }
    if (filterParams.moeProjet !== undefined) {
      filterParamsAsMap.set("moeProjet", filterParams.moeProjet);
    }
    if (filterParams.porteurProspection !== undefined) {
      filterParamsAsMap.set("porteurProspection", filterParams.porteurProspection);
    }
    console.log("param as map=" + filterParams);

    let flag = false;
    let urlParams = ""; 
    for (let entry of filterParamsAsMap.entries()) {
      if (flag == false) {
        console.log("here flag : " + flag);
        flag = true;
        url+="?" + entry[0] + "=" + entry[1]
      } else {
        url+="&" + entry[0] + "=" + entry[1];
      }
    }
    console.log("URL="+url);
    return this.http
      .get<Site[]>(url);
  }
}
