import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { CatalogToken } from 'app/shared/model/catalog-token.model';
import { CatalogTokenService } from './catalog-token.service';
import { CatalogTokenComponent } from './catalog-token.component';
import { CatalogTokenDetailComponent } from './catalog-token-detail.component';
import { CatalogTokenUpdateComponent } from './catalog-token-update.component';
import { CatalogTokenDeletePopupComponent } from './catalog-token-delete-dialog.component';
import { ICatalogToken } from 'app/shared/model/catalog-token.model';

@Injectable({ providedIn: 'root' })
export class CatalogTokenResolve implements Resolve<ICatalogToken> {
    constructor(private service: CatalogTokenService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((catalogToken: HttpResponse<CatalogToken>) => catalogToken.body);
        }
        return Observable.of(new CatalogToken());
    }
}

export const catalogTokenRoute: Routes = [
    {
        path: 'catalog-token',
        component: CatalogTokenComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'CatalogTokens'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'catalog-token/:id/view',
        component: CatalogTokenDetailComponent,
        resolve: {
            catalogToken: CatalogTokenResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatalogTokens'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'catalog-token/new',
        component: CatalogTokenUpdateComponent,
        resolve: {
            catalogToken: CatalogTokenResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatalogTokens'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'catalog-token/:id/edit',
        component: CatalogTokenUpdateComponent,
        resolve: {
            catalogToken: CatalogTokenResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatalogTokens'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const catalogTokenPopupRoute: Routes = [
    {
        path: 'catalog-token/:id/delete',
        component: CatalogTokenDeletePopupComponent,
        resolve: {
            catalogToken: CatalogTokenResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CatalogTokens'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
