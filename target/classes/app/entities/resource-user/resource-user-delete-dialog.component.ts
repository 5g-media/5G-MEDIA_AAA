import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IResourceUser } from 'app/shared/model/resource-user.model';
import { ResourceUserService } from './resource-user.service';

@Component({
    selector: 'jhi-resource-user-delete-dialog',
    templateUrl: './resource-user-delete-dialog.component.html'
})
export class ResourceUserDeleteDialogComponent {
    resourceUser: IResourceUser;

    constructor(
        private resourceUserService: ResourceUserService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.resourceUserService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'resourceUserListModification',
                content: 'Deleted an resourceUser'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-resource-user-delete-popup',
    template: ''
})
export class ResourceUserDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ resourceUser }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ResourceUserDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.resourceUser = resourceUser;
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
