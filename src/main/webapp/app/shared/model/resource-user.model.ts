import { IResourceToken } from 'app/shared/model//resource-token.model';

export interface IResourceUser {
    id?: number;
    name?: string;
    tenant?: string;
    permissions?: string;
    groupname?: number;
    enabled?: boolean;
    resourceUserLoginId?: number;
    resourceTokenSets?: IResourceToken[];
    resourceId?: number;
    catalogUserId?: number;
}

export class ResourceUser implements IResourceUser {
    constructor(
        public id?: number,
        public name?: string,
        public tenant?: string,
        public permissions?: string,
        public groupname?: number,
        public enabled?: boolean,
        public resourceUserLoginId?: number,
        public resourceTokenSets?: IResourceToken[],
        public resourceId?: number,
        public catalogUserId?: number
    ) {
        this.enabled = false;
    }
}
