import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IQualityProfile } from 'app/shared/model/quality-profile.model';
import { QualityProfileService } from './quality-profile.service';

@Component({
    selector: 'jhi-quality-profile-delete-dialog',
    templateUrl: './quality-profile-delete-dialog.component.html'
})
export class QualityProfileDeleteDialogComponent {
    qualityProfile: IQualityProfile;

    constructor(
        private qualityProfileService: QualityProfileService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.qualityProfileService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'qualityProfileListModification',
                content: 'Deleted an qualityProfile'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-quality-profile-delete-popup',
    template: ''
})
export class QualityProfileDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ qualityProfile }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(QualityProfileDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.qualityProfile = qualityProfile;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
