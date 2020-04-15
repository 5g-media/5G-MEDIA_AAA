import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IResourceUserLogin } from 'app/shared/model/resource-user-login.model';
import { ResourceUserLoginService } from './resource-user-login.service';

@Component({
    selector: 'jhi-resource-user-login-delete-dialog',
    templateUrl: './resource-user-login-delete-dialog.component.html'
})
export class ResourceUserLoginDeleteDialogComponent {
    resourceUserLogin: IResourceUserLogin;

    constructor(
        private resourceUserLoginService: ResourceUserLoginService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.resourceUserLoginService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'resourceUserLoginListModification',
                content: 'Deleted an resourceUserLogin'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-resource-user-login-delete-popup',
    template: ''
})
export class ResourceUserLoginDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ resourceUserLogin }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ResourceUserLoginDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.resourceUserLogin = resourceUserLogin;
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
