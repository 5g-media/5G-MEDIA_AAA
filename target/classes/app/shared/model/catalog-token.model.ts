export interface ICatalogToken {
    id?: number;
    value?: string;
    type?: string;
    timestampCreated?: number;
    timestampExpiration?: number;
    valid?: boolean;
    catalogUserId?: number;
}

export class CatalogToken implements ICatalogToken {
    constructor(
        public id?: number,
        public value?: string,
        public type?: string,
        public timestampCreated?: number,
        public timestampExpiration?: number,
        public valid?: boolean,
        public catalogUserId?: number
    ) {
        this.valid = false;
    }
}
