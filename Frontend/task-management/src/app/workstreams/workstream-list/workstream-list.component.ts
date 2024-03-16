import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import { finalize } from 'rxjs/operators';
import { Workstream } from '../workstream';
import { WorkstreamService } from '../workstream.service';


@Component({
  selector: 'app-workstream-list',
  templateUrl: './workstream-list.component.html',
  styleUrls: ['./workstream-list.component.css']
})
export class WorkstreamListComponent implements OnInit {
  errorMessage: string;
  code: string;
  name: string;
  type: string;
  responseStatus: number;
  isInsert = false;
  workstreams: Workstream[];
  listOfWorkstreamsWithName: Workstream[];
  isWorkstreamsDataReceived: boolean = false;

  constructor(private router: Router, private workstreamService: WorkstreamService) {

  }
  ngOnInit() {
    this.workstreamService.getWorkstreams().pipe(
      finalize(() => {
        this.isWorkstreamsDataReceived = true;
      })
    ).subscribe(
      workstreams => this.workstreams = workstreams,
      error => this.errorMessage = error as any);
    }


  onSelect(workstream: Workstream) {
    this.router.navigate(['/workstreams', workstream.id]);
  }

  addWorkstream() {
    this.router.navigate(['/workstreams/add']);
  }

  searchByName(name: string)
  {
      console.log('inside search by name starting with ' + (name));
      if (name === '')
      {
      this.workstreamService.getWorkstreams()
      .subscribe(
            (workstreams) => {
             this.workstreams = workstreams;
            });
      }
      if (name !== '')
      {
      this.workstreamService.searchWorkstreams(name)
      .subscribe(
      (workstreams) => {

       this.workstreams = workstreams;
       console.log('this.workstreams ' + this.workstreams);

       },
       (error) =>
       {
         this.workstreams = null;
       }
      );

      }
  }
  deleteWorkstream(workstream: Workstream) {
    this.workstreamService.deleteWorkstream(workstream.id.toString()).subscribe(
      response => {
        //this.responseStatus = response;
        this.workstreams = this.workstreams.filter(currentItem => !(currentItem.id === workstream.id));
      },
      error => this.errorMessage = error as any);
  }

  showAddWorkstreamComponent() {
    this.isInsert = !this.isInsert;
  }

  showEditWorkstreamComponent(updatedWorkstream: Workstream) {
    this.router.navigate(['/workstreams', updatedWorkstream.id.toString(), 'edit']);
  }

  gotoHome() {
    this.router.navigate(['/welcome']);
  }
}
