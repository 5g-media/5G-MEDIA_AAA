import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Fivegmedia3ASharedModule } from 'app/shared';
import {
    CatalogUserComponent,
    CatalogUserDetailComponent,
    CatalogUserUpdateComponent,
    CatalogUserDeletePopupComponent,
    CatalogUserDeleteDialogComponent,
    catalogUserRoute,
    catalogUserPopupRoute
} from './';

const ENTITY_STATES = [...catalogUserRoute, ...catalogUserPopupRoute];

@NgModule({
    imports: [Fivegmedia3ASharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CatalogUserComponent,
        CatalogUserDetailComponent,
        CatalogUserUpdateComponent,
        CatalogUserDeleteDialogComponent,
        CatalogUserDeletePopupComponent
    ],
    entryComponents: [CatalogUserComponent, CatalogUserUpdateComponent, CatalogUserDeleteDialogComponent, CatalogUserDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Fivegmedia3ACatalogUserModule {}
