import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IResourceUser } from 'app/shared/model/resource-user.model';
import { ResourceUserService } from './resource-user.service';
import { IResourceUserLogin } from 'app/shared/model/resource-user-login.model';
import { ResourceUserLoginService } from 'app/entities/resource-user-login';
import { IResource } from 'app/shared/model/resource.model';
import { ResourceService } from 'app/entities/resource';
import { ICatalogUser } from 'app/shared/model/catalog-user.model';
import { CatalogUserService } from 'app/entities/catalog-user';

@Component({
    selector: 'jhi-resource-user-update',
    templateUrl: './resource-user-update.component.html'
})
export class ResourceUserUpdateComponent implements OnInit {
    private _resourceUser: IResourceUser;
    isSaving: boolean;

    resourceuserlogins: IResourceUserLogin[];

    resources: IResource[];
    resourceUserManos: IResourceUser[] = [];

    catalogusers: ICatalogUser[];
    tenantSuggestions: any;
    permissionsSuggestions: any;
    resourceType: any;

    manoName: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private resourceUserService: ResourceUserService,
        private resourceUserLoginService: ResourceUserLoginService,
        private resourceService: ResourceService,
        private catalogUserService: CatalogUserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ resourceUser }) => {
            this.resourceUser = resourceUser;
            if (this.resourceUser.resourceId > 0) {
                this.loadSuggestions(this.resourceUser.resourceId + ': ' + this.resourceUser.resourceId);
            }
        });
        this.resourceUserLoginService.query({ filter: 'resourceuser-is-null' }).subscribe(
            (res: HttpResponse<IResourceUserLogin[]>) => {
                if (!this.resourceUser.resourceUserLoginId) {
                    this.resourceuserlogins = res.body;
                } else {
                    this.resourceUserLoginService.find(this.resourceUser.resourceUserLoginId).subscribe(
                        (subRes: HttpResponse<IResourceUserLogin>) => {
                            this.resourceuserlogins = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.resourceService.query().subscribe(
            (res: HttpResponse<IResource[]>) => {
                this.resources = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.resourceUserService.queryByResourceType('OSM_R3').subscribe(
            (res: HttpResponse<IResourceUser[]>) => {
                this.resourceUserManos = this.resourceUserManos.concat(res.body);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.resourceUserService.queryByResourceType('OSM_R4').subscribe(
            (res: HttpResponse<IResourceUser[]>) => {
                this.resourceUserManos = this.resourceUserManos.concat(res.body);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.resourceUserService.queryByResourceType('OSM_R5').subscribe(
            (res: HttpResponse<IResourceUser[]>) => {
                this.resourceUserManos = this.resourceUserManos.concat(res.body);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.catalogUserService.query().subscribe(
            (res: HttpResponse<ICatalogUser[]>) => {
                this.catalogusers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        if (this.resourceUser.groupname != null) {
            this.resourceUserService
                .find(Number(this.resourceUser.groupname))
                .subscribe((res: HttpResponse<IResourceUser>) => (this.manoName = res.body.name));
        }
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.resourceUser.id !== undefined) {
            this.subscribeToSaveResponse(this.resourceUserService.update(this.resourceUser));
        } else {
            this.subscribeToSaveResponse(this.resourceUserService.create(this.resourceUser));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IResourceUser>>) {
        result.subscribe((res: HttpResponse<IResourceUser>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackResourceUserLoginById(index: number, item: IResourceUserLogin) {
        return item.id;
    }

    trackResourceUserById(index: number, item: IResourceUser) {
        return item.id + ' ' + item.name;
    }

    trackResourceById(index: number, item: IResource) {
        return item.id + ' ' + item.resourceEnum;
    }

    trackCatalogUserById(index: number, item: ICatalogUser) {
        return item.id;
    }
    get resourceUser() {
        return this._resourceUser;
    }

    set resourceUser(resourceUser: IResourceUser) {
        this._resourceUser = resourceUser;
    }

    loadSuggestions(id: string) {
        if (id != null && id !== 'null') {
            const resourceID = Number(id.substr(0, id.indexOf(':')));
            if (resourceID > 0) {
                this.resourceService.find(resourceID).subscribe((res: HttpResponse<IResource>) => this.buildSuggestions(res));
            } else {
                this.tenantSuggestions = '';
                this.permissionsSuggestions = '';
            }
        } else {
            this.tenantSuggestions = '';
            this.permissionsSuggestions = '';
        }
    }
    buildSuggestions(res: any) {
        if (res != null) {
            this.resourceType = res.body.resourceEnum;
            if (this.resourceType === 'OSM_R3') {
                this.tenantSuggestions = ' (OSM R3 resource, insert project (e.g. default))';
                this.permissionsSuggestions = ' (OSM R3 resource, admin permissions are: rw-project:project-admin)';
            } else if (this.resourceType === 'OSM_R4') {
                this.tenantSuggestions = '';
                this.permissionsSuggestions = ' (OSM R4 resource, admin permissions are: admin)';
            } else if (this.resourceType === 'OSM_R5') {
                this.tenantSuggestions = '';
                this.permissionsSuggestions = ' (OSM R5 resource, admin permissions are: admin)';
            } else if (this.resourceType === 'OPENSTACK') {
                this.tenantSuggestions = ' (Openstack resource, insert project, e.g. admin)';
                this.permissionsSuggestions = ' (Openstack resource, admin permissions are: admin)';
            } else {
                this.permissionsSuggestions = '';
            }
        }
    }
}
