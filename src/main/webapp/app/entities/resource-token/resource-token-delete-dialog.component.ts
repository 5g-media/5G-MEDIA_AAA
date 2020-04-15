import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IResourceToken } from 'app/shared/model/resource-token.model';
import { ResourceTokenService } from './resource-token.service';

@Component({
    selector: 'jhi-resource-token-delete-dialog',
    templateUrl: './resource-token-delete-dialog.component.html'
})
export class ResourceTokenDeleteDialogComponent {
    resourceToken: IResourceToken;

    constructor(
        private resourceTokenService: ResourceTokenService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.resourceTokenService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'resourceTokenListModification',
                content: 'Deleted an resourceToken'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-resource-token-delete-popup',
    template: ''
})
export class ResourceTokenDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ resourceToken }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ResourceTokenDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.resourceToken = resourceToken;
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
