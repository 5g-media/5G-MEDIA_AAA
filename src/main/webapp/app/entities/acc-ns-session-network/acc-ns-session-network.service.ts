import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAccNsSessionNetwork } from 'app/shared/model/acc-ns-session-network.model';

type EntityResponseType = HttpResponse<IAccNsSessionNetwork>;
type EntityArrayResponseType = HttpResponse<IAccNsSessionNetwork[]>;

@Injectable({ providedIn: 'root' })
export class AccNsSessionNetworkService {
    private resourceUrl = SERVER_API_URL + 'api/acc-ns-session-networks';

    constructor(private http: HttpClient) {}

    create(accNsSessionNetwork: IAccNsSessionNetwork): Observable<EntityResponseType> {
        return this.http.post<IAccNsSessionNetwork>(this.resourceUrl, accNsSessionNetwork, { observe: 'response' });
    }

    update(accNsSessionNetwork: IAccNsSessionNetwork): Observable<EntityResponseType> {
        return this.http.put<IAccNsSessionNetwork>(this.resourceUrl, accNsSessionNetwork, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAccNsSessionNetwork>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAccNsSessionNetwork[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
