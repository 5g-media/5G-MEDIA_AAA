import { IPolicy } from 'app/shared/model//policy.model';
import { ICatalogUser } from 'app/shared/model//catalog-user.model';

export interface ICatalogTenant {
    id?: number;
    name?: string;
    policySets?: IPolicy[];
    catalogUserSets?: ICatalogUser[];
}

export class CatalogTenant implements ICatalogTenant {
    constructor(public id?: number, public name?: string, public policySets?: IPolicy[], public catalogUserSets?: ICatalogUser[]) {}
}
