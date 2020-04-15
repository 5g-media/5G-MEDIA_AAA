import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAccVduResourceConsumption } from 'app/shared/model/acc-vdu-resource-consumption.model';
import { AccVduResourceConsumptionService } from './acc-vdu-resource-consumption.service';
import { IAccVduSession } from 'app/shared/model/acc-vdu-session.model';
import { AccVduSessionService } from 'app/entities/acc-vdu-session';

@Component({
    selector: 'jhi-acc-vdu-resource-consumption-update',
    templateUrl: './acc-vdu-resource-consumption-update.component.html'
})
export class AccVduResourceConsumptionUpdateComponent implements OnInit {
    private _accVduResourceConsumption: IAccVduResourceConsumption;
    isSaving: boolean;

    accvdusessions: IAccVduSession[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private accVduResourceConsumptionService: AccVduResourceConsumptionService,
        private accVduSessionService: AccVduSessionService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ accVduResourceConsumption }) => {
            this.accVduResourceConsumption = accVduResourceConsumption;
        });
        this.accVduSessionService.query().subscribe(
            (res: HttpResponse<IAccVduSession[]>) => {
                this.accvdusessions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.accVduResourceConsumption.id !== undefined) {
            this.subscribeToSaveResponse(this.accVduResourceConsumptionService.update(this.accVduResourceConsumption));
        } else {
            this.subscribeToSaveResponse(this.accVduResourceConsumptionService.create(this.accVduResourceConsumption));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAccVduResourceConsumption>>) {
        result.subscribe(
            (res: HttpResponse<IAccVduResourceConsumption>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
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

    trackAccVduSessionById(index: number, item: IAccVduSession) {
        return item.id;
    }
    get accVduResourceConsumption() {
        return this._accVduResourceConsumption;
    }

    set accVduResourceConsumption(accVduResourceConsumption: IAccVduResourceConsumption) {
        this._accVduResourceConsumption = accVduResourceConsumption;
    }
}
