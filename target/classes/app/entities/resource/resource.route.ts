import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { Resource } from 'app/shared/model/resource.model';
import { ResourceService } from './resource.service';
import { ResourceComponent } from './resource.component';
import { ResourceDetailComponent } from './resource-detail.component';
import { ResourceUpdateComponent } from './resource-update.component';
import { ResourceDeletePopupComponent } from './resource-delete-dialog.component';
import { IResource } from 'app/shared/model/resource.model';

@Injectable({ providedIn: 'root' })
export class ResourceResolve implements Resolve<IResource> {
    constructor(private service: ResourceService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((resource: HttpResponse<Resource>) => resource.body);
        }
        return Observable.of(new Resource());
    }
}

export const resourceRoute: Routes = [
    {
        path: 'resource',
        component: ResourceComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Resources'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resource/:id/view',
        component: ResourceDetailComponent,
        resolve: {
            resource: ResourceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Resources'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resource/new',
        component: ResourceUpdateComponent,
        resolve: {
            resource: ResourceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Resources'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resource/:id/edit',
        component: ResourceUpdateComponent,
        resolve: {
            resource: ResourceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Resources'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const resourcePopupRoute: Routes = [
    {
        path: 'resource/:id/delete',
        component: ResourceDeletePopupComponent,
        resolve: {
            resource: ResourceResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Resources'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
