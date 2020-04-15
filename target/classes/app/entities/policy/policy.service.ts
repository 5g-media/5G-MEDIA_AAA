import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPolicy } from 'app/shared/model/policy.model';

type EntityResponseType = HttpResponse<IPolicy>;
type EntityArrayResponseType = HttpResponse<IPolicy[]>;

@Injectable({ providedIn: 'root' })
export class PolicyService {
    private resourceUrl = SERVER_API_URL + 'api/policies';

    constructor(private http: HttpClient) {}

    create(policy: IPolicy): Observable<EntityResponseType> {
        return this.http.post<IPolicy>(this.resourceUrl, policy, { observe: 'response' });
    }

    update(policy: IPolicy): Observable<EntityResponseType> {
        return this.http.put<IPolicy>(this.resourceUrl, policy, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPolicy>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPolicy[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
