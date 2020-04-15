import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPolicy } from 'app/shared/model/policy.model';

@Component({
    selector: 'jhi-policy-detail',
    templateUrl: './policy-detail.component.html'
})
export class PolicyDetailComponent implements OnInit {
    policy: IPolicy;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ policy }) => {
            this.policy = policy;
        });
    }

    previousState() {
        window.history.back();
    }
}
