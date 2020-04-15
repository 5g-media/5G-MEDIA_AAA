import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { QualityProfile } from 'app/shared/model/quality-profile.model';
import { QualityProfileService } from './quality-profile.service';
import { QualityProfileComponent } from './quality-profile.component';
import { QualityProfileDetailComponent } from './quality-profile-detail.component';
import { QualityProfileUpdateComponent } from './quality-profile-update.component';
import { QualityProfileDeletePopupComponent } from './quality-profile-delete-dialog.component';
import { IQualityProfile } from 'app/shared/model/quality-profile.model';

@Injectable({ providedIn: 'root' })
export class QualityProfileResolve implements Resolve<IQualityProfile> {
    constructor(private service: QualityProfileService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((qualityProfile: HttpResponse<QualityProfile>) => qualityProfile.body);
        }
        return Observable.of(new QualityProfile());
    }
}

export const qualityProfileRoute: Routes = [
    {
        path: 'quality-profile',
        component: QualityProfileComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'QualityProfiles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'quality-profile/:id/view',
        component: QualityProfileDetailComponent,
        resolve: {
            qualityProfile: QualityProfileResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'QualityProfiles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'quality-profile/new',
        component: QualityProfileUpdateComponent,
        resolve: {
            qualityProfile: QualityProfileResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'QualityProfiles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'quality-profile/:id/edit',
        component: QualityProfileUpdateComponent,
        resolve: {
            qualityProfile: QualityProfileResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'QualityProfiles'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const qualityProfilePopupRoute: Routes = [
    {
        path: 'quality-profile/:id/delete',
        component: QualityProfileDeletePopupComponent,
        resolve: {
            qualityProfile: QualityProfileResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'QualityProfiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
