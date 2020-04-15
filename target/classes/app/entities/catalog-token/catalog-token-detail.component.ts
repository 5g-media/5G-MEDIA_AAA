import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICatalogToken } from 'app/shared/model/catalog-token.model';

@Component({
    selector: 'jhi-catalog-token-detail',
    templateUrl: './catalog-token-detail.component.html'
})
export class CatalogTokenDetailComponent implements OnInit {
    catalogToken: ICatalogToken;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catalogToken }) => {
            this.catalogToken = catalogToken;
        });
    }

    previousState() {
        window.history.back();
    }
}
