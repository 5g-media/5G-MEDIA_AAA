import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAccVduSession } from 'app/shared/model/acc-vdu-session.model';
import { AccVduSessionService } from './acc-vdu-session.service';

@Component({
    selector: 'jhi-acc-vdu-session-delete-dialog',
    templateUrl: './acc-vdu-session-delete-dialog.component.html'
})
export class AccVduSessionDeleteDialogComponent {
    accVduSession: IAccVduSession;

    constructor(
        private accVduSessionService: AccVduSessionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.accVduSessionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'accVduSessionListModification',
                content: 'Deleted an accVduSession'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-acc-vdu-session-delete-popup',
    template: ''
})
export class AccVduSessionDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ accVduSession }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AccVduSessionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.accVduSession = accVduSession;
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
