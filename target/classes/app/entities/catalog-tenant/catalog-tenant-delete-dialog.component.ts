import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICatalogTenant } from 'app/shared/model/catalog-tenant.model';
import { CatalogTenantService } from './catalog-tenant.service';

@Component({
    selector: 'jhi-catalog-tenant-delete-dialog',
    templateUrl: './catalog-tenant-delete-dialog.component.html'
})
export class CatalogTenantDeleteDialogComponent {
    catalogTenant: ICatalogTenant;

    constructor(
        private catalogTenantService: CatalogTenantService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.catalogTenantService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'catalogTenantListModification',
                content: 'Deleted an catalogTenant'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-catalog-tenant-delete-popup',
    template: ''
})
export class CatalogTenantDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ catalogTenant }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CatalogTenantDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.catalogTenant = catalogTenant;
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
