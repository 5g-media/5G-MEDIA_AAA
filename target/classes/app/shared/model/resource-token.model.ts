export interface IResourceToken {
    id?: number;
    value?: string;
    type?: string;
    timestampCreated?: number;
    timestampExpiration?: number;
    valid?: boolean;
    resourceUserId?: number;
}

export class ResourceToken implements IResourceToken {
    constructor(
        public id?: number,
        public value?: string,
        public type?: string,
        public timestampCreated?: number,
        public timestampExpiration?: number,
        public valid?: boolean,
        public resourceUserId?: number
    ) {
        this.valid = false;
    }
}
