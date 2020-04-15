import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IResourceUserLogin } from 'app/shared/model/resource-user-login.model';

type EntityResponseType = HttpResponse<IResourceUserLogin>;
type EntityArrayResponseType = HttpResponse<IResourceUserLogin[]>;

@Injectable({ providedIn: 'root' })
export class ResourceUserLoginService {
    private resourceUrl = SERVER_API_URL + 'api/resource-user-logins';

    constructor(private http: HttpClient) {}

    create(resourceUserLogin: IResourceUserLogin): Observable<EntityResponseType> {
        return this.http.post<IResourceUserLogin>(this.resourceUrl, resourceUserLogin, { observe: 'response' });
    }

    update(resourceUserLogin: IResourceUserLogin): Observable<EntityResponseType> {
        return this.http.put<IResourceUserLogin>(this.resourceUrl, resourceUserLogin, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IResourceUserLogin>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IResourceUserLogin[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
