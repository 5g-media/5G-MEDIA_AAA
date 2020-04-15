import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICatalogTenant } from 'app/shared/model/catalog-tenant.model';

type EntityResponseType = HttpResponse<ICatalogTenant>;
type EntityArrayResponseType = HttpResponse<ICatalogTenant[]>;

@Injectable({ providedIn: 'root' })
export class CatalogTenantService {
    private resourceUrl = SERVER_API_URL + 'api/catalog-tenants';

    constructor(private http: HttpClient) {}

    create(catalogTenant: ICatalogTenant): Observable<EntityResponseType> {
        return this.http.post<ICatalogTenant>(this.resourceUrl, catalogTenant, { observe: 'response' });
    }

    update(catalogTenant: ICatalogTenant): Observable<EntityResponseType> {
        return this.http.put<ICatalogTenant>(this.resourceUrl, catalogTenant, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICatalogTenant>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICatalogTenant[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
