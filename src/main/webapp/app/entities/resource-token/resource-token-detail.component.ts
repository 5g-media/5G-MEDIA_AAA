import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IResourceToken } from 'app/shared/model/resource-token.model';

@Component({
    selector: 'jhi-resource-token-detail',
    templateUrl: './resource-token-detail.component.html'
})
export class ResourceTokenDetailComponent implements OnInit {
    resourceToken: IResourceToken;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ resourceToken }) => {
            this.resourceToken = resourceToken;
        });
    }

    previousState() {
        window.history.back();
    }
}
