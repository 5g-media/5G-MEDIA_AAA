export interface IAccNsSessionMetrics {
    id?: number;
    timestamp?: number;
    nfvipopId?: string;
    sessionLengthSec?: number;
    qualityProfile?: string;
    expectedQoe?: number;
    measuredQoe?: number;
    entropyTI?: number;
    entropySI?: number;
    compressionLevel?: number;
    bandwidthAllocatedMbps?: number;
    bitrateKbps?: number;
    blur?: number;
    userCount?: number;
    accNsSessionId?: number;
}

export class AccNsSessionMetrics implements IAccNsSessionMetrics {
    constructor(
        public id?: number,
        public timestamp?: number,
        public nfvipopId?: string,
        public sessionLengthSec?: number,
        public qualityProfile?: string,
        public expectedQoe?: number,
        public measuredQoe?: number,
        public entropyTI?: number,
        public entropySI?: number,
        public compressionLevel?: number,
        public bandwidthAllocatedMbps?: number,
        public bitrateKbps?: number,
        public blur?: number,
        public userCount?: number,
        public accNsSessionId?: number
    ) {}
}
