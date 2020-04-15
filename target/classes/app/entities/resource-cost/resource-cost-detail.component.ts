import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IResourceCost } from 'app/shared/model/resource-cost.model';

@Component({
    selector: 'jhi-resource-cost-detail',
    templateUrl: './resource-cost-detail.component.html'
})
export class ResourceCostDetailComponent implements OnInit {
    resourceCost: IResourceCost;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ resourceCost }) => {
            this.resourceCost = resourceCost;
        });
    }

    previousState() {
        window.history.back();
    }
}
