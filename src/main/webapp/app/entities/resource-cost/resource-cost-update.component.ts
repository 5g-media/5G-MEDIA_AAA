import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IResourceCost } from 'app/shared/model/resource-cost.model';
import { ResourceCostService } from './resource-cost.service';

@Component({
    selector: 'jhi-resource-cost-update',
    templateUrl: './resource-cost-update.component.html'
})
export class ResourceCostUpdateComponent implements OnInit {
    private _resourceCost: IResourceCost;
    isSaving: boolean;

    constructor(private resourceCostService: ResourceCostService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ resourceCost }) => {
            this.resourceCost = resourceCost;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.resourceCost.id !== undefined) {
            this.subscribeToSaveResponse(this.resourceCostService.update(this.resourceCost));
        } else {
            this.subscribeToSaveResponse(this.resourceCostService.create(this.resourceCost));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IResourceCost>>) {
        result.subscribe((res: HttpResponse<IResourceCost>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get resourceCost() {
        return this._resourceCost;
    }

    set resourceCost(resourceCost: IResourceCost) {
        this._resourceCost = resourceCost;
    }
}
