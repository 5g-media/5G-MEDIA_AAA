import { IResourceUser } from 'app/shared/model//resource-user.model';
import { ICatalogToken } from 'app/shared/model//catalog-token.model';
import { IAccNsSession } from 'app/shared/model//acc-ns-session.model';
import { ICatalogTenant } from 'app/shared/model//catalog-tenant.model';

export const enum RoleEnum {
    SERVICE_PROVIDER = 'SERVICE_PROVIDER',
    VERTICAL = 'VERTICAL',
    ADMIN = 'ADMIN',
    SERVICE_COMPOSER = 'SERVICE_COMPOSER',
    DEVELOPER = 'DEVELOPER'
}

export interface ICatalogUser {
    id?: number;
    name?: string;
    description?: string;
    userId?: number;
    resourceUserSets?: IResourceUser[];
    catalogTokenSets?: ICatalogToken[];
    accNsSessionSets?: IAccNsSession[];
    catalogTenantSets?: ICatalogTenant[];
}

export class CatalogUser implements ICatalogUser {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string,
        public userId?: number,
        public resourceUserSets?: IResourceUser[],
        public catalogTokenSets?: ICatalogToken[],
        public accNsSessionSets?: IAccNsSession[],
        public catalogTenantSets?: ICatalogTenant[]
    ) {}
}

export interface ICatalogUserLogin {
    id?: number;
    login?: string;
    roles?: string;
}

export class CatalogUserLogin implements ICatalogUserLogin {
    constructor(public id?: number, public login?: string, public roles?: string) {}
}
