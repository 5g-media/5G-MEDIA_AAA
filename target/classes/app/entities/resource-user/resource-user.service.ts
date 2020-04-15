import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IResourceUser } from 'app/shared/model/resource-user.model';

type EntityResponseType = HttpResponse<IResourceUser>;
type EntityArrayResponseType = HttpResponse<IResourceUser[]>;

@Injectable({ providedIn: 'root' })
export class ResourceUserService {
    private resourceUrl = SERVER_API_URL + 'api/resource-users';

    constructor(private http: HttpClient) {}

    create(resourceUser: IResourceUser): Observable<EntityResponseType> {
        return this.http.post<IResourceUser>(this.resourceUrl, resourceUser, { observe: 'response' });
    }

    update(resourceUser: IResourceUser): Observable<EntityResponseType> {
        return this.http.put<IResourceUser>(this.resourceUrl, resourceUser, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IResourceUser>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IResourceUser[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    queryByCatalogUser(id: number): Observable<EntityArrayResponseType> {
        return this.http.get<IResourceUser[]>(`${this.resourceUrl}/filterByCatalogUser/${id}`, { observe: 'response' });
    }

    queryByResourceType(resourceType: string): Observable<EntityArrayResponseType> {
        return this.http.get<IResourceUser[]>(`${this.resourceUrl}/filterByResourceType/${resourceType}`, { observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
