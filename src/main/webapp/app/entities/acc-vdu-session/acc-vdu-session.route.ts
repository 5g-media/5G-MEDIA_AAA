import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { AccVduSession } from 'app/shared/model/acc-vdu-session.model';
import { AccVduSessionService } from './acc-vdu-session.service';
import { AccVduSessionComponent } from './acc-vdu-session.component';
import { AccVduSessionDetailComponent } from './acc-vdu-session-detail.component';
import { AccVduSessionUpdateComponent } from './acc-vdu-session-update.component';
import { AccVduSessionDeletePopupComponent } from './acc-vdu-session-delete-dialog.component';
import { IAccVduSession } from 'app/shared/model/acc-vdu-session.model';

@Injectable({ providedIn: 'root' })
export class AccVduSessionResolve implements Resolve<IAccVduSession> {
    constructor(private service: AccVduSessionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((accVduSession: HttpResponse<AccVduSession>) => accVduSession.body);
        }
        return Observable.of(new AccVduSession());
    }
}

export const accVduSessionRoute: Routes = [
    {
        path: 'acc-vdu-session',
        component: AccVduSessionComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'AccVduSessions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acc-vdu-session/:id/view',
        component: AccVduSessionDetailComponent,
        resolve: {
            accVduSession: AccVduSessionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccVduSessions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acc-vdu-session/new',
        component: AccVduSessionUpdateComponent,
        resolve: {
            accVduSession: AccVduSessionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccVduSessions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acc-vdu-session/:id/edit',
        component: AccVduSessionUpdateComponent,
        resolve: {
            accVduSession: AccVduSessionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccVduSessions'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const accVduSessionPopupRoute: Routes = [
    {
        path: 'acc-vdu-session/:id/delete',
        component: AccVduSessionDeletePopupComponent,
        resolve: {
            accVduSession: AccVduSessionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccVduSessions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
