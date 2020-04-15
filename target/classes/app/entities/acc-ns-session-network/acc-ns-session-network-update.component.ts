import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAccNsSessionNetwork } from 'app/shared/model/acc-ns-session-network.model';
import { AccNsSessionNetworkService } from './acc-ns-session-network.service';
import { IAccNsSession } from 'app/shared/model/acc-ns-session.model';
import { AccNsSessionService } from 'app/entities/acc-ns-session';

@Component({
    selector: 'jhi-acc-ns-session-network-update',
    templateUrl: './acc-ns-session-network-update.component.html'
})
export class AccNsSessionNetworkUpdateComponent implements OnInit {
    private _accNsSessionNetwork: IAccNsSessionNetwork;
    isSaving: boolean;

    accnssessions: IAccNsSession[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private accNsSessionNetworkService: AccNsSessionNetworkService,
        private accNsSessionService: AccNsSessionService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ accNsSessionNetwork }) => {
            this.accNsSessionNetwork = accNsSessionNetwork;
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
        if (this.accNsSessionNetwork.id !== undefined) {
            this.subscribeToSaveResponse(this.accNsSessionNetworkService.update(this.accNsSessionNetwork));
        } else {
            this.subscribeToSaveResponse(this.accNsSessionNetworkService.create(this.accNsSessionNetwork));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAccNsSessionNetwork>>) {
        result.subscribe((res: HttpResponse<IAccNsSessionNetwork>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get accNsSessionNetwork() {
        return this._accNsSessionNetwork;
    }

    set accNsSessionNetwork(accNsSessionNetwork: IAccNsSessionNetwork) {
        this._accNsSessionNetwork = accNsSessionNetwork;
    }
}
