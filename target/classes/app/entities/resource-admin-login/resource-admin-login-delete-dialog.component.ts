import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IResourceAdminLogin } from 'app/shared/model/resource-admin-login.model';
import { ResourceAdminLoginService } from './resource-admin-login.service';

@Component({
    selector: 'jhi-resource-admin-login-delete-dialog',
    templateUrl: './resource-admin-login-delete-dialog.component.html'
})
export class ResourceAdminLoginDeleteDialogComponent {
    resourceAdminLogin: IResourceAdminLogin;

    constructor(
        private resourceAdminLoginService: ResourceAdminLoginService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.resourceAdminLoginService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'resourceAdminLoginListModification',
                content: 'Deleted an resourceAdminLogin'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-resource-admin-login-delete-popup',
    template: ''
})
export class ResourceAdminLoginDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ resourceAdminLogin }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ResourceAdminLoginDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.resourceAdminLogin = resourceAdminLogin;
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
