import { NgModule } from '@angular/core';

import { Fivegmedia3ASharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [Fivegmedia3ASharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [Fivegmedia3ASharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class Fivegmedia3ASharedCommonModule {}
