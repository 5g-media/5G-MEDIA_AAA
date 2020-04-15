export interface IPolicy {
    id?: number;
    name?: string;
    policy?: string;
    catalogTenantId?: number;
}

export class Policy implements IPolicy {
    constructor(public id?: number, public name?: string, public policy?: string, public catalogUserId?: number) {}
}
