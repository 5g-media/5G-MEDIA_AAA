import { IAccVnfSession } from 'app/shared/model//acc-vnf-session.model';
import { IAccNsSessionMetrics } from 'app/shared/model//acc-ns-session-metrics.model';
import { IAccNsSessionNetwork } from 'app/shared/model//acc-ns-session-network.model';

export interface IAccNsSession {
    id?: number;
    manoId?: string;
    manoUser?: string;
    manoProject?: string;
    nsId?: string;
    nsName?: string;
    timestampStart?: number;
    timestampStop?: number;
    qualityProfile?: string;
    resourceCost?: number;
    optimisation?: string;
    accVnfSessionSets?: IAccVnfSession[];
    accNsSessionMetricsSets?: IAccNsSessionMetrics[];
    accNsSessionNetworkSets?: IAccNsSessionNetwork[];
    catalogUserId?: number;
}

export class AccNsSession implements IAccNsSession {
    constructor(
        public id?: number,
        public manoId?: string,
        public manoUser?: string,
        public manoProject?: string,
        public nsId?: string,
        public nsName?: string,
        public timestampStart?: number,
        public timestampStop?: number,
        public qualityProfile?: string,
        public resourceCost?: number,
        public optimisation?: string,
        public accVnfSessionSets?: IAccVnfSession[],
        public accNsSessionMetricsSets?: IAccNsSessionMetrics[],
        public accNsSessionNetworkSets?: IAccNsSessionNetwork[],
        public catalogUserId?: number
    ) {}
}
