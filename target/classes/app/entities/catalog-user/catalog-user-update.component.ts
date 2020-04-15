import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ICatalogUser } from 'app/shared/model/catalog-user.model';
import { CatalogUserService } from './catalog-user.service';
import { ICatalogTenant } from 'app/shared/model/catalog-tenant.model';
import { CatalogTenantService } from 'app/entities/catalog-tenant';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-catalog-user-update',
    templateUrl: './catalog-user-update.component.html'
})
export class CatalogUserUpdateComponent implements OnInit {
    private _catalogUser: ICatalogUser;
    isSaving: boolean;

    catalogtenants: ICatalogTenant[];

    users: IUser[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private userService: UserService,
        private catalogUserService: CatalogUserService,
        private catalogTenantService: CatalogTenantService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ catalogUser }) => {
            this.catalogUser = catalogUser;
        });
        this.catalogTenantService.query().subscribe(
            (res: HttpResponse<ICatalogTenant[]>) => {
                this.catalogtenants = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.catalogUser.id !== undefined) {
            this.subscribeToSaveResponse(this.catalogUserService.update(this.catalogUser));
        } else {
            this.subscribeToSaveResponse(this.catalogUserService.create(this.catalogUser));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICatalogUser>>) {
        result.subscribe((res: HttpResponse<ICatalogUser>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCatalogTenantById(index: number, item: ICatalogTenant) {
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
    get catalogUser() {
        return this._catalogUser;
    }

    set catalogUser(catalogUser: ICatalogUser) {
        this._catalogUser = catalogUser;
    }
}
