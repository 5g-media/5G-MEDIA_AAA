import { Pipe, PipeTransform } from '@angular/core';
import { DatePipe } from '@angular/common';

@Pipe({ name: 'dateFormatter' })
export class DateFormatterPipe implements PipeTransform {
    constructor(public datepipe: DatePipe) {}

    public transform(name: number): string {
        if (!name) {
            return '';
        }

        const date = new Date(name);
        const formattedDate = this.datepipe.transform(date, 'yyyy.MM.dd HH:mm:ss');
        return formattedDate;
    }
}
