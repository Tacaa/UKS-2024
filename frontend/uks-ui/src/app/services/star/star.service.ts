import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RepositoryDTO } from 'src/app/shared/dto/repository/repository.dto';
import { StarDTO } from 'src/app/shared/dto/star/star.dto';

@Injectable({
  providedIn: 'root',
})
export class StarService {
  private baseUrl = 'http://localhost:8081/api/stars';

  constructor(private http: HttpClient) {}

  starRepository(starDTO: StarDTO): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}`, starDTO);
  }

  unstarRepository(starDTO: StarDTO): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}`, { body: starDTO });
  }

  getStarredRepositories(userId: number): Observable<RepositoryDTO[]> {
    const params = new HttpParams().set('userId', userId.toString());
    return this.http.get<RepositoryDTO[]>(`${this.baseUrl}/user`, { params });
  }

  countStars(repositoryId: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/count/${repositoryId}`);
  }
}
