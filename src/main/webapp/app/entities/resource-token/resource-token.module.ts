import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Fivegmedia3ASharedModule } from 'app/shared';
import {
    ResourceTokenComponent,
    ResourceTokenDetailComponent,
    ResourceTokenUpdateComponent,
    ResourceTokenDeletePopupComponent,
    ResourceTokenDeleteDialogComponent,
    resourceTokenRoute,
    resourceTokenPopupRoute
} from './';

const ENTITY_STATES = [...resourceTokenRoute, ...resourceTokenPopupRoute];

@NgModule({
    imports: [Fivegmedia3ASharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ResourceTokenComponent,
        ResourceTokenDetailComponent,
        ResourceTokenUpdateComponent,
        ResourceTokenDeleteDialogComponent,
        ResourceTokenDeletePopupComponent
    ],
    entryComponents: [
        ResourceTokenComponent,
        ResourceTokenUpdateComponent,
        ResourceTokenDeleteDialogComponent,
        ResourceTokenDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Fivegmedia3AResourceTokenModule {}
