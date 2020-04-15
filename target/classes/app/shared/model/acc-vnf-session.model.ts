import { IAccVduSession } from 'app/shared/model//acc-vdu-session.model';

export interface IAccVnfSession {
    id?: number;
    vnfId?: string;
    vnfName?: string;
    timestampStart?: number;
    timestampStop?: number;
    accVduSessionSets?: IAccVduSession[];
    accNsSessionId?: number;
}

export class AccVnfSession implements IAccVnfSession {
    constructor(
        public id?: number,
        public vnfId?: string,
        public vnfName?: string,
        public timestampStart?: number,
        public timestampStop?: number,
        public accVduSessionSets?: IAccVduSession[],
        public accNsSessionId?: number
    ) {}
}
