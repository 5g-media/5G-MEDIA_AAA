import { IResourceUser } from 'app/shared/model//resource-user.model';
import { IEndpoint } from 'app/shared/model//endpoint.model';

export const enum ResourceEnum {
    OSM_R3 = 'OSM_R3',
    OSM_R4 = 'OSM_R4',
    OSM_R5 = 'OSM_R5',
    SDN_CONTROLLER = 'SDN_CONTROLLER',
    OPENSTACK = 'OPENSTACK',
    OPENNEBULA = 'OPENNEBULA',
    FAAS = 'FAAS'
}

export interface IResource {
    id?: number;
    resourceid?: string;
    name?: string;
    url?: string;
    authDriver?: string;
    authConf?: string;
    properties?: string;
    resourceEnum?: ResourceEnum;
    resourceAdminLoginId?: number;
    resourceUserSets?: IResourceUser[];
    endpointSets?: IEndpoint[];
}

export class Resource implements IResource {
    constructor(
        public id?: number,
        public resourceid?: string,
        public name?: string,
        public url?: string,
        public authDriver?: string,
        public authConf?: string,
        public properties?: string,
        public resourceEnum?: ResourceEnum,
        public resourceAdminLoginId?: number,
        public resourceUserSets?: IResourceUser[],
        public endpointSets?: IEndpoint[]
    ) {}
}
