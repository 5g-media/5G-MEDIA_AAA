import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAccVduSession } from 'app/shared/model/acc-vdu-session.model';

@Component({
    selector: 'jhi-acc-vdu-session-detail',
    templateUrl: './acc-vdu-session-detail.component.html'
})
export class AccVduSessionDetailComponent implements OnInit {
    accVduSession: IAccVduSession;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ accVduSession }) => {
            this.accVduSession = accVduSession;
        });
    }

    previousState() {
        window.history.back();
    }
}
