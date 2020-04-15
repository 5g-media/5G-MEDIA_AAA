import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { AccNsSession } from 'app/shared/model/acc-ns-session.model';
import { AccNsSessionService } from './acc-ns-session.service';
import { AccNsSessionComponent } from './acc-ns-session.component';
import { AccNsSessionDetailComponent } from './acc-ns-session-detail.component';
import { AccNsSessionUpdateComponent } from './acc-ns-session-update.component';
import { AccNsSessionDeletePopupComponent } from './acc-ns-session-delete-dialog.component';
import { IAccNsSession } from 'app/shared/model/acc-ns-session.model';

@Injectable({ providedIn: 'root' })
export class AccNsSessionResolve implements Resolve<IAccNsSession> {
    constructor(private service: AccNsSessionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((accNsSession: HttpResponse<AccNsSession>) => accNsSession.body);
        }
        return Observable.of(new AccNsSession());
    }
}

export const accNsSessionRoute: Routes = [
    {
        path: 'acc-ns-session',
        component: AccNsSessionComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'AccNsSessions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acc-ns-session/:id/view',
        component: AccNsSessionDetailComponent,
        resolve: {
            accNsSession: AccNsSessionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccNsSessions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acc-ns-session/new',
        component: AccNsSessionUpdateComponent,
        resolve: {
            accNsSession: AccNsSessionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccNsSessions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acc-ns-session/:id/edit',
        component: AccNsSessionUpdateComponent,
        resolve: {
            accNsSession: AccNsSessionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccNsSessions'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const accNsSessionPopupRoute: Routes = [
    {
        path: 'acc-ns-session/:id/delete',
        component: AccNsSessionDeletePopupComponent,
        resolve: {
            accNsSession: AccNsSessionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccNsSessions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
