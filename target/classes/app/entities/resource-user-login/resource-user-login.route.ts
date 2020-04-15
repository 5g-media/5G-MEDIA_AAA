import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { ResourceUserLogin } from 'app/shared/model/resource-user-login.model';
import { ResourceUserLoginService } from './resource-user-login.service';
import { ResourceUserLoginComponent } from './resource-user-login.component';
import { ResourceUserLoginDetailComponent } from './resource-user-login-detail.component';
import { ResourceUserLoginUpdateComponent } from './resource-user-login-update.component';
import { ResourceUserLoginDeletePopupComponent } from './resource-user-login-delete-dialog.component';
import { IResourceUserLogin } from 'app/shared/model/resource-user-login.model';

@Injectable({ providedIn: 'root' })
export class ResourceUserLoginResolve implements Resolve<IResourceUserLogin> {
    constructor(private service: ResourceUserLoginService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((resourceUserLogin: HttpResponse<ResourceUserLogin>) => resourceUserLogin.body);
        }
        return Observable.of(new ResourceUserLogin());
    }
}

export const resourceUserLoginRoute: Routes = [
    {
        path: 'resource-user-login',
        component: ResourceUserLoginComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'ResourceUserLogins'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resource-user-login/:id/view',
        component: ResourceUserLoginDetailComponent,
        resolve: {
            resourceUserLogin: ResourceUserLoginResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ResourceUserLogins'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resource-user-login/new',
        component: ResourceUserLoginUpdateComponent,
        resolve: {
            resourceUserLogin: ResourceUserLoginResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ResourceUserLogins'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resource-user-login/:id/edit',
        component: ResourceUserLoginUpdateComponent,
        resolve: {
            resourceUserLogin: ResourceUserLoginResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ResourceUserLogins'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const resourceUserLoginPopupRoute: Routes = [
    {
        path: 'resource-user-login/:id/delete',
        component: ResourceUserLoginDeletePopupComponent,
        resolve: {
            resourceUserLogin: ResourceUserLoginResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ResourceUserLogins'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
