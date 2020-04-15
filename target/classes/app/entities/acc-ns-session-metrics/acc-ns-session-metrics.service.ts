import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAccNsSessionMetrics } from 'app/shared/model/acc-ns-session-metrics.model';

type EntityResponseType = HttpResponse<IAccNsSessionMetrics>;
type EntityArrayResponseType = HttpResponse<IAccNsSessionMetrics[]>;

@Injectable({ providedIn: 'root' })
export class AccNsSessionMetricsService {
    private resourceUrl = SERVER_API_URL + 'api/acc-ns-session-metrics';

    constructor(private http: HttpClient) {}

    create(accNsSessionMetrics: IAccNsSessionMetrics): Observable<EntityResponseType> {
        return this.http.post<IAccNsSessionMetrics>(this.resourceUrl, accNsSessionMetrics, { observe: 'response' });
    }

    update(accNsSessionMetrics: IAccNsSessionMetrics): Observable<EntityResponseType> {
        return this.http.put<IAccNsSessionMetrics>(this.resourceUrl, accNsSessionMetrics, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAccNsSessionMetrics>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAccNsSessionMetrics[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
