import { DateFormatterPipe } from './util/date-formatter';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';

import { NgbDateMomentAdapter } from './util/datepicker-adapter';
import { Fivegmedia3ASharedLibsModule, Fivegmedia3ASharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
    imports: [Fivegmedia3ASharedLibsModule, Fivegmedia3ASharedCommonModule],
    declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective, DateFormatterPipe],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    entryComponents: [JhiLoginModalComponent],
    exports: [DateFormatterPipe, Fivegmedia3ASharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Fivegmedia3ASharedModule {}
