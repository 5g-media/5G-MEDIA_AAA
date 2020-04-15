import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { AccNsSessionNetwork } from 'app/shared/model/acc-ns-session-network.model';
import { AccNsSessionNetworkService } from './acc-ns-session-network.service';
import { AccNsSessionNetworkComponent } from './acc-ns-session-network.component';
import { AccNsSessionNetworkDetailComponent } from './acc-ns-session-network-detail.component';
import { AccNsSessionNetworkUpdateComponent } from './acc-ns-session-network-update.component';
import { AccNsSessionNetworkDeletePopupComponent } from './acc-ns-session-network-delete-dialog.component';
import { IAccNsSessionNetwork } from 'app/shared/model/acc-ns-session-network.model';

@Injectable({ providedIn: 'root' })
export class AccNsSessionNetworkResolve implements Resolve<IAccNsSessionNetwork> {
    constructor(private service: AccNsSessionNetworkService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((accNsSessionNetwork: HttpResponse<AccNsSessionNetwork>) => accNsSessionNetwork.body);
        }
        return Observable.of(new AccNsSessionNetwork());
    }
}

export const accNsSessionNetworkRoute: Routes = [
    {
        path: 'acc-ns-session-network',
        component: AccNsSessionNetworkComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'AccNsSessionNetworks'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acc-ns-session-network/:id/view',
        component: AccNsSessionNetworkDetailComponent,
        resolve: {
            accNsSessionNetwork: AccNsSessionNetworkResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccNsSessionNetworks'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acc-ns-session-network/new',
        component: AccNsSessionNetworkUpdateComponent,
        resolve: {
            accNsSessionNetwork: AccNsSessionNetworkResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccNsSessionNetworks'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acc-ns-session-network/:id/edit',
        component: AccNsSessionNetworkUpdateComponent,
        resolve: {
            accNsSessionNetwork: AccNsSessionNetworkResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccNsSessionNetworks'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const accNsSessionNetworkPopupRoute: Routes = [
    {
        path: 'acc-ns-session-network/:id/delete',
        component: AccNsSessionNetworkDeletePopupComponent,
        resolve: {
            accNsSessionNetwork: AccNsSessionNetworkResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccNsSessionNetworks'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
