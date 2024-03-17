import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Site } from './site';
import { HandleError, HttpErrorHandler } from '../error.service';
import { FormGroup } from '@angular/forms';


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


  searchSites(searchParams: Map<string, string>): Observable<Site[]> {
    console.log("siteService.searchSites ...");
    console.log(searchParams);
    let url = this.entityUrl + '/searchByParams';
    let flag = false;
    for (let entry of searchParams.entries()) {
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
