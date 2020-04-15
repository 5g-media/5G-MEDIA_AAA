import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAccNsSession } from 'app/shared/model/acc-ns-session.model';

type EntityResponseType = HttpResponse<IAccNsSession>;
type EntityArrayResponseType = HttpResponse<IAccNsSession[]>;

@Injectable({ providedIn: 'root' })
export class AccNsSessionService {
    private resourceUrl = SERVER_API_URL + 'api/acc-ns-sessions';

    constructor(private http: HttpClient) {}

    create(accNsSession: IAccNsSession): Observable<EntityResponseType> {
        return this.http.post<IAccNsSession>(this.resourceUrl, accNsSession, { observe: 'response' });
    }

    update(accNsSession: IAccNsSession): Observable<EntityResponseType> {
        return this.http.put<IAccNsSession>(this.resourceUrl, accNsSession, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAccNsSession>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAccNsSession[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
