import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAccNsSessionNetwork } from 'app/shared/model/acc-ns-session-network.model';

@Component({
    selector: 'jhi-acc-ns-session-network-detail',
    templateUrl: './acc-ns-session-network-detail.component.html'
})
export class AccNsSessionNetworkDetailComponent implements OnInit {
    accNsSessionNetwork: IAccNsSessionNetwork;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ accNsSessionNetwork }) => {
            this.accNsSessionNetwork = accNsSessionNetwork;
        });
    }

    previousState() {
        window.history.back();
    }
}
