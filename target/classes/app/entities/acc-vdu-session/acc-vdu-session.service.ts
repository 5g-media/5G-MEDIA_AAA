import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAccVduSession } from 'app/shared/model/acc-vdu-session.model';

type EntityResponseType = HttpResponse<IAccVduSession>;
type EntityArrayResponseType = HttpResponse<IAccVduSession[]>;

@Injectable({ providedIn: 'root' })
export class AccVduSessionService {
    private resourceUrl = SERVER_API_URL + 'api/acc-vdu-sessions';

    constructor(private http: HttpClient) {}

    create(accVduSession: IAccVduSession): Observable<EntityResponseType> {
        return this.http.post<IAccVduSession>(this.resourceUrl, accVduSession, { observe: 'response' });
    }

    update(accVduSession: IAccVduSession): Observable<EntityResponseType> {
        return this.http.put<IAccVduSession>(this.resourceUrl, accVduSession, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAccVduSession>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAccVduSession[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
