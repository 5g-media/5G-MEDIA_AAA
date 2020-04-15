import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IResourceToken } from 'app/shared/model/resource-token.model';
import { ResourceTokenService } from './resource-token.service';
import { IResourceUser } from 'app/shared/model/resource-user.model';
import { ResourceUserService } from 'app/entities/resource-user';

@Component({
    selector: 'jhi-resource-token-update',
    templateUrl: './resource-token-update.component.html'
})
export class ResourceTokenUpdateComponent implements OnInit {
    private _resourceToken: IResourceToken;
    isSaving: boolean;

    resourceusers: IResourceUser[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private resourceTokenService: ResourceTokenService,
        private resourceUserService: ResourceUserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ resourceToken }) => {
            this.resourceToken = resourceToken;
        });
        this.resourceUserService.query().subscribe(
            (res: HttpResponse<IResourceUser[]>) => {
                this.resourceusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.resourceToken.id !== undefined) {
            this.subscribeToSaveResponse(this.resourceTokenService.update(this.resourceToken));
        } else {
            this.subscribeToSaveResponse(this.resourceTokenService.create(this.resourceToken));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IResourceToken>>) {
        result.subscribe((res: HttpResponse<IResourceToken>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackResourceUserById(index: number, item: IResourceUser) {
        return item.id;
    }
    get resourceToken() {
        return this._resourceToken;
    }

    set resourceToken(resourceToken: IResourceToken) {
        this._resourceToken = resourceToken;
    }
}
