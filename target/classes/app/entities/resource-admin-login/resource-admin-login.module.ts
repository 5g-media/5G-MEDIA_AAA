import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Fivegmedia3ASharedModule } from 'app/shared';
import {
    ResourceAdminLoginComponent,
    ResourceAdminLoginDetailComponent,
    ResourceAdminLoginUpdateComponent,
    ResourceAdminLoginDeletePopupComponent,
    ResourceAdminLoginDeleteDialogComponent,
    resourceAdminLoginRoute,
    resourceAdminLoginPopupRoute
} from './';

const ENTITY_STATES = [...resourceAdminLoginRoute, ...resourceAdminLoginPopupRoute];

@NgModule({
    imports: [Fivegmedia3ASharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ResourceAdminLoginComponent,
        ResourceAdminLoginDetailComponent,
        ResourceAdminLoginUpdateComponent,
        ResourceAdminLoginDeleteDialogComponent,
        ResourceAdminLoginDeletePopupComponent
    ],
    entryComponents: [
        ResourceAdminLoginComponent,
        ResourceAdminLoginUpdateComponent,
        ResourceAdminLoginDeleteDialogComponent,
        ResourceAdminLoginDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Fivegmedia3AResourceAdminLoginModule {}
