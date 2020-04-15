export interface IAccNsSessionNetwork {
    id?: number;
    timestamp?: number;
    nfvipopId?: string;
    maxBandwidthMbps?: number;
    usedBandwidthMbps?: string;
    sessionLengthSec?: number;
    accNsSessionId?: number;
}

export class AccNsSessionNetwork implements IAccNsSessionNetwork {
    constructor(
        public id?: number,
        public timestamp?: number,
        public nfvipopId?: string,
        public maxBandwidthMbps?: number,
        public usedBandwidthMbps?: string,
        public sessionLengthSec?: number,
        public accNsSessionId?: number
    ) {}
}
