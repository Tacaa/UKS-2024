import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RepositoryDTO } from 'src/app/shared/dto/repository/repository.dto';
import { CreateOfficialRepositoryDTO } from 'src/app/shared/models/create-official-repository-model';

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

  getRepositoryById(id: number): Observable<RepositoryDTO> {
    return this.http.get<RepositoryDTO>(`${this.baseUrl}/${id}`);
  }
}
