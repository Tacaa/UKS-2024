import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface LogDocument {
  id: string;
  timestamp: string;
  level: string;
  message: string;
  thread: string;
}

@Injectable({
  providedIn: 'root',
})
export class LogService {
  private baseUrl = 'http://localhost:8081/api/logs';

  constructor(private http: HttpClient) {}

  searchLogs(query: string): Observable<LogDocument[]> {
    const params = new HttpParams().set('query', query);

    return this.http.get<LogDocument[]>(`${this.baseUrl}/search`, { params });
  }
}
