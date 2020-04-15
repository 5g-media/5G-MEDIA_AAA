import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ICatalogToken } from 'app/shared/model/catalog-token.model';
import { CatalogTokenService } from './catalog-token.service';
import { ICatalogUser } from 'app/shared/model/catalog-user.model';
import { CatalogUserService } from 'app/entities/catalog-user';

@Component({
    selector: 'jhi-catalog-token-update',
    templateUrl: './catalog-token-update.component.html'
})
export class CatalogTokenUpdateComponent implements OnInit {
    private _catalogToken: ICatalogToken;
    isSaving: boolean;

    catalogusers: ICatalogUser[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private catalogTokenService: CatalogTokenService,
        private catalogUserService: CatalogUserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ catalogToken }) => {
            this.catalogToken = catalogToken;
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
        if (this.catalogToken.id !== undefined) {
            this.subscribeToSaveResponse(this.catalogTokenService.update(this.catalogToken));
        } else {
            this.subscribeToSaveResponse(this.catalogTokenService.create(this.catalogToken));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICatalogToken>>) {
        result.subscribe((res: HttpResponse<ICatalogToken>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get catalogToken() {
        return this._catalogToken;
    }

    set catalogToken(catalogToken: ICatalogToken) {
        this._catalogToken = catalogToken;
    }
}
