export interface IEndpoint {
    id?: number;
    name?: string;
    url?: string;
    method?: string;
    properties?: string;
    resourceId?: number;
}

export class Endpoint implements IEndpoint {
    constructor(
        public id?: number,
        public name?: string,
        public url?: string,
        public method?: string,
        public properties?: string,
        public resourceId?: number
    ) {}
}
