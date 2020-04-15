import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAccVnfSession } from 'app/shared/model/acc-vnf-session.model';

@Component({
    selector: 'jhi-acc-vnf-session-detail',
    templateUrl: './acc-vnf-session-detail.component.html'
})
export class AccVnfSessionDetailComponent implements OnInit {
    accVnfSession: IAccVnfSession;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ accVnfSession }) => {
            this.accVnfSession = accVnfSession;
        });
    }

    previousState() {
        window.history.back();
    }
}
