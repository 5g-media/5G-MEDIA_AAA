import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IResource } from 'app/shared/model/resource.model';

@Component({
    selector: 'jhi-resource-detail',
    templateUrl: './resource-detail.component.html'
})
export class ResourceDetailComponent implements OnInit {
    resource: IResource;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ resource }) => {
            this.resource = resource;
        });
    }

    previousState() {
        window.history.back();
    }
}
