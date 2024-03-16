import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Workstream } from './workstream';
import { HandleError, HttpErrorHandler } from 'app/error.service';
import { environment } from 'environments/environment';

@Injectable()
export class WorkstreamService {
  entityUrl = environment.REST_API_URL + 'workstreams';

  private readonly handlerError: HandleError;

  constructor(private http: HttpClient, private httpErrorHandler: HttpErrorHandler) {
    this.handlerError = httpErrorHandler.createHandleError('WorkstreamService');
  }

  getWorkstreams(): Observable<Workstream[]> {
    console.log("getWorkstreams");
    console.log("URL:" + this.entityUrl);
    return this.http
      .get<Workstream[]>(this.entityUrl)
      .pipe(catchError(this.handlerError('getWorkstreams', [])));
  }

  getWorkstreamById(workstreamId: number): Observable<Workstream> {
    return this.http
      .get<Workstream>(this.entityUrl + '/' + workstreamId)
      .pipe(catchError(this.handlerError('getWorkstreamById', {} as Workstream)));
  }

  addWorkstream(workstream: Workstream): Observable<Workstream> {
    console.log("addWorkstream");
    console.log(workstream)
    return this.http
      .post<Workstream>(this.entityUrl, workstream)
      .pipe(catchError(this.handlerError('addWorkstream', workstream)));
  }


  updateWorkstream(workstreamId: string, workstream: Workstream): Observable<{}> {
    console.log("updateWorkstream");
    console.log(workstream)
    return this.http
      .put<Workstream>(this.entityUrl + '/' + workstreamId, workstream)
      .pipe(catchError(this.handlerError('updateWorkstream', workstream)));
  }

  deleteWorkstream(workstreamId: string): Observable<{}> {
    return this.http
      .delete<Workstream>(this.entityUrl + '/' + workstreamId)
      .pipe(catchError(this.handlerError('deleteWorkstream', [workstreamId])));
  }

  searchWorkstreams(siteCode: string): Observable<Workstream[]> {
    let url = this.entityUrl + '/searchByName';
    console.log("URL: "+ url);
    if (siteCode !== undefined) {
      url += '?siteCode=' + siteCode;
    }
    console.log("searchWorkstreams by siteCode=");
    console.log(url);
    return this.http
      .get<Workstream[]>(url)
      .pipe(catchError(this.handlerError('searcWorkstreams', [])));
  }
}
