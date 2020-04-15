import { ResourceUserService } from './resource-user.service';
import { ResourceService } from 'app/entities/resource/resource.service';
import { HttpResponse } from '@angular/common/http';
import { IResource } from './../../shared/model/resource.model';
import { ICatalogUser } from 'app/shared/model/catalog-user.model';
import { CatalogUserService } from 'app/entities/catalog-user';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IResourceUser } from 'app/shared/model/resource-user.model';

@Component({
    selector: 'jhi-resource-user-detail',
    templateUrl: './resource-user-detail.component.html'
})
export class ResourceUserDetailComponent implements OnInit {
    resourceUser: IResourceUser;
    catalogueUserData = '-';
    resourceData = '-';
    resourceUserData = '-';

    constructor(
        private activatedRoute: ActivatedRoute,
        private resourceService: ResourceService,
        private catalogueUserService: CatalogUserService,
        private resourceUserService: ResourceUserService
    ) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ resourceUser }) => {
            this.resourceUser = resourceUser;
        });
    }

    previousState() {
        window.history.back();
    }

    loadCatalogueUserByID(id: number) {
        this.catalogueUserService.find(id).subscribe((res: HttpResponse<ICatalogUser>) => (this.catalogueUserData = res.body.name));
    }
    loadResourceByID(id: number) {
        this.resourceService.find(id).subscribe((res: HttpResponse<IResource>) => (this.resourceData = res.body.name));
    }
    loadResourceUsrByID(id: number) {
        this.resourceUserService.find(id).subscribe((res: HttpResponse<IResourceUser>) => (this.resourceUserData = res.body.name));
    }
}
