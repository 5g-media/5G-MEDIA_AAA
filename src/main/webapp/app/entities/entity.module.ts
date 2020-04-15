import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { Fivegmedia3AAccNsSessionModule } from './acc-ns-session/acc-ns-session.module';
import { Fivegmedia3AAccNsSessionMetricsModule } from './acc-ns-session-metrics/acc-ns-session-metrics.module';
import { Fivegmedia3AAccNsSessionNetworkModule } from './acc-ns-session-network/acc-ns-session-network.module';
import { Fivegmedia3AQualityProfileModule } from './quality-profile/quality-profile.module';
import { Fivegmedia3AResourceCostModule } from './resource-cost/resource-cost.module';
import { Fivegmedia3AAccVduSessionModule } from './acc-vdu-session/acc-vdu-session.module';
import { Fivegmedia3AAccVduResourceConsumptionModule } from './acc-vdu-resource-consumption/acc-vdu-resource-consumption.module';
import { Fivegmedia3AEndpointModule } from './endpoint/endpoint.module';
import { Fivegmedia3ACatalogTokenModule } from './catalog-token/catalog-token.module';
import { Fivegmedia3AResourceModule } from './resource/resource.module';
import { Fivegmedia3AResourceTokenModule } from './resource-token/resource-token.module';
import { Fivegmedia3AResourceAdminLoginModule } from './resource-admin-login/resource-admin-login.module';
import { Fivegmedia3APolicyModule } from './policy/policy.module';
import { Fivegmedia3AAccVnfSessionModule } from './acc-vnf-session/acc-vnf-session.module';
import { Fivegmedia3AResourceUserModule } from './resource-user/resource-user.module';
import { Fivegmedia3AResourceUserLoginModule } from './resource-user-login/resource-user-login.module';
import { Fivegmedia3ACatalogUserModule } from './catalog-user/catalog-user.module';
import { Fivegmedia3ACatalogTenantModule } from './catalog-tenant/catalog-tenant.module';

/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        Fivegmedia3AAccNsSessionModule,
        Fivegmedia3AAccNsSessionMetricsModule,
        Fivegmedia3AAccNsSessionNetworkModule,
        Fivegmedia3AResourceCostModule,
        Fivegmedia3AQualityProfileModule,
        Fivegmedia3AAccVduSessionModule,
        Fivegmedia3AAccVduResourceConsumptionModule,
        Fivegmedia3AEndpointModule,
        Fivegmedia3ACatalogTokenModule,
        Fivegmedia3AResourceModule,
        Fivegmedia3AResourceTokenModule,
        Fivegmedia3AResourceAdminLoginModule,
        Fivegmedia3APolicyModule,
        Fivegmedia3AAccVnfSessionModule,
        Fivegmedia3AResourceUserModule,
        Fivegmedia3AResourceUserLoginModule,
        Fivegmedia3ACatalogUserModule,
        Fivegmedia3ACatalogTenantModule
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    exports: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Fivegmedia3AEntityModule {}
