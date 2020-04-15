import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { ResourceToken } from 'app/shared/model/resource-token.model';
import { ResourceTokenService } from './resource-token.service';
import { ResourceTokenComponent } from './resource-token.component';
import { ResourceTokenDetailComponent } from './resource-token-detail.component';
import { ResourceTokenUpdateComponent } from './resource-token-update.component';
import { ResourceTokenDeletePopupComponent } from './resource-token-delete-dialog.component';
import { IResourceToken } from 'app/shared/model/resource-token.model';

@Injectable({ providedIn: 'root' })
export class ResourceTokenResolve implements Resolve<IResourceToken> {
    constructor(private service: ResourceTokenService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((resourceToken: HttpResponse<ResourceToken>) => resourceToken.body);
        }
        return Observable.of(new ResourceToken());
    }
}

export const resourceTokenRoute: Routes = [
    {
        path: 'resource-token',
        component: ResourceTokenComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'ResourceTokens'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resource-token/:id/view',
        component: ResourceTokenDetailComponent,
        resolve: {
            resourceToken: ResourceTokenResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ResourceTokens'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resource-token/new',
        component: ResourceTokenUpdateComponent,
        resolve: {
            resourceToken: ResourceTokenResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ResourceTokens'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resource-token/:id/edit',
        component: ResourceTokenUpdateComponent,
        resolve: {
            resourceToken: ResourceTokenResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ResourceTokens'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const resourceTokenPopupRoute: Routes = [
    {
        path: 'resource-token/:id/delete',
        component: ResourceTokenDeletePopupComponent,
        resolve: {
            resourceToken: ResourceTokenResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ResourceTokens'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
