import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICatalogToken } from 'app/shared/model/catalog-token.model';
import { CatalogTokenService } from './catalog-token.service';

@Component({
    selector: 'jhi-catalog-token-delete-dialog',
    templateUrl: './catalog-token-delete-dialog.component.html'
})
export class CatalogTokenDeleteDialogComponent {
    catalogToken: ICatalogToken;

    constructor(
        private catalogTokenService: CatalogTokenService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.catalogTokenService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'catalogTokenListModification',
                content: 'Deleted an catalogToken'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-catalog-token-delete-popup',
    template: ''
})
export class CatalogTokenDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catalogToken }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CatalogTokenDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.catalogToken = catalogToken;
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
