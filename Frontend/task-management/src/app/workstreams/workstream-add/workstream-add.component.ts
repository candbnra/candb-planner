
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Workstream } from '../workstream';
import { WorkstreamService } from '../workstream.service';

@Component({
  selector: 'app-workstream-add',
  templateUrl: './workstream-add.component.html',
  styleUrls: ['./workstream-add.component.css']
})
export class WorkstreamAddComponent implements OnInit {

  workstream: Workstream;
  errorMessage: string;

  constructor(private workstreamService: WorkstreamService, private router: Router) {
    this.workstream = {} as Workstream;
  }

  ngOnInit() {
  }

  onSubmit(workstream: Workstream) {
    workstream.id = null;
    console.log("addWorkstream");
    console.log(workstream);
    this.workstreamService.addWorkstream(workstream).subscribe(
      newWorkstream => {
        this.workstream = newWorkstream;
        this.gotoWorkstreamsList();
      },
      error => this.errorMessage = error as any
    );
  }

  gotoWorkstreamsList() {
    this.router.navigate(['/workstreams']);
  }

}

