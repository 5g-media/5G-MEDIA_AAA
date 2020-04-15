import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQualityProfile } from 'app/shared/model/quality-profile.model';

@Component({
    selector: 'jhi-quality-profile-detail',
    templateUrl: './quality-profile-detail.component.html'
})
export class QualityProfileDetailComponent implements OnInit {
    qualityProfile: IQualityProfile;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ qualityProfile }) => {
            this.qualityProfile = qualityProfile;
        });
    }

    previousState() {
        window.history.back();
    }
}
