import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAccVduResourceConsumption } from 'app/shared/model/acc-vdu-resource-consumption.model';

@Component({
    selector: 'jhi-acc-vdu-resource-consumption-detail',
    templateUrl: './acc-vdu-resource-consumption-detail.component.html'
})
export class AccVduResourceConsumptionDetailComponent implements OnInit {
    accVduResourceConsumption: IAccVduResourceConsumption;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ accVduResourceConsumption }) => {
            this.accVduResourceConsumption = accVduResourceConsumption;
        });
    }

    previousState() {
        window.history.back();
    }
}
