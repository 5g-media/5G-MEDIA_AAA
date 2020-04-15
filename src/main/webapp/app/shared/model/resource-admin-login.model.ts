export interface IResourceAdminLogin {
    id?: number;
    username?: string;
    password?: string;
    publicKey?: string;
}

export class ResourceAdminLogin implements IResourceAdminLogin {
    constructor(public id?: number, public username?: string, public password?: string, public publicKey?: string) {}
}
