import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrganisationCreateDTO } from 'src/app/shared/dto/organisation/organisation-create.dto';
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

  createOrganisation(
    dto: OrganisationCreateDTO
  ): Observable<{ message: string | null; data: OrganisationDTO }> {
    return this.http.post<{ message: string | null; data: OrganisationDTO }>(
      this.baseUrl,
      dto
    );
  }

  getOrganisationById(
    id: number
  ): Observable<{ message: string | null; data: OrganisationDTO }> {
    return this.http.get<{ message: string | null; data: OrganisationDTO }>(
      `${this.baseUrl}/${id}`
    );
  }
}
