import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { ResourceUser } from 'app/shared/model/resource-user.model';
import { ResourceUserService } from './resource-user.service';
import { ResourceUserComponent } from './resource-user.component';
import { ResourceUserDetailComponent } from './resource-user-detail.component';
import { ResourceUserUpdateComponent } from './resource-user-update.component';
import { ResourceUserDeletePopupComponent } from './resource-user-delete-dialog.component';
import { IResourceUser } from 'app/shared/model/resource-user.model';
import { CatalogUserResolve } from 'app/entities/catalog-user';
import { ResourceUserFilteredComponent } from 'app/entities/resource-user/resource-user-filtered.component';

@Injectable({ providedIn: 'root' })
export class ResourceUserResolve implements Resolve<IResourceUser> {
    constructor(private service: ResourceUserService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((resourceUser: HttpResponse<ResourceUser>) => resourceUser.body);
        }
        return Observable.of(new ResourceUser());
    }
}

export const resourceUserRoute: Routes = [
    {
        path: 'resource-user',
        component: ResourceUserComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'ResourceUsers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resource-user/filterByCatalogUser/:id',
        component: ResourceUserFilteredComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams,
            catalogUser: CatalogUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'ResourceUsers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resource-user/:id/view',
        component: ResourceUserDetailComponent,
        resolve: {
            resourceUser: ResourceUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ResourceUsers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resource-user/new',
        component: ResourceUserUpdateComponent,
        resolve: {
            resourceUser: ResourceUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ResourceUsers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resource-user/:id/edit',
        component: ResourceUserUpdateComponent,
        resolve: {
            resourceUser: ResourceUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ResourceUsers'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const resourceUserPopupRoute: Routes = [
    {
        path: 'resource-user/:id/delete',
        component: ResourceUserDeletePopupComponent,
        resolve: {
            resourceUser: ResourceUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ResourceUsers'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
