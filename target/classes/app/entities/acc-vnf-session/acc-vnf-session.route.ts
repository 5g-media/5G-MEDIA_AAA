import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { AccVnfSession } from 'app/shared/model/acc-vnf-session.model';
import { AccVnfSessionService } from './acc-vnf-session.service';
import { AccVnfSessionComponent } from './acc-vnf-session.component';
import { AccVnfSessionDetailComponent } from './acc-vnf-session-detail.component';
import { AccVnfSessionUpdateComponent } from './acc-vnf-session-update.component';
import { AccVnfSessionDeletePopupComponent } from './acc-vnf-session-delete-dialog.component';
import { IAccVnfSession } from 'app/shared/model/acc-vnf-session.model';

@Injectable({ providedIn: 'root' })
export class AccVnfSessionResolve implements Resolve<IAccVnfSession> {
    constructor(private service: AccVnfSessionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((accVnfSession: HttpResponse<AccVnfSession>) => accVnfSession.body);
        }
        return Observable.of(new AccVnfSession());
    }
}

export const accVnfSessionRoute: Routes = [
    {
        path: 'acc-vnf-session',
        component: AccVnfSessionComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'AccVnfSessions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acc-vnf-session/:id/view',
        component: AccVnfSessionDetailComponent,
        resolve: {
            accVnfSession: AccVnfSessionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccVnfSessions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acc-vnf-session/new',
        component: AccVnfSessionUpdateComponent,
        resolve: {
            accVnfSession: AccVnfSessionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccVnfSessions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acc-vnf-session/:id/edit',
        component: AccVnfSessionUpdateComponent,
        resolve: {
            accVnfSession: AccVnfSessionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccVnfSessions'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const accVnfSessionPopupRoute: Routes = [
    {
        path: 'acc-vnf-session/:id/delete',
        component: AccVnfSessionDeletePopupComponent,
        resolve: {
            accVnfSession: AccVnfSessionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AccVnfSessions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
