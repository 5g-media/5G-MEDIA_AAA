import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IResource } from 'app/shared/model/resource.model';
import { ResourceService } from './resource.service';
import { IResourceAdminLogin } from 'app/shared/model/resource-admin-login.model';
import { ResourceAdminLoginService } from 'app/entities/resource-admin-login';

@Component({
    selector: 'jhi-resource-update',
    templateUrl: './resource-update.component.html'
})
export class ResourceUpdateComponent implements OnInit {
    private _resource: IResource;
    isSaving: boolean;

    resourceadminlogins: IResourceAdminLogin[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private resourceService: ResourceService,
        private resourceAdminLoginService: ResourceAdminLoginService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ resource }) => {
            this.resource = resource;
        });
        this.resourceAdminLoginService.query({ filter: 'resource-is-null' }).subscribe(
            (res: HttpResponse<IResourceAdminLogin[]>) => {
                if (!this.resource.resourceAdminLoginId) {
                    this.resourceadminlogins = res.body;
                } else {
                    this.resourceAdminLoginService.find(this.resource.resourceAdminLoginId).subscribe(
                        (subRes: HttpResponse<IResourceAdminLogin>) => {
                            this.resourceadminlogins = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.resource.id !== undefined) {
            this.subscribeToSaveResponse(this.resourceService.update(this.resource));
        } else {
            this.subscribeToSaveResponse(this.resourceService.create(this.resource));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IResource>>) {
        result.subscribe((res: HttpResponse<IResource>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackResourceAdminLoginById(index: number, item: IResourceAdminLogin) {
        return item.id;
    }
    get resource() {
        return this._resource;
    }

    set resource(resource: IResource) {
        this._resource = resource;
    }
}
