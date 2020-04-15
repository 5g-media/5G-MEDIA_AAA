import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IResourceCost } from 'app/shared/model/resource-cost.model';
import { ResourceCostService } from './resource-cost.service';

@Component({
    selector: 'jhi-resource-cost-delete-dialog',
    templateUrl: './resource-cost-delete-dialog.component.html'
})
export class ResourceCostDeleteDialogComponent {
    resourceCost: IResourceCost;

    constructor(
        private resourceCostService: ResourceCostService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.resourceCostService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'resourceCostListModification',
                content: 'Deleted an resourceCost'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-resource-cost-delete-popup',
    template: ''
})
export class ResourceCostDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ resourceCost }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ResourceCostDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.resourceCost = resourceCost;
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
