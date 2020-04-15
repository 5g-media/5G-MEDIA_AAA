import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAccNsSessionMetrics } from 'app/shared/model/acc-ns-session-metrics.model';
import { AccNsSessionMetricsService } from './acc-ns-session-metrics.service';
import { IAccNsSession } from 'app/shared/model/acc-ns-session.model';
import { AccNsSessionService } from 'app/entities/acc-ns-session';

@Component({
    selector: 'jhi-acc-ns-session-metrics-update',
    templateUrl: './acc-ns-session-metrics-update.component.html'
})
export class AccNsSessionMetricsUpdateComponent implements OnInit {
    private _accNsSessionMetrics: IAccNsSessionMetrics;
    isSaving: boolean;

    accnssessions: IAccNsSession[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private accNsSessionMetricsService: AccNsSessionMetricsService,
        private accNsSessionService: AccNsSessionService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ accNsSessionMetrics }) => {
            this.accNsSessionMetrics = accNsSessionMetrics;
        });
        this.accNsSessionService.query().subscribe(
            (res: HttpResponse<IAccNsSession[]>) => {
                this.accnssessions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.accNsSessionMetrics.id !== undefined) {
            this.subscribeToSaveResponse(this.accNsSessionMetricsService.update(this.accNsSessionMetrics));
        } else {
            this.subscribeToSaveResponse(this.accNsSessionMetricsService.create(this.accNsSessionMetrics));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAccNsSessionMetrics>>) {
        result.subscribe((res: HttpResponse<IAccNsSessionMetrics>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackAccNsSessionById(index: number, item: IAccNsSession) {
        return item.id;
    }
    get accNsSessionMetrics() {
        return this._accNsSessionMetrics;
    }

    set accNsSessionMetrics(accNsSessionMetrics: IAccNsSessionMetrics) {
        this._accNsSessionMetrics = accNsSessionMetrics;
    }
}
