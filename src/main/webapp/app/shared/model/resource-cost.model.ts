export interface IResourceCost {
    id?: number;
    name?: string;
    nfvipopId?: string;
    billing?: string;
    cpu?: number;
    memoryGB?: number;
    diskGB?: number;
    gpu?: number;
    trafficGB?: number;
    totalCost?: number;
}

export class ResourceCost implements IResourceCost {
    constructor(
        public id?: number,
        public name?: string,
        public nfvipopId?: string,
        public billing?: string,
        public cpu?: number,
        public memoryGB?: number,
        public diskGB?: number,
        public gpu?: number,
        public trafficGB?: number,
        public totalCost?: number
    ) {}
}
