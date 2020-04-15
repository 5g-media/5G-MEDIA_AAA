import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IPolicy } from 'app/shared/model/policy.model';
import { PolicyService } from './policy.service';
import { ICatalogTenant } from 'app/shared/model/catalog-tenant.model';
import { CatalogTenantService } from 'app/entities/catalog-tenant';

@Component({
    selector: 'jhi-policy-update',
    templateUrl: './policy-update.component.html'
})
export class PolicyUpdateComponent implements OnInit {
    private _policy: IPolicy;
    isSaving: boolean;

    catalogtenants: ICatalogTenant[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private policyService: PolicyService,
        private catalogTenantService: CatalogTenantService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ policy }) => {
            this.policy = policy;
        });
        this.catalogTenantService.query().subscribe(
            (res: HttpResponse<ICatalogTenant[]>) => {
                this.catalogtenants = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.policy.id !== undefined) {
            this.subscribeToSaveResponse(this.policyService.update(this.policy));
        } else {
            this.subscribeToSaveResponse(this.policyService.create(this.policy));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPolicy>>) {
        result.subscribe((res: HttpResponse<IPolicy>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get policy() {
        return this._policy;
    }

    set policy(policy: IPolicy) {
        this._policy = policy;
    }
}
