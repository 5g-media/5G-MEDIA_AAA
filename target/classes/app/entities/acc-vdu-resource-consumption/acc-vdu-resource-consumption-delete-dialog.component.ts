import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAccVduResourceConsumption } from 'app/shared/model/acc-vdu-resource-consumption.model';
import { AccVduResourceConsumptionService } from './acc-vdu-resource-consumption.service';

@Component({
    selector: 'jhi-acc-vdu-resource-consumption-delete-dialog',
    templateUrl: './acc-vdu-resource-consumption-delete-dialog.component.html'
})
export class AccVduResourceConsumptionDeleteDialogComponent {
    accVduResourceConsumption: IAccVduResourceConsumption;

    constructor(
        private accVduResourceConsumptionService: AccVduResourceConsumptionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.accVduResourceConsumptionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'accVduResourceConsumptionListModification',
                content: 'Deleted an accVduResourceConsumption'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-acc-vdu-resource-consumption-delete-popup',
    template: ''
})
export class AccVduResourceConsumptionDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ accVduResourceConsumption }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AccVduResourceConsumptionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.accVduResourceConsumption = accVduResourceConsumption;
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
