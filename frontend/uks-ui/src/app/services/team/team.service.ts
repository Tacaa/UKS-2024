import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TeamDTO } from 'src/app/shared/dto/team/team.dto';

export interface TeamResponse {
  message: string;
  data: TeamDTO[] | null;
}

@Injectable({
  providedIn: 'root',
})
export class TeamService {
  private baseUrl = 'http://localhost:8081/api/team';

  constructor(private http: HttpClient) {}

  getOrganisationTeams(
    orgId: number,
    userId: number
  ): Observable<TeamResponse> {
    return this.http.get<TeamResponse>(`${this.baseUrl}/${orgId}`, {
      params: {
        userId: userId.toString(),
      },
    });
  }
}
