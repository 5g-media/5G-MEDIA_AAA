import { IAccVduResourceConsumption } from 'app/shared/model//acc-vdu-resource-consumption.model';

export const enum VduTypeEnum {
    PLAIN_VNF = 'PLAIN_VNF',
    FAAS_VNF = 'FAAS_VNF'
}

export interface IAccVduSession {
    id?: number;
    vduId?: string;
    nfvipopId?: string;
    flavorCpuCount?: number;
    flavorMemoryMb?: number;
    flavorDiskGb?: number;
    timestampStart?: number;
    timestampStop?: number;
    vduTypeEnum?: VduTypeEnum;
    accVduResourceConsumptionSets?: IAccVduResourceConsumption[];
    accVnfSessionId?: number;
}

export class AccVduSession implements IAccVduSession {
    constructor(
        public id?: number,
        public vduId?: string,
        public nfvipopId?: string,
        public flavorCpuCount?: number,
        public flavorMemoryMb?: number,
        public flavorDiskGb?: number,
        public timestampStart?: number,
        public timestampStop?: number,
        public vduTypeEnum?: VduTypeEnum,
        public accVduResourceConsumptionSets?: IAccVduResourceConsumption[],
        public accVnfSessionId?: number
    ) {}
}
