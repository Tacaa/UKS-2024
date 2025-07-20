import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrganisationDTO } from 'src/app/shared/dto/organisation/organisation.dto';

@Injectable({
  providedIn: 'root',
})
export class OrganisationService {
  private baseUrl = 'http://localhost:8081/api/organisation';

  constructor(private http: HttpClient) {}

  getUserOrganisations(
    userId: number
  ): Observable<{ message: string; data: OrganisationDTO[] }> {
    return this.http.get<{ message: string; data: OrganisationDTO[] }>(
      `${this.baseUrl}/user/${userId}`
    );
  }
}
