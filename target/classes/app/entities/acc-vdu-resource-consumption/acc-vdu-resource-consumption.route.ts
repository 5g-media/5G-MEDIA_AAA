import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { AccVduResourceConsumption } from 'app/shared/model/acc-vdu-resource-consumption.model';
import { AccVduResourceConsumptionService } from './acc-vdu-resource-consumption.service';
import { AccVduResourceConsumptionComponent } from './acc-vdu-resource-consumption.component';
import { AccVduResourceConsumptionDetailComponent } from './acc-vdu-resource-consumption-detail.component';
import { AccVduResourceConsumptionUpdateComponent } from './acc-vdu-resource-consumption-update.component';
import { AccVduResourceConsumptionDeletePopupComponent } from './acc-vdu-resource-consumption-delete-dialog.component';
import { IAccVduResourceConsumption } from 'app/shared/model/acc-vdu-resource-consumption.model';

@Injectable({ providedIn: 'root' })
export class AccVduResourceConsumptionResolve implements Resolve<IAccVduResourceConsumption> {
    constructor(private service: AccVduResourceConsumptionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .map((accVduResourceConsumption: HttpResponse<AccVduResourceConsumption>) => accVduResourceConsumption.body);
        }
        return Observable.of(new AccVduResourceConsumption());
    }
}

export const accVduResourceConsumptionRoute: Routes = [
    {
        path: 'acc-vdu-resource-consumption',
        component: AccVduResourceConsumptionComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'AccVduResourceConsumptions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acc-vdu-resource-consumption/:id/view',
        component: AccVduResourceConsumptionDetailComponent,
        resolve: {
            accVduResourceConsumption: AccVduResourceConsumptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccVduResourceConsumptions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acc-vdu-resource-consumption/new',
        component: AccVduResourceConsumptionUpdateComponent,
        resolve: {
            accVduResourceConsumption: AccVduResourceConsumptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccVduResourceConsumptions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acc-vdu-resource-consumption/:id/edit',
        component: AccVduResourceConsumptionUpdateComponent,
        resolve: {
            accVduResourceConsumption: AccVduResourceConsumptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccVduResourceConsumptions'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const accVduResourceConsumptionPopupRoute: Routes = [
    {
        path: 'acc-vdu-resource-consumption/:id/delete',
        component: AccVduResourceConsumptionDeletePopupComponent,
        resolve: {
            accVduResourceConsumption: AccVduResourceConsumptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccVduResourceConsumptions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
