export const enum VduResourceEnum {
    CPU_CYCLE = 'CPU_CYCLE',
    MEMORY_MB = 'MEMORY_MB',
    DISK_GB = 'DISK_GB'
}

export interface IAccVduResourceConsumption {
    id?: number;
    value?: number;
    timestamp?: number;
    resourceType?: VduResourceEnum;
    accVduSessionId?: number;
}

export class AccVduResourceConsumption implements IAccVduResourceConsumption {
    constructor(
        public id?: number,
        public value?: number,
        public timestamp?: number,
        public resourceType?: VduResourceEnum,
        public accVduSessionId?: number
    ) {}
}
