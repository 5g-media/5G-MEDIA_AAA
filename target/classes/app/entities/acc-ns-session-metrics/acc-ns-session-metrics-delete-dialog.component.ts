import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAccNsSessionMetrics } from 'app/shared/model/acc-ns-session-metrics.model';
import { AccNsSessionMetricsService } from './acc-ns-session-metrics.service';

@Component({
    selector: 'jhi-acc-ns-session-metrics-delete-dialog',
    templateUrl: './acc-ns-session-metrics-delete-dialog.component.html'
})
export class AccNsSessionMetricsDeleteDialogComponent {
    accNsSessionMetrics: IAccNsSessionMetrics;

    constructor(
        private accNsSessionMetricsService: AccNsSessionMetricsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.accNsSessionMetricsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'accNsSessionMetricsListModification',
                content: 'Deleted an accNsSessionMetrics'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-acc-ns-session-metrics-delete-popup',
    template: ''
})
export class AccNsSessionMetricsDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ accNsSessionMetrics }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AccNsSessionMetricsDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.accNsSessionMetrics = accNsSessionMetrics;
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
