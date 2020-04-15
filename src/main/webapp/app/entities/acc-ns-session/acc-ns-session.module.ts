import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Fivegmedia3ASharedModule } from 'app/shared';
import {
    AccNsSessionComponent,
    AccNsSessionDetailComponent,
    AccNsSessionUpdateComponent,
    AccNsSessionDeletePopupComponent,
    AccNsSessionDeleteDialogComponent,
    accNsSessionRoute,
    accNsSessionPopupRoute
} from './';

const ENTITY_STATES = [...accNsSessionRoute, ...accNsSessionPopupRoute];

@NgModule({
    imports: [Fivegmedia3ASharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AccNsSessionComponent,
        AccNsSessionDetailComponent,
        AccNsSessionUpdateComponent,
        AccNsSessionDeleteDialogComponent,
        AccNsSessionDeletePopupComponent
    ],
    entryComponents: [
        AccNsSessionComponent,
        AccNsSessionUpdateComponent,
        AccNsSessionDeleteDialogComponent,
        AccNsSessionDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Fivegmedia3AAccNsSessionModule {}
