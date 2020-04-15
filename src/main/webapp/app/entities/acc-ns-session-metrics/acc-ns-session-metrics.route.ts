import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { AccNsSessionMetrics } from 'app/shared/model/acc-ns-session-metrics.model';
import { AccNsSessionMetricsService } from './acc-ns-session-metrics.service';
import { AccNsSessionMetricsComponent } from './acc-ns-session-metrics.component';
import { AccNsSessionMetricsDetailComponent } from './acc-ns-session-metrics-detail.component';
import { AccNsSessionMetricsUpdateComponent } from './acc-ns-session-metrics-update.component';
import { AccNsSessionMetricsDeletePopupComponent } from './acc-ns-session-metrics-delete-dialog.component';
import { IAccNsSessionMetrics } from 'app/shared/model/acc-ns-session-metrics.model';

@Injectable({ providedIn: 'root' })
export class AccNsSessionMetricsResolve implements Resolve<IAccNsSessionMetrics> {
    constructor(private service: AccNsSessionMetricsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((accNsSessionMetrics: HttpResponse<AccNsSessionMetrics>) => accNsSessionMetrics.body);
        }
        return Observable.of(new AccNsSessionMetrics());
    }
}

export const accNsSessionMetricsRoute: Routes = [
    {
        path: 'acc-ns-session-metrics',
        component: AccNsSessionMetricsComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'AccNsSessionMetrics'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acc-ns-session-metrics/:id/view',
        component: AccNsSessionMetricsDetailComponent,
        resolve: {
            accNsSessionMetrics: AccNsSessionMetricsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccNsSessionMetrics'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acc-ns-session-metrics/new',
        component: AccNsSessionMetricsUpdateComponent,
        resolve: {
            accNsSessionMetrics: AccNsSessionMetricsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccNsSessionMetrics'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acc-ns-session-metrics/:id/edit',
        component: AccNsSessionMetricsUpdateComponent,
        resolve: {
            accNsSessionMetrics: AccNsSessionMetricsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccNsSessionMetrics'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const accNsSessionMetricsPopupRoute: Routes = [
    {
        path: 'acc-ns-session-metrics/:id/delete',
        component: AccNsSessionMetricsDeletePopupComponent,
        resolve: {
            accNsSessionMetrics: AccNsSessionMetricsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccNsSessionMetrics'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
