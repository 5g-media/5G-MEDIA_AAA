import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAccVduResourceConsumption } from 'app/shared/model/acc-vdu-resource-consumption.model';

type EntityResponseType = HttpResponse<IAccVduResourceConsumption>;
type EntityArrayResponseType = HttpResponse<IAccVduResourceConsumption[]>;

@Injectable({ providedIn: 'root' })
export class AccVduResourceConsumptionService {
    private resourceUrl = SERVER_API_URL + 'api/acc-vdu-resource-consumptions';

    constructor(private http: HttpClient) {}

    create(accVduResourceConsumption: IAccVduResourceConsumption): Observable<EntityResponseType> {
        return this.http.post<IAccVduResourceConsumption>(this.resourceUrl, accVduResourceConsumption, { observe: 'response' });
    }

    update(accVduResourceConsumption: IAccVduResourceConsumption): Observable<EntityResponseType> {
        return this.http.put<IAccVduResourceConsumption>(this.resourceUrl, accVduResourceConsumption, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAccVduResourceConsumption>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAccVduResourceConsumption[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
