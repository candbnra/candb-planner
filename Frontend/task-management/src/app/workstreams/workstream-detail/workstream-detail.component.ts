import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Workstream} from '../workstream';
import { WorkstreamService } from '../workstream.service';



@Component({
  selector: 'app-workstream-detail',
  templateUrl: './workstream-detail.component.html',
  styleUrls: ['./workstream-detail.component.css']
})
export class WorkstreamDetailComponent implements OnInit {
  errorMessage: string;
  workstream: Workstream;

  constructor(private route: ActivatedRoute, private router: Router, private workstreamService: WorkstreamService) {
    this.workstream = {} as Workstream;
  }
  ngOnInit() {
    const workstreamId = this.route.snapshot.params.id;
    this.workstreamService.getWorkstreamById(workstreamId).subscribe(
      workstream => this.workstream = workstream,
      error => this.errorMessage = error as any);
  }

  gotoWorkstreamsList() {
    this.router.navigate(['/workstreams']);
  }

  editWorkstream() {
    this.router.navigate(['/workstreams', this.workstream.id, 'edit']);
  }

  addTeam(workstream: Workstream) {
    this.router.navigate(['/workstreams', workstream.id, 'workstreams', 'add']);
  }
}

