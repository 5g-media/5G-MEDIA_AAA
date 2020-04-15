import { Routes } from '@angular/router';

import { auditsRoute, configurationRoute, accountingRoute, docsRoute, healthRoute, logsRoute, metricsRoute, userMgmtRoute } from './';

import { UserRouteAccessService } from 'app/core';

const ADMIN_ROUTES = [auditsRoute, configurationRoute, accountingRoute, docsRoute, healthRoute, logsRoute, ...userMgmtRoute, metricsRoute];

export const adminState: Routes = [
    {
        path: '',
        data: {
            authorities: ['ROLE_ADMIN', 'ROLE_CATALOG_INTERNAL', 'ROLE_ACCOUNTING']
        },
        canActivate: [UserRouteAccessService],
        children: [docsRoute]
    },
    {
        path: '',
        data: {
            authorities: ['ROLE_ADMIN']
        },
        canActivate: [UserRouteAccessService],
        children: ADMIN_ROUTES
    }
];
