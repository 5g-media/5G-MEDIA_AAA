import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Fivegmedia3ASharedModule } from 'app/shared';
import {
    ResourceUserLoginComponent,
    ResourceUserLoginDetailComponent,
    ResourceUserLoginUpdateComponent,
    ResourceUserLoginDeletePopupComponent,
    ResourceUserLoginDeleteDialogComponent,
    resourceUserLoginRoute,
    resourceUserLoginPopupRoute
} from './';

const ENTITY_STATES = [...resourceUserLoginRoute, ...resourceUserLoginPopupRoute];

@NgModule({
    imports: [Fivegmedia3ASharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ResourceUserLoginComponent,
        ResourceUserLoginDetailComponent,
        ResourceUserLoginUpdateComponent,
        ResourceUserLoginDeleteDialogComponent,
        ResourceUserLoginDeletePopupComponent
    ],
    entryComponents: [
        ResourceUserLoginComponent,
        ResourceUserLoginUpdateComponent,
        ResourceUserLoginDeleteDialogComponent,
        ResourceUserLoginDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Fivegmedia3AResourceUserLoginModule {}
