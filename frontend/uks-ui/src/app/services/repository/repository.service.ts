import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CreateOfficialRepositoryDTO } from 'src/app/shared/models/create-official-repository-model';

@Injectable({
  providedIn: 'root',
})
export class RepositoryService {
  private baseUrl = 'http://localhost:8081/api/repositories';

  constructor(private http: HttpClient) {}

  createOfficialRepository(dto: CreateOfficialRepositoryDTO) {
    return this.http.post(`${this.baseUrl}/official`, dto);
  }
}
