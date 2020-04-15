import { ICatalogUserLogin } from './../../shared/model/catalog-user.model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICatalogUser } from 'app/shared/model/catalog-user.model';

type EntityResponseType = HttpResponse<ICatalogUser>;
type EntityArrayResponseType = HttpResponse<ICatalogUser[]>;

@Injectable({ providedIn: 'root' })
export class CatalogUserService {
    private resourceUrl = SERVER_API_URL + 'api/catalog-users';

    constructor(private http: HttpClient) {}

    create(catalogUser: ICatalogUser): Observable<EntityResponseType> {
        return this.http.post<ICatalogUser>(this.resourceUrl, catalogUser, { observe: 'response' });
    }

    update(catalogUser: ICatalogUser): Observable<EntityResponseType> {
        return this.http.put<ICatalogUser>(this.resourceUrl, catalogUser, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICatalogUser>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    findLogin(id: number): Observable<EntityResponseType> {
        return this.http.get<ICatalogUserLogin>(`${this.resourceUrl}/user/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICatalogUser[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
