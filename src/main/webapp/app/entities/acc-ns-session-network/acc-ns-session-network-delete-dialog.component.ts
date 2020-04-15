import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAccNsSessionNetwork } from 'app/shared/model/acc-ns-session-network.model';
import { AccNsSessionNetworkService } from './acc-ns-session-network.service';

@Component({
    selector: 'jhi-acc-ns-session-network-delete-dialog',
    templateUrl: './acc-ns-session-network-delete-dialog.component.html'
})
export class AccNsSessionNetworkDeleteDialogComponent {
    accNsSessionNetwork: IAccNsSessionNetwork;

    constructor(
        private accNsSessionNetworkService: AccNsSessionNetworkService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.accNsSessionNetworkService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'accNsSessionNetworkListModification',
                content: 'Deleted an accNsSessionNetwork'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-acc-ns-session-network-delete-popup',
    template: ''
})
export class AccNsSessionNetworkDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ accNsSessionNetwork }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AccNsSessionNetworkDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.accNsSessionNetwork = accNsSessionNetwork;
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
