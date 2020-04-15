import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAccNsSessionMetrics } from 'app/shared/model/acc-ns-session-metrics.model';

@Component({
    selector: 'jhi-acc-ns-session-metrics-detail',
    templateUrl: './acc-ns-session-metrics-detail.component.html'
})
export class AccNsSessionMetricsDetailComponent implements OnInit {
    accNsSessionMetrics: IAccNsSessionMetrics;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ accNsSessionMetrics }) => {
            this.accNsSessionMetrics = accNsSessionMetrics;
        });
    }

    previousState() {
        window.history.back();
    }
}
