import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAccVnfSession } from 'app/shared/model/acc-vnf-session.model';
import { AccVnfSessionService } from './acc-vnf-session.service';

@Component({
    selector: 'jhi-acc-vnf-session-delete-dialog',
    templateUrl: './acc-vnf-session-delete-dialog.component.html'
})
export class AccVnfSessionDeleteDialogComponent {
    accVnfSession: IAccVnfSession;

    constructor(
        private accVnfSessionService: AccVnfSessionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.accVnfSessionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'accVnfSessionListModification',
                content: 'Deleted an accVnfSession'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-acc-vnf-session-delete-popup',
    template: ''
})
export class AccVnfSessionDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ accVnfSession }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AccVnfSessionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.accVnfSession = accVnfSession;
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
