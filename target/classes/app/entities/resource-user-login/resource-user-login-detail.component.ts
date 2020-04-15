import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IResourceUserLogin } from 'app/shared/model/resource-user-login.model';

@Component({
    selector: 'jhi-resource-user-login-detail',
    templateUrl: './resource-user-login-detail.component.html'
})
export class ResourceUserLoginDetailComponent implements OnInit {
    resourceUserLogin: IResourceUserLogin;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ resourceUserLogin }) => {
            this.resourceUserLogin = resourceUserLogin;
        });
    }

    previousState() {
        window.history.back();
    }
}
