import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IResourceUserLogin } from 'app/shared/model/resource-user-login.model';
import { ResourceUserLoginService } from './resource-user-login.service';

@Component({
    selector: 'jhi-resource-user-login-update',
    templateUrl: './resource-user-login-update.component.html'
})
export class ResourceUserLoginUpdateComponent implements OnInit {
    private _resourceUserLogin: IResourceUserLogin;
    isSaving: boolean;

    constructor(private resourceUserLoginService: ResourceUserLoginService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ resourceUserLogin }) => {
            this.resourceUserLogin = resourceUserLogin;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.resourceUserLogin.id !== undefined) {
            this.subscribeToSaveResponse(this.resourceUserLoginService.update(this.resourceUserLogin));
        } else {
            this.subscribeToSaveResponse(this.resourceUserLoginService.create(this.resourceUserLogin));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IResourceUserLogin>>) {
        result.subscribe((res: HttpResponse<IResourceUserLogin>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get resourceUserLogin() {
        return this._resourceUserLogin;
    }

    set resourceUserLogin(resourceUserLogin: IResourceUserLogin) {
        this._resourceUserLogin = resourceUserLogin;
    }
}
