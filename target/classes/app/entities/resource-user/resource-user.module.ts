import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Fivegmedia3ASharedModule } from 'app/shared';
import {
    ResourceUserComponent,
    ResourceUserFilteredComponent,
    ResourceUserDetailComponent,
    ResourceUserUpdateComponent,
    ResourceUserDeletePopupComponent,
    ResourceUserDeleteDialogComponent,
    resourceUserRoute,
    resourceUserPopupRoute
} from './';

const ENTITY_STATES = [...resourceUserRoute, ...resourceUserPopupRoute];

@NgModule({
    imports: [Fivegmedia3ASharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ResourceUserComponent,
        ResourceUserFilteredComponent,
        ResourceUserDetailComponent,
        ResourceUserUpdateComponent,
        ResourceUserDeleteDialogComponent,
        ResourceUserDeletePopupComponent
    ],
    entryComponents: [
        ResourceUserComponent,
        ResourceUserFilteredComponent,
        ResourceUserUpdateComponent,
        ResourceUserDeleteDialogComponent,
        ResourceUserDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Fivegmedia3AResourceUserModule {}
