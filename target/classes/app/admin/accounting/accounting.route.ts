import { Route } from '@angular/router';

import { JhiAccountingComponent } from './accounting.component';

export const accountingRoute: Route = {
    path: 'accounting',
    component: JhiAccountingComponent,
    data: {
        pageTitle: 'Accounting'
    }
};
