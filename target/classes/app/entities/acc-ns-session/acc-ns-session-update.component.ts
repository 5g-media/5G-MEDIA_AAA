import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAccNsSession } from 'app/shared/model/acc-ns-session.model';
import { AccNsSessionService } from './acc-ns-session.service';
import { ICatalogUser } from 'app/shared/model/catalog-user.model';
import { CatalogUserService } from 'app/entities/catalog-user';

@Component({
    selector: 'jhi-acc-ns-session-update',
    templateUrl: './acc-ns-session-update.component.html'
})
export class AccNsSessionUpdateComponent implements OnInit {
    private _accNsSession: IAccNsSession;
    isSaving: boolean;

    catalogusers: ICatalogUser[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private accNsSessionService: AccNsSessionService,
        private catalogUserService: CatalogUserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ accNsSession }) => {
            this.accNsSession = accNsSession;
        });
        this.catalogUserService.query().subscribe(
            (res: HttpResponse<ICatalogUser[]>) => {
                this.catalogusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.accNsSession.id !== undefined) {
            this.subscribeToSaveResponse(this.accNsSessionService.update(this.accNsSession));
        } else {
            this.subscribeToSaveResponse(this.accNsSessionService.create(this.accNsSession));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAccNsSession>>) {
        result.subscribe((res: HttpResponse<IAccNsSession>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCatalogUserById(index: number, item: ICatalogUser) {
        return item.id;
    }
    get accNsSession() {
        return this._accNsSession;
    }

    set accNsSession(accNsSession: IAccNsSession) {
        this._accNsSession = accNsSession;
    }
}
