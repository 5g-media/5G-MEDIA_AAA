import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { Endpoint } from 'app/shared/model/endpoint.model';
import { EndpointService } from './endpoint.service';
import { EndpointComponent } from './endpoint.component';
import { EndpointDetailComponent } from './endpoint-detail.component';
import { EndpointUpdateComponent } from './endpoint-update.component';
import { EndpointDeletePopupComponent } from './endpoint-delete-dialog.component';
import { IEndpoint } from 'app/shared/model/endpoint.model';

@Injectable({ providedIn: 'root' })
export class EndpointResolve implements Resolve<IEndpoint> {
    constructor(private service: EndpointService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((endpoint: HttpResponse<Endpoint>) => endpoint.body);
        }
        return Observable.of(new Endpoint());
    }
}

export const endpointRoute: Routes = [
    {
        path: 'endpoint',
        component: EndpointComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Endpoints'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'endpoint/:id/view',
        component: EndpointDetailComponent,
        resolve: {
            endpoint: EndpointResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Endpoints'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'endpoint/new',
        component: EndpointUpdateComponent,
        resolve: {
            endpoint: EndpointResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Endpoints'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'endpoint/:id/edit',
        component: EndpointUpdateComponent,
        resolve: {
            endpoint: EndpointResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Endpoints'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const endpointPopupRoute: Routes = [
    {
        path: 'endpoint/:id/delete',
        component: EndpointDeletePopupComponent,
        resolve: {
            endpoint: EndpointResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Endpoints'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
