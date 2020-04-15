import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Fivegmedia3ASharedModule } from 'app/shared';
import {
    AccVduSessionComponent,
    AccVduSessionDetailComponent,
    AccVduSessionUpdateComponent,
    AccVduSessionDeletePopupComponent,
    AccVduSessionDeleteDialogComponent,
    accVduSessionRoute,
    accVduSessionPopupRoute
} from './';

const ENTITY_STATES = [...accVduSessionRoute, ...accVduSessionPopupRoute];

@NgModule({
    imports: [Fivegmedia3ASharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AccVduSessionComponent,
        AccVduSessionDetailComponent,
        AccVduSessionUpdateComponent,
        AccVduSessionDeleteDialogComponent,
        AccVduSessionDeletePopupComponent
    ],
    entryComponents: [
        AccVduSessionComponent,
        AccVduSessionUpdateComponent,
        AccVduSessionDeleteDialogComponent,
        AccVduSessionDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Fivegmedia3AAccVduSessionModule {}
