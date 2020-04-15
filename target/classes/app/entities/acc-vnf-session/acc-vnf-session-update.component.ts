import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAccVnfSession } from 'app/shared/model/acc-vnf-session.model';
import { AccVnfSessionService } from './acc-vnf-session.service';
import { IAccNsSession } from 'app/shared/model/acc-ns-session.model';
import { AccNsSessionService } from 'app/entities/acc-ns-session';

@Component({
    selector: 'jhi-acc-vnf-session-update',
    templateUrl: './acc-vnf-session-update.component.html'
})
export class AccVnfSessionUpdateComponent implements OnInit {
    private _accVnfSession: IAccVnfSession;
    isSaving: boolean;

    accnssessions: IAccNsSession[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private accVnfSessionService: AccVnfSessionService,
        private accNsSessionService: AccNsSessionService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ accVnfSession }) => {
            this.accVnfSession = accVnfSession;
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
        if (this.accVnfSession.id !== undefined) {
            this.subscribeToSaveResponse(this.accVnfSessionService.update(this.accVnfSession));
        } else {
            this.subscribeToSaveResponse(this.accVnfSessionService.create(this.accVnfSession));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAccVnfSession>>) {
        result.subscribe((res: HttpResponse<IAccVnfSession>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get accVnfSession() {
        return this._accVnfSession;
    }

    set accVnfSession(accVnfSession: IAccVnfSession) {
        this._accVnfSession = accVnfSession;
    }
}
