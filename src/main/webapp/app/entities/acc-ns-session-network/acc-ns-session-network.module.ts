import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Fivegmedia3ASharedModule } from 'app/shared';
import {
    AccNsSessionNetworkComponent,
    AccNsSessionNetworkDetailComponent,
    AccNsSessionNetworkUpdateComponent,
    AccNsSessionNetworkDeletePopupComponent,
    AccNsSessionNetworkDeleteDialogComponent,
    accNsSessionNetworkRoute,
    accNsSessionNetworkPopupRoute
} from './';

const ENTITY_STATES = [...accNsSessionNetworkRoute, ...accNsSessionNetworkPopupRoute];

@NgModule({
    imports: [Fivegmedia3ASharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AccNsSessionNetworkComponent,
        AccNsSessionNetworkDetailComponent,
        AccNsSessionNetworkUpdateComponent,
        AccNsSessionNetworkDeleteDialogComponent,
        AccNsSessionNetworkDeletePopupComponent
    ],
    entryComponents: [
        AccNsSessionNetworkComponent,
        AccNsSessionNetworkUpdateComponent,
        AccNsSessionNetworkDeleteDialogComponent,
        AccNsSessionNetworkDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Fivegmedia3AAccNsSessionNetworkModule {}
