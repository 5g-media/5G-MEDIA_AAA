import { ICatalogUserLogin } from './../../shared/model/catalog-user.model';
import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICatalogUser } from 'app/shared/model/catalog-user.model';
import { CatalogUserService } from './catalog-user.service';

@Component({
    selector: 'jhi-catalog-user-detail',
    templateUrl: './catalog-user-detail.component.html'
})
export class CatalogUserDetailComponent implements OnInit {
    catalogUser: ICatalogUser;
    userData = '-';

    constructor(private catalogUserService: CatalogUserService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catalogUser }) => {
            this.catalogUser = catalogUser;
        });
    }

    previousState() {
        window.history.back();
    }

    loadUserByID(id: number) {
        this.catalogUserService
            .findLogin(id)
            .subscribe((res: HttpResponse<ICatalogUserLogin>) => (this.userData = res.body.login + ' ' + res.body.roles));
    }
}
