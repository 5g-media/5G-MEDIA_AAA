import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IResourceToken } from 'app/shared/model/resource-token.model';

type EntityResponseType = HttpResponse<IResourceToken>;
type EntityArrayResponseType = HttpResponse<IResourceToken[]>;

@Injectable({ providedIn: 'root' })
export class ResourceTokenService {
    private resourceUrl = SERVER_API_URL + 'api/resource-tokens';

    constructor(private http: HttpClient) {}

    create(resourceToken: IResourceToken): Observable<EntityResponseType> {
        return this.http.post<IResourceToken>(this.resourceUrl, resourceToken, { observe: 'response' });
    }

    update(resourceToken: IResourceToken): Observable<EntityResponseType> {
        return this.http.put<IResourceToken>(this.resourceUrl, resourceToken, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IResourceToken>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IResourceToken[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
