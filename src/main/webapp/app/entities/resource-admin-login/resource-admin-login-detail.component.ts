import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IResourceAdminLogin } from 'app/shared/model/resource-admin-login.model';

@Component({
    selector: 'jhi-resource-admin-login-detail',
    templateUrl: './resource-admin-login-detail.component.html'
})
export class ResourceAdminLoginDetailComponent implements OnInit {
    resourceAdminLogin: IResourceAdminLogin;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ resourceAdminLogin }) => {
            this.resourceAdminLogin = resourceAdminLogin;
        });
    }

    previousState() {
        window.history.back();
    }
}
