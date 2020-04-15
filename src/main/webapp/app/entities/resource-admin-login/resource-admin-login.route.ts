import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { ResourceAdminLogin } from 'app/shared/model/resource-admin-login.model';
import { ResourceAdminLoginService } from './resource-admin-login.service';
import { ResourceAdminLoginComponent } from './resource-admin-login.component';
import { ResourceAdminLoginDetailComponent } from './resource-admin-login-detail.component';
import { ResourceAdminLoginUpdateComponent } from './resource-admin-login-update.component';
import { ResourceAdminLoginDeletePopupComponent } from './resource-admin-login-delete-dialog.component';
import { IResourceAdminLogin } from 'app/shared/model/resource-admin-login.model';

@Injectable({ providedIn: 'root' })
export class ResourceAdminLoginResolve implements Resolve<IResourceAdminLogin> {
    constructor(private service: ResourceAdminLoginService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((resourceAdminLogin: HttpResponse<ResourceAdminLogin>) => resourceAdminLogin.body);
        }
        return Observable.of(new ResourceAdminLogin());
    }
}

export const resourceAdminLoginRoute: Routes = [
    {
        path: 'resource-admin-login',
        component: ResourceAdminLoginComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'ResourceAdminLogins'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resource-admin-login/:id/view',
        component: ResourceAdminLoginDetailComponent,
        resolve: {
            resourceAdminLogin: ResourceAdminLoginResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ResourceAdminLogins'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resource-admin-login/new',
        component: ResourceAdminLoginUpdateComponent,
        resolve: {
            resourceAdminLogin: ResourceAdminLoginResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ResourceAdminLogins'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resource-admin-login/:id/edit',
        component: ResourceAdminLoginUpdateComponent,
        resolve: {
            resourceAdminLogin: ResourceAdminLoginResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ResourceAdminLogins'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const resourceAdminLoginPopupRoute: Routes = [
    {
        path: 'resource-admin-login/:id/delete',
        component: ResourceAdminLoginDeletePopupComponent,
        resolve: {
            resourceAdminLogin: ResourceAdminLoginResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ResourceAdminLogins'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
