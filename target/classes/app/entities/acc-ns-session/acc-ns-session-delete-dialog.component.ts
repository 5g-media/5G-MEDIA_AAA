import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAccNsSession } from 'app/shared/model/acc-ns-session.model';
import { AccNsSessionService } from './acc-ns-session.service';

@Component({
    selector: 'jhi-acc-ns-session-delete-dialog',
    templateUrl: './acc-ns-session-delete-dialog.component.html'
})
export class AccNsSessionDeleteDialogComponent {
    accNsSession: IAccNsSession;

    constructor(
        private accNsSessionService: AccNsSessionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.accNsSessionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'accNsSessionListModification',
                content: 'Deleted an accNsSession'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-acc-ns-session-delete-popup',
    template: ''
})
export class AccNsSessionDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ accNsSession }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AccNsSessionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.accNsSession = accNsSession;
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
