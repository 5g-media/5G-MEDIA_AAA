import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Fivegmedia3ASharedModule } from 'app/shared';
import {
    CatalogTokenComponent,
    CatalogTokenDetailComponent,
    CatalogTokenUpdateComponent,
    CatalogTokenDeletePopupComponent,
    CatalogTokenDeleteDialogComponent,
    catalogTokenRoute,
    catalogTokenPopupRoute
} from './';

const ENTITY_STATES = [...catalogTokenRoute, ...catalogTokenPopupRoute];

@NgModule({
    imports: [Fivegmedia3ASharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CatalogTokenComponent,
        CatalogTokenDetailComponent,
        CatalogTokenUpdateComponent,
        CatalogTokenDeleteDialogComponent,
        CatalogTokenDeletePopupComponent
    ],
    entryComponents: [
        CatalogTokenComponent,
        CatalogTokenUpdateComponent,
        CatalogTokenDeleteDialogComponent,
        CatalogTokenDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Fivegmedia3ACatalogTokenModule {}
