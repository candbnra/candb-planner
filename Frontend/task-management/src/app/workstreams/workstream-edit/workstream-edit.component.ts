
import { Component, OnInit } from '@angular/core';

import { Workstream } from '../workstream';
import { ActivatedRoute, Router } from '@angular/router';
import { WorkstreamService } from '../workstream.service';

@Component({
  selector: 'app-workstream-edit',
  templateUrl: './workstream-edit.component.html',
  styleUrls: ['./workstream-edit.component.css'],
})
export class WorkstreamEditComponent implements OnInit {
  workstream: Workstream;
  errorMessage: string; // server error message
  workstreamId: number;
  constructor(
    private workstreamService: WorkstreamService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.workstream = {} as Workstream;
  }

  ngOnInit() {
    const workstreamId = this.route.snapshot.params.id;
    this.workstreamService.getWorkstreamById(workstreamId).subscribe(
      (workstream) => (this.workstream = workstream),
      (error) => (this.errorMessage = error as any)
    );
  }

  onSubmit(workstream: Workstream) {
    const that = this;  
    const workstreamId = this.route.snapshot.params.id;
    this.workstreamService.updateWorkstream(workstreamId , workstream).subscribe(
      (res) => this.gotoWorkstreamDetail(workstream),
      (error) => (this.errorMessage = error as any)
    );
  }

  gotoWorkstreamDetail(workstream: Workstream) {
    this.errorMessage = null;
    this.router.navigate(['/workstreams', workstream.id]);
  }
}
