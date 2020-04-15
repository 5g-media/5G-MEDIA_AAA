import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Fivegmedia3ASharedModule } from 'app/shared';
import {
    ResourceCostComponent,
    ResourceCostDetailComponent,
    ResourceCostUpdateComponent,
    ResourceCostDeletePopupComponent,
    ResourceCostDeleteDialogComponent,
    resourceCostRoute,
    resourceCostPopupRoute
} from './';

const ENTITY_STATES = [...resourceCostRoute, ...resourceCostPopupRoute];

@NgModule({
    imports: [Fivegmedia3ASharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ResourceCostComponent,
        ResourceCostDetailComponent,
        ResourceCostUpdateComponent,
        ResourceCostDeleteDialogComponent,
        ResourceCostDeletePopupComponent
    ],
    entryComponents: [
        ResourceCostComponent,
        ResourceCostUpdateComponent,
        ResourceCostDeleteDialogComponent,
        ResourceCostDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Fivegmedia3AResourceCostModule {}
