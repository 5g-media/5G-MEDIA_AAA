import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Fivegmedia3ASharedModule } from 'app/shared';
import {
    CatalogTenantComponent,
    CatalogTenantDetailComponent,
    CatalogTenantUpdateComponent,
    CatalogTenantDeletePopupComponent,
    CatalogTenantDeleteDialogComponent,
    catalogTenantRoute,
    catalogTenantPopupRoute
} from './';

const ENTITY_STATES = [...catalogTenantRoute, ...catalogTenantPopupRoute];

@NgModule({
    imports: [Fivegmedia3ASharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CatalogTenantComponent,
        CatalogTenantDetailComponent,
        CatalogTenantUpdateComponent,
        CatalogTenantDeleteDialogComponent,
        CatalogTenantDeletePopupComponent
    ],
    entryComponents: [
        CatalogTenantComponent,
        CatalogTenantUpdateComponent,
        CatalogTenantDeleteDialogComponent,
        CatalogTenantDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Fivegmedia3ACatalogTenantModule {}
