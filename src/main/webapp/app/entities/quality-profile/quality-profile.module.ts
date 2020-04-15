import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Fivegmedia3ASharedModule } from 'app/shared';
import {
    QualityProfileComponent,
    QualityProfileDetailComponent,
    QualityProfileUpdateComponent,
    QualityProfileDeletePopupComponent,
    QualityProfileDeleteDialogComponent,
    qualityProfileRoute,
    qualityProfilePopupRoute
} from './';

const ENTITY_STATES = [...qualityProfileRoute, ...qualityProfilePopupRoute];

@NgModule({
    imports: [Fivegmedia3ASharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        QualityProfileComponent,
        QualityProfileDetailComponent,
        QualityProfileUpdateComponent,
        QualityProfileDeleteDialogComponent,
        QualityProfileDeletePopupComponent
    ],
    entryComponents: [
        QualityProfileComponent,
        QualityProfileUpdateComponent,
        QualityProfileDeleteDialogComponent,
        QualityProfileDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Fivegmedia3AQualityProfileModule {}
