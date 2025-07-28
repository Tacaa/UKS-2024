import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateTagDTO } from 'src/app/shared/dto/tag/create-tag.dto';
import { TagDTO } from 'src/app/shared/dto/tag/tag.dto';

@Injectable({
  providedIn: 'root',
})
export class TagService {
  private baseUrl = 'http://localhost:8081/api/tags';

  constructor(private http: HttpClient) {}

  createTag(dto: CreateTagDTO): Observable<{ message: string; data: TagDTO }> {
    return this.http.post<{ message: string; data: TagDTO }>(this.baseUrl, dto);
  }

  getTagsByRepository(repositoryId: number): Observable<TagDTO[]> {
    return this.http.get<TagDTO[]>(`${this.baseUrl}/${repositoryId}`);
  }
}
