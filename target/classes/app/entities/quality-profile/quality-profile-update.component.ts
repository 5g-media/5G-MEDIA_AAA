import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IQualityProfile } from 'app/shared/model/quality-profile.model';
import { QualityProfileService } from './quality-profile.service';

@Component({
    selector: 'jhi-quality-profile-update',
    templateUrl: './quality-profile-update.component.html'
})
export class QualityProfileUpdateComponent implements OnInit {
    private _qualityProfile: IQualityProfile;
    isSaving: boolean;

    constructor(private qualityProfileService: QualityProfileService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ qualityProfile }) => {
            this.qualityProfile = qualityProfile;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.qualityProfile.id !== undefined) {
            this.subscribeToSaveResponse(this.qualityProfileService.update(this.qualityProfile));
        } else {
            this.subscribeToSaveResponse(this.qualityProfileService.create(this.qualityProfile));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IQualityProfile>>) {
        result.subscribe((res: HttpResponse<IQualityProfile>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get qualityProfile() {
        return this._qualityProfile;
    }

    set qualityProfile(qualityProfile: IQualityProfile) {
        this._qualityProfile = qualityProfile;
    }
}
