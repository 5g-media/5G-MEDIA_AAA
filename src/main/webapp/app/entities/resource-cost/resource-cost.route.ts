import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { ResourceCost } from 'app/shared/model/resource-cost.model';
import { ResourceCostService } from './resource-cost.service';
import { ResourceCostComponent } from './resource-cost.component';
import { ResourceCostDetailComponent } from './resource-cost-detail.component';
import { ResourceCostUpdateComponent } from './resource-cost-update.component';
import { ResourceCostDeletePopupComponent } from './resource-cost-delete-dialog.component';
import { IResourceCost } from 'app/shared/model/resource-cost.model';

@Injectable({ providedIn: 'root' })
export class ResourceCostResolve implements Resolve<IResourceCost> {
    constructor(private service: ResourceCostService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((resourceCost: HttpResponse<ResourceCost>) => resourceCost.body);
        }
        return Observable.of(new ResourceCost());
    }
}

export const resourceCostRoute: Routes = [
    {
        path: 'resource-cost',
        component: ResourceCostComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'ResourceCosts'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resource-cost/:id/view',
        component: ResourceCostDetailComponent,
        resolve: {
            resourceCost: ResourceCostResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ResourceCosts'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resource-cost/new',
        component: ResourceCostUpdateComponent,
        resolve: {
            resourceCost: ResourceCostResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ResourceCosts'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resource-cost/:id/edit',
        component: ResourceCostUpdateComponent,
        resolve: {
            resourceCost: ResourceCostResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ResourceCosts'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const resourceCostPopupRoute: Routes = [
    {
        path: 'resource-cost/:id/delete',
        component: ResourceCostDeletePopupComponent,
        resolve: {
            resourceCost: ResourceCostResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ResourceCosts'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
