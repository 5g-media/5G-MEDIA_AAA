import { NgZone } from '@angular/core';
/**
 * Class that enforce the browser focus to be trapped inside a DOM element.
 *
 * The implementation is rather simple, the class add a `focusin` listener on the document with capture phase.
 * Any focus event will then be caught, and therefore the class will only allow the one for elements contained inside
 * it's own element.
 *
 * In case the element is not contained, the class will determine which new element has to be focused based on the `tab`
 * navigation direction.
 *
 * Should not be used directly. Use only via {@link NgbFocusTrapFactory}
 */
export declare class NgbFocusTrap {
    private _element;
    private _document;
    private _ngZone;
    private _removeDocumentListener;
    private _direction;
    private _previouslyFocused;
    private _endOfDocument;
    /**
     * Guess the next focusable element.
     * Computation is based on specific CSS selector and [tab] navigation direction
     */
    private readonly focusableElement;
    /**
     * @param _element The element around which focus will be trapped inside
     * @param autofocus Initially put the focus on specific element with a `ngbFocustrap` attribute. Will also remenber
     * and restore any previously focused element on destroy.
     * @param _document Document on which `focusin` and `keydown.TAB` events are listened
     * @param _ngZone The zone Angular is running in
     */
    constructor(_element: HTMLElement, autofocus: boolean, _document: Document, _ngZone: NgZone);
    /** Detect if incoming focus event should be prevented or not */
    private _enforceFocus(event);
    /** Event handler detecting current `tab` navigation direction */
    private _detectDirection(event);
    /** Try to set focus on the first found element that has an ngbAutofocus attribute */
    private _focusInitial();
    /**
     * Destroys the focustrap by removing all event listeners set on document.
     *
     * Eventually put the focus back on the previously focused element at the time
     * focustrap has been initialized.
     */
    destroy(): void;
}
/**
 * Factory service to easily create a `NgbFocusTrap` instance on an element
 */
export declare class NgbFocusTrapFactory {
    private _document;
    private _ngZone;
    constructor(_document: Document, _ngZone: NgZone);
    /**
     * Create an instance of {@link NgbFocusTrap} and return it
     * @param element HTMLElement to trap focus inside
     * @param autofocus Whether the focustrap should automatically move focus into the trapped element upon
     * initialization and return focus to the previous activeElement upon destruction.
     */
    create(element: HTMLElement, autofocus?: boolean): NgbFocusTrap;
}
