import { InjectionToken, OnDestroy } from '@angular/core';
export declare type ARIA_LIVE_DELAY_TYPE = number | null;
export declare const ARIA_LIVE_DELAY: InjectionToken<number>;
export declare const DEFAULT_ARIA_LIVE_DELAY: ARIA_LIVE_DELAY_TYPE;
export declare class Live implements OnDestroy {
    private _delay;
    private _element;
    constructor(document: any, _delay: ARIA_LIVE_DELAY_TYPE);
    ngOnDestroy(): void;
    say(message: string): void;
}
