import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAccVduSession } from 'app/shared/model/acc-vdu-session.model';
import { AccVduSessionService } from './acc-vdu-session.service';
import { IAccVnfSession } from 'app/shared/model/acc-vnf-session.model';
import { AccVnfSessionService } from 'app/entities/acc-vnf-session';

@Component({
    selector: 'jhi-acc-vdu-session-update',
    templateUrl: './acc-vdu-session-update.component.html'
})
export class AccVduSessionUpdateComponent implements OnInit {
    private _accVduSession: IAccVduSession;
    isSaving: boolean;

    accvnfsessions: IAccVnfSession[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private accVduSessionService: AccVduSessionService,
        private accVnfSessionService: AccVnfSessionService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ accVduSession }) => {
            this.accVduSession = accVduSession;
        });
        this.accVnfSessionService.query().subscribe(
            (res: HttpResponse<IAccVnfSession[]>) => {
                this.accvnfsessions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.accVduSession.id !== undefined) {
            this.subscribeToSaveResponse(this.accVduSessionService.update(this.accVduSession));
        } else {
            this.subscribeToSaveResponse(this.accVduSessionService.create(this.accVduSession));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAccVduSession>>) {
        result.subscribe((res: HttpResponse<IAccVduSession>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackAccVnfSessionById(index: number, item: IAccVnfSession) {
        return item.id;
    }
    get accVduSession() {
        return this._accVduSession;
    }

    set accVduSession(accVduSession: IAccVduSession) {
        this._accVduSession = accVduSession;
    }
}
