import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ICatalogTenant } from 'app/shared/model/catalog-tenant.model';
import { CatalogTenantService } from './catalog-tenant.service';
import { ICatalogUser } from 'app/shared/model/catalog-user.model';
import { CatalogUserService } from 'app/entities/catalog-user';

@Component({
    selector: 'jhi-catalog-tenant-update',
    templateUrl: './catalog-tenant-update.component.html'
})
export class CatalogTenantUpdateComponent implements OnInit {
    private _catalogTenant: ICatalogTenant;
    isSaving: boolean;

    catalogusers: ICatalogUser[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private catalogTenantService: CatalogTenantService,
        private catalogUserService: CatalogUserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ catalogTenant }) => {
            this.catalogTenant = catalogTenant;
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
        if (this.catalogTenant.id !== undefined) {
            this.subscribeToSaveResponse(this.catalogTenantService.update(this.catalogTenant));
        } else {
            this.subscribeToSaveResponse(this.catalogTenantService.create(this.catalogTenant));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICatalogTenant>>) {
        result.subscribe((res: HttpResponse<ICatalogTenant>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
    get catalogTenant() {
        return this._catalogTenant;
    }

    set catalogTenant(catalogTenant: ICatalogTenant) {
        this._catalogTenant = catalogTenant;
    }
}
