import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { CatalogTenant } from 'app/shared/model/catalog-tenant.model';
import { CatalogTenantService } from './catalog-tenant.service';
import { CatalogTenantComponent } from './catalog-tenant.component';
import { CatalogTenantDetailComponent } from './catalog-tenant-detail.component';
import { CatalogTenantUpdateComponent } from './catalog-tenant-update.component';
import { CatalogTenantDeletePopupComponent } from './catalog-tenant-delete-dialog.component';
import { ICatalogTenant } from 'app/shared/model/catalog-tenant.model';

@Injectable({ providedIn: 'root' })
export class CatalogTenantResolve implements Resolve<ICatalogTenant> {
    constructor(private service: CatalogTenantService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((catalogTenant: HttpResponse<CatalogTenant>) => catalogTenant.body);
        }
        return Observable.of(new CatalogTenant());
    }
}

export const catalogTenantRoute: Routes = [
    {
        path: 'catalog-tenant',
        component: CatalogTenantComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'CatalogTenants'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'catalog-tenant/:id/view',
        component: CatalogTenantDetailComponent,
        resolve: {
            catalogTenant: CatalogTenantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatalogTenants'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'catalog-tenant/new',
        component: CatalogTenantUpdateComponent,
        resolve: {
            catalogTenant: CatalogTenantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatalogTenants'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'catalog-tenant/:id/edit',
        component: CatalogTenantUpdateComponent,
        resolve: {
            catalogTenant: CatalogTenantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatalogTenants'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const catalogTenantPopupRoute: Routes = [
    {
        path: 'catalog-tenant/:id/delete',
        component: CatalogTenantDeletePopupComponent,
        resolve: {
            catalogTenant: CatalogTenantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatalogTenants'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
