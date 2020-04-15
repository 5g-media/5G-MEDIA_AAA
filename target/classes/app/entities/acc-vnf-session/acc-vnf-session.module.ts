import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Fivegmedia3ASharedModule } from 'app/shared';
import {
    AccVnfSessionComponent,
    AccVnfSessionDetailComponent,
    AccVnfSessionUpdateComponent,
    AccVnfSessionDeletePopupComponent,
    AccVnfSessionDeleteDialogComponent,
    accVnfSessionRoute,
    accVnfSessionPopupRoute
} from './';

const ENTITY_STATES = [...accVnfSessionRoute, ...accVnfSessionPopupRoute];

@NgModule({
    imports: [Fivegmedia3ASharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AccVnfSessionComponent,
        AccVnfSessionDetailComponent,
        AccVnfSessionUpdateComponent,
        AccVnfSessionDeleteDialogComponent,
        AccVnfSessionDeletePopupComponent
    ],
    entryComponents: [
        AccVnfSessionComponent,
        AccVnfSessionUpdateComponent,
        AccVnfSessionDeleteDialogComponent,
        AccVnfSessionDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Fivegmedia3AAccVnfSessionModule {}
