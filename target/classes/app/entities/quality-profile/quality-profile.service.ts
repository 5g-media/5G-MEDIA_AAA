import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IQualityProfile } from 'app/shared/model/quality-profile.model';

type EntityResponseType = HttpResponse<IQualityProfile>;
type EntityArrayResponseType = HttpResponse<IQualityProfile[]>;

@Injectable({ providedIn: 'root' })
export class QualityProfileService {
    private resourceUrl = SERVER_API_URL + 'api/quality-profiles';

    constructor(private http: HttpClient) {}

    create(qualityProfile: IQualityProfile): Observable<EntityResponseType> {
        return this.http.post<IQualityProfile>(this.resourceUrl, qualityProfile, { observe: 'response' });
    }

    update(qualityProfile: IQualityProfile): Observable<EntityResponseType> {
        return this.http.put<IQualityProfile>(this.resourceUrl, qualityProfile, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IQualityProfile>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IQualityProfile[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
