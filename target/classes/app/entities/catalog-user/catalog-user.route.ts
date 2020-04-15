import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { CatalogUser } from 'app/shared/model/catalog-user.model';
import { CatalogUserService } from './catalog-user.service';
import { CatalogUserComponent } from './catalog-user.component';
import { CatalogUserDetailComponent } from './catalog-user-detail.component';
import { CatalogUserUpdateComponent } from './catalog-user-update.component';
import { CatalogUserDeletePopupComponent } from './catalog-user-delete-dialog.component';
import { ICatalogUser } from 'app/shared/model/catalog-user.model';

@Injectable({ providedIn: 'root' })
export class CatalogUserResolve implements Resolve<ICatalogUser> {
    constructor(private service: CatalogUserService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((catalogUser: HttpResponse<CatalogUser>) => catalogUser.body);
        }
        return Observable.of(new CatalogUser());
    }
}

export const catalogUserRoute: Routes = [
    {
        path: 'catalog-user',
        component: CatalogUserComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'CatalogUsers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'catalog-user/:id/view',
        component: CatalogUserDetailComponent,
        resolve: {
            catalogUser: CatalogUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatalogUsers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'catalog-user/new',
        component: CatalogUserUpdateComponent,
        resolve: {
            catalogUser: CatalogUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatalogUsers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'catalog-user/:id/edit',
        component: CatalogUserUpdateComponent,
        resolve: {
            catalogUser: CatalogUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatalogUsers'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const catalogUserPopupRoute: Routes = [
    {
        path: 'catalog-user/:id/delete',
        component: CatalogUserDeletePopupComponent,
        resolve: {
            catalogUser: CatalogUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatalogUsers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
