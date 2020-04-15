import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICatalogTenant } from 'app/shared/model/catalog-tenant.model';

@Component({
    selector: 'jhi-catalog-tenant-detail',
    templateUrl: './catalog-tenant-detail.component.html'
})
export class CatalogTenantDetailComponent implements OnInit {
    catalogTenant: ICatalogTenant;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catalogTenant }) => {
            this.catalogTenant = catalogTenant;
        });
    }

    previousState() {
        window.history.back();
    }
}
