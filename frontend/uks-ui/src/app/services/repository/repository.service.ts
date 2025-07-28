import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { OrganisationRepositoryDTO } from 'src/app/shared/dto/repository/organisation-repository.dto';
import { RepositoryDTO } from 'src/app/shared/dto/repository/repository.dto';
import { UpdateRepositoryDTO } from 'src/app/shared/dto/repository/update-repository.dto';
import { CreateOfficialRepositoryDTO } from 'src/app/shared/dto/repository/create-repository.dto';

export interface PagedResponse<T> {
  content: T[];
  totalPages: number;
  totalElements: number;
}

@Injectable({
  providedIn: 'root',
})
export class RepositoryService {
  private baseUrl = 'http://localhost:8081/api/repositories';

  constructor(private http: HttpClient) {}

  createOfficialRepository(dto: CreateOfficialRepositoryDTO) {
    return this.http.post(`${this.baseUrl}/official`, dto);
  }

  getRepositoriesByOwner(
    ownerId: number
  ): Observable<PagedResponse<RepositoryDTO>> {
    const params = new HttpParams().set('ownerId', ownerId.toString());
    return this.http.get<PagedResponse<RepositoryDTO>>(
      `${this.baseUrl}/search`,
      {
        params,
      }
    );
  }
  getRepositoriesByOrganisation(
    organisationId: number
  ): Observable<PagedResponse<RepositoryDTO>> {
    return this.http
      .get<OrganisationRepositoryDTO[]>(
        `${this.baseUrl}/organisation/${organisationId}`
      )
      .pipe(
        map((repos) => {
          const content: RepositoryDTO[] = repos
            .filter((repo) => repo.owner)
            .map((repo) => ({
              id: repo.id,
              name: repo.name,
              namespace: repo.namespace ?? '',
              description: repo.description ?? '',
              visibility: repo.visibility,
              created: new Date(repo.created),
              updated: new Date(repo.updated),
              star: repo.star,
              personal: repo.personal,
              categoryString: repo.categoryString,
              category: repo.category,
              deleted: repo.deleted,
              owner: repo.owner!,
              organisation: repo.organisation ?? undefined,
            }));

          return {
            content,
            totalElements: content.length,
            totalPages: 1,
          };
        })
      );
  }

  getRepositoryById(id: number): Observable<RepositoryDTO> {
    return this.http.get<RepositoryDTO>(`${this.baseUrl}/${id}`);
  }

  updateRepository(
    id: number,
    updateDto: UpdateRepositoryDTO
  ): Observable<RepositoryDTO> {
    return this.http
      .put<{ data: RepositoryDTO }>(`${this.baseUrl}/${id}`, updateDto)
      .pipe(map((res) => res.data));
  }

  deleteRepository(id: number): Observable<string> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getAllRepositories(): Observable<RepositoryDTO> {
    return this.http.get<RepositoryDTO>(`${this.baseUrl}/all`);
  }
}
