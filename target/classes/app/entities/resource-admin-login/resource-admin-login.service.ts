import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IResourceAdminLogin } from 'app/shared/model/resource-admin-login.model';

type EntityResponseType = HttpResponse<IResourceAdminLogin>;
type EntityArrayResponseType = HttpResponse<IResourceAdminLogin[]>;

@Injectable({ providedIn: 'root' })
export class ResourceAdminLoginService {
    private resourceUrl = SERVER_API_URL + 'api/resource-admin-logins';

    constructor(private http: HttpClient) {}

    create(resourceAdminLogin: IResourceAdminLogin): Observable<EntityResponseType> {
        return this.http.post<IResourceAdminLogin>(this.resourceUrl, resourceAdminLogin, { observe: 'response' });
    }

    update(resourceAdminLogin: IResourceAdminLogin): Observable<EntityResponseType> {
        return this.http.put<IResourceAdminLogin>(this.resourceUrl, resourceAdminLogin, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IResourceAdminLogin>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IResourceAdminLogin[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
