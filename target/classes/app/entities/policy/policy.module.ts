import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Fivegmedia3ASharedModule } from 'app/shared';
import {
    PolicyComponent,
    PolicyDetailComponent,
    PolicyUpdateComponent,
    PolicyDeletePopupComponent,
    PolicyDeleteDialogComponent,
    policyRoute,
    policyPopupRoute
} from './';

const ENTITY_STATES = [...policyRoute, ...policyPopupRoute];

@NgModule({
    imports: [Fivegmedia3ASharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [PolicyComponent, PolicyDetailComponent, PolicyUpdateComponent, PolicyDeleteDialogComponent, PolicyDeletePopupComponent],
    entryComponents: [PolicyComponent, PolicyUpdateComponent, PolicyDeleteDialogComponent, PolicyDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Fivegmedia3APolicyModule {}
