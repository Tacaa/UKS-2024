import { TeamPermission } from '../../enum/TeamPersmission';

export interface CreateTeamDTO {
  name: string;
  description: string;
  teamPermission: TeamPermission;
  ownerId: number;
  organisationId: number;
}
