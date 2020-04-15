import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICatalogToken } from 'app/shared/model/catalog-token.model';

type EntityResponseType = HttpResponse<ICatalogToken>;
type EntityArrayResponseType = HttpResponse<ICatalogToken[]>;

@Injectable({ providedIn: 'root' })
export class CatalogTokenService {
    private resourceUrl = SERVER_API_URL + 'api/catalog-tokens';

    constructor(private http: HttpClient) {}

    create(catalogToken: ICatalogToken): Observable<EntityResponseType> {
        return this.http.post<ICatalogToken>(this.resourceUrl, catalogToken, { observe: 'response' });
    }

    update(catalogToken: ICatalogToken): Observable<EntityResponseType> {
        return this.http.put<ICatalogToken>(this.resourceUrl, catalogToken, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICatalogToken>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICatalogToken[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
