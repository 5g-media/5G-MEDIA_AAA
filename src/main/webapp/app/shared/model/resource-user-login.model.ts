export interface IResourceUserLogin {
    id?: number;
    username?: string;
    password?: string;
    publicKey?: string;
}

export class ResourceUserLogin implements IResourceUserLogin {
    constructor(public id?: number, public username?: string, public password?: string, public publicKey?: string) {}
}
