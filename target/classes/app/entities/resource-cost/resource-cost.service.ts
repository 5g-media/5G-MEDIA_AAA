import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IResourceCost } from 'app/shared/model/resource-cost.model';

type EntityResponseType = HttpResponse<IResourceCost>;
type EntityArrayResponseType = HttpResponse<IResourceCost[]>;

@Injectable({ providedIn: 'root' })
export class ResourceCostService {
    private resourceUrl = SERVER_API_URL + 'api/resource-costs';

    constructor(private http: HttpClient) {}

    create(resourceCost: IResourceCost): Observable<EntityResponseType> {
        return this.http.post<IResourceCost>(this.resourceUrl, resourceCost, { observe: 'response' });
    }

    update(resourceCost: IResourceCost): Observable<EntityResponseType> {
        return this.http.put<IResourceCost>(this.resourceUrl, resourceCost, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IResourceCost>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IResourceCost[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
