import { TeamMemberDTO } from './team-member.dto';

export interface TeamDTO {
  id?: number;
  name?: string;
  description?: string;
  teamPermission?: string;
  members?: TeamMemberDTO[];
}
