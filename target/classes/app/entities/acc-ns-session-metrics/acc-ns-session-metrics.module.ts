import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Fivegmedia3ASharedModule } from 'app/shared';
import {
    AccNsSessionMetricsComponent,
    AccNsSessionMetricsDetailComponent,
    AccNsSessionMetricsUpdateComponent,
    AccNsSessionMetricsDeletePopupComponent,
    AccNsSessionMetricsDeleteDialogComponent,
    accNsSessionMetricsRoute,
    accNsSessionMetricsPopupRoute
} from './';

const ENTITY_STATES = [...accNsSessionMetricsRoute, ...accNsSessionMetricsPopupRoute];

@NgModule({
    imports: [Fivegmedia3ASharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AccNsSessionMetricsComponent,
        AccNsSessionMetricsDetailComponent,
        AccNsSessionMetricsUpdateComponent,
        AccNsSessionMetricsDeleteDialogComponent,
        AccNsSessionMetricsDeletePopupComponent
    ],
    entryComponents: [
        AccNsSessionMetricsComponent,
        AccNsSessionMetricsUpdateComponent,
        AccNsSessionMetricsDeleteDialogComponent,
        AccNsSessionMetricsDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Fivegmedia3AAccNsSessionMetricsModule {}
