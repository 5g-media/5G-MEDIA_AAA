import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Fivegmedia3ASharedModule } from 'app/shared';
import {
    EndpointComponent,
    EndpointDetailComponent,
    EndpointUpdateComponent,
    EndpointDeletePopupComponent,
    EndpointDeleteDialogComponent,
    endpointRoute,
    endpointPopupRoute
} from './';

const ENTITY_STATES = [...endpointRoute, ...endpointPopupRoute];

@NgModule({
    imports: [Fivegmedia3ASharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EndpointComponent,
        EndpointDetailComponent,
        EndpointUpdateComponent,
        EndpointDeleteDialogComponent,
        EndpointDeletePopupComponent
    ],
    entryComponents: [EndpointComponent, EndpointUpdateComponent, EndpointDeleteDialogComponent, EndpointDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Fivegmedia3AEndpointModule {}
