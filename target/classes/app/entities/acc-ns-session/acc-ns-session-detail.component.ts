import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAccNsSession } from 'app/shared/model/acc-ns-session.model';

@Component({
    selector: 'jhi-acc-ns-session-detail',
    templateUrl: './acc-ns-session-detail.component.html'
})
export class AccNsSessionDetailComponent implements OnInit {
    accNsSession: IAccNsSession;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ accNsSession }) => {
            this.accNsSession = accNsSession;
        });
    }

    previousState() {
        window.history.back();
    }
}
