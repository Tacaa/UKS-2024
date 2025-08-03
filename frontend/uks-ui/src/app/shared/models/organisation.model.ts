import { Repository } from './repository.model';
import { Team } from './team.model';
import { User } from './user.model';

export interface Organisation {
  id?: number;
  name: string;
  description?: string;
  owner?: User;
  deactivated: boolean;
  image?: string;
  members?: User[];
  teams?: Team[];
  repositories?: Repository[];
}
