import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { Policy } from 'app/shared/model/policy.model';
import { PolicyService } from './policy.service';
import { PolicyComponent } from './policy.component';
import { PolicyDetailComponent } from './policy-detail.component';
import { PolicyUpdateComponent } from './policy-update.component';
import { PolicyDeletePopupComponent } from './policy-delete-dialog.component';
import { IPolicy } from 'app/shared/model/policy.model';

@Injectable({ providedIn: 'root' })
export class PolicyResolve implements Resolve<IPolicy> {
    constructor(private service: PolicyService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((policy: HttpResponse<Policy>) => policy.body);
        }
        return Observable.of(new Policy());
    }
}

export const policyRoute: Routes = [
    {
        path: 'policy',
        component: PolicyComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Policies'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'policy/:id/view',
        component: PolicyDetailComponent,
        resolve: {
            policy: PolicyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Policies'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'policy/new',
        component: PolicyUpdateComponent,
        resolve: {
            policy: PolicyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Policies'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'policy/:id/edit',
        component: PolicyUpdateComponent,
        resolve: {
            policy: PolicyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Policies'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const policyPopupRoute: Routes = [
    {
        path: 'policy/:id/delete',
        component: PolicyDeletePopupComponent,
        resolve: {
            policy: PolicyResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Policies'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
