import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAccVnfSession } from 'app/shared/model/acc-vnf-session.model';

type EntityResponseType = HttpResponse<IAccVnfSession>;
type EntityArrayResponseType = HttpResponse<IAccVnfSession[]>;

@Injectable({ providedIn: 'root' })
export class AccVnfSessionService {
    private resourceUrl = SERVER_API_URL + 'api/acc-vnf-sessions';

    constructor(private http: HttpClient) {}

    create(accVnfSession: IAccVnfSession): Observable<EntityResponseType> {
        return this.http.post<IAccVnfSession>(this.resourceUrl, accVnfSession, { observe: 'response' });
    }

    update(accVnfSession: IAccVnfSession): Observable<EntityResponseType> {
        return this.http.put<IAccVnfSession>(this.resourceUrl, accVnfSession, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAccVnfSession>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAccVnfSession[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
