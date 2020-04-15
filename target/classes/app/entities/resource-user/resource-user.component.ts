import { IResourceUserLogin } from './../../shared/model/resource-user-login.model';
import { ResourceUserLoginService } from './../resource-user-login/resource-user-login.service';
import { ResourceService } from 'app/entities/resource/resource.service';
import { IResource } from './../../shared/model/resource.model';
import { CatalogUserService } from 'app/entities/catalog-user';
import { ICatalogUser } from './../../shared/model/catalog-user.model';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IResourceUser } from 'app/shared/model/resource-user.model';
import { Principal } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { ResourceUserService } from './resource-user.service';

@Component({
    selector: 'jhi-resource-user',
    templateUrl: './resource-user.component.html'
})
export class ResourceUserComponent implements OnInit, OnDestroy {
    currentAccount: any;
    resourceUsers: IResourceUser[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    catalogueUserData = '-';
    resourceData = '-';
    resourceUserLoginData = '-';
    groupnameData = '-';

    constructor(
        private resourceUserService: ResourceUserService,
        private resourceUserLoginService: ResourceUserLoginService,
        private catalogueUserService: CatalogUserService,
        private resourceService: ResourceService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    loadAll() {
        this.resourceUserService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IResourceUser[]>) => this.paginateResourceUsers(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    loadCatalogueUserByID(id: number) {
        this.catalogueUserService.find(id).subscribe((res: HttpResponse<ICatalogUser>) => (this.catalogueUserData = res.body.name));
    }
    loadResourceByID(id: number) {
        this.resourceService.find(id).subscribe((res: HttpResponse<IResource>) => (this.resourceData = res.body.name));
    }

    loadResourceUserLoginByID(id: number) {
        this.resourceUserLoginService
            .find(id)
            .subscribe((res: HttpResponse<IResourceUserLogin>) => (this.resourceUserLoginData = res.body.username));
    }

    loadGroupnameByID(id: number) {
        this.resourceUserService.find(id).subscribe((res: HttpResponse<IResourceUser>) => (this.groupnameData = res.body.name));
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/resource-user'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/resource-user',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInResourceUsers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IResourceUser) {
        return item.id;
    }

    registerChangeInResourceUsers() {
        this.eventSubscriber = this.eventManager.subscribe('resourceUserListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateResourceUsers(data: IResourceUser[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.resourceUsers = data;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
