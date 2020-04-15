import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Fivegmedia3ASharedModule } from 'app/shared';
import {
    AccVduResourceConsumptionComponent,
    AccVduResourceConsumptionDetailComponent,
    AccVduResourceConsumptionUpdateComponent,
    AccVduResourceConsumptionDeletePopupComponent,
    AccVduResourceConsumptionDeleteDialogComponent,
    accVduResourceConsumptionRoute,
    accVduResourceConsumptionPopupRoute
} from './';

const ENTITY_STATES = [...accVduResourceConsumptionRoute, ...accVduResourceConsumptionPopupRoute];

@NgModule({
    imports: [Fivegmedia3ASharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AccVduResourceConsumptionComponent,
        AccVduResourceConsumptionDetailComponent,
        AccVduResourceConsumptionUpdateComponent,
        AccVduResourceConsumptionDeleteDialogComponent,
        AccVduResourceConsumptionDeletePopupComponent
    ],
    entryComponents: [
        AccVduResourceConsumptionComponent,
        AccVduResourceConsumptionUpdateComponent,
        AccVduResourceConsumptionDeleteDialogComponent,
        AccVduResourceConsumptionDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Fivegmedia3AAccVduResourceConsumptionModule {}
