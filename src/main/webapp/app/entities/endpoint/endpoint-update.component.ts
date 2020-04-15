import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IEndpoint } from 'app/shared/model/endpoint.model';
import { EndpointService } from './endpoint.service';
import { IResource } from 'app/shared/model/resource.model';
import { ResourceService } from 'app/entities/resource';

@Component({
    selector: 'jhi-endpoint-update',
    templateUrl: './endpoint-update.component.html'
})
export class EndpointUpdateComponent implements OnInit {
    private _endpoint: IEndpoint;
    isSaving: boolean;

    resources: IResource[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private endpointService: EndpointService,
        private resourceService: ResourceService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ endpoint }) => {
            this.endpoint = endpoint;
        });
        this.resourceService.query().subscribe(
            (res: HttpResponse<IResource[]>) => {
                this.resources = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.endpoint.id !== undefined) {
            this.subscribeToSaveResponse(this.endpointService.update(this.endpoint));
        } else {
            this.subscribeToSaveResponse(this.endpointService.create(this.endpoint));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEndpoint>>) {
        result.subscribe((res: HttpResponse<IEndpoint>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackResourceById(index: number, item: IResource) {
        return item.id;
    }
    get endpoint() {
        return this._endpoint;
    }

    set endpoint(endpoint: IEndpoint) {
        this._endpoint = endpoint;
    }
}
