import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IResourceAdminLogin } from 'app/shared/model/resource-admin-login.model';
import { ResourceAdminLoginService } from './resource-admin-login.service';

@Component({
    selector: 'jhi-resource-admin-login-update',
    templateUrl: './resource-admin-login-update.component.html'
})
export class ResourceAdminLoginUpdateComponent implements OnInit {
    private _resourceAdminLogin: IResourceAdminLogin;
    isSaving: boolean;

    constructor(private resourceAdminLoginService: ResourceAdminLoginService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ resourceAdminLogin }) => {
            this.resourceAdminLogin = resourceAdminLogin;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.resourceAdminLogin.id !== undefined) {
            this.subscribeToSaveResponse(this.resourceAdminLoginService.update(this.resourceAdminLogin));
        } else {
            this.subscribeToSaveResponse(this.resourceAdminLoginService.create(this.resourceAdminLogin));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IResourceAdminLogin>>) {
        result.subscribe((res: HttpResponse<IResourceAdminLogin>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get resourceAdminLogin() {
        return this._resourceAdminLogin;
    }

    set resourceAdminLogin(resourceAdminLogin: IResourceAdminLogin) {
        this._resourceAdminLogin = resourceAdminLogin;
    }
}
