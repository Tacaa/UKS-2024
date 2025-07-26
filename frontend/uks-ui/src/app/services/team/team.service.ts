import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddTeamMemberDTO } from 'src/app/shared/dto/team/add-member.dto';
import { CreateTeamDTO } from 'src/app/shared/dto/team/create-team.dto';
import { TeamDTO } from 'src/app/shared/dto/team/team.dto';
import { UpdateTeamDTO } from 'src/app/shared/dto/team/update-team.dto';

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

  createTeam(dto: CreateTeamDTO): Observable<any> {
    return this.http.post<any>(this.baseUrl, dto);
  }

  updateTeam(teamId: number, dto: UpdateTeamDTO): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/${teamId}`, dto);
  }

  addMemberToTeam(dto: AddTeamMemberDTO): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/add_member`, dto);
  }
}
