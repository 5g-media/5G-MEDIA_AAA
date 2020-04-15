import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICatalogUser } from 'app/shared/model/catalog-user.model';
import { CatalogUserService } from './catalog-user.service';

@Component({
    selector: 'jhi-catalog-user-delete-dialog',
    templateUrl: './catalog-user-delete-dialog.component.html'
})
export class CatalogUserDeleteDialogComponent {
    catalogUser: ICatalogUser;

    constructor(
        private catalogUserService: CatalogUserService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.catalogUserService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'catalogUserListModification',
                content: 'Deleted an catalogUser'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-catalog-user-delete-popup',
    template: ''
})
export class CatalogUserDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catalogUser }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CatalogUserDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.catalogUser = catalogUser;
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
