import { TeamPermission } from '../enum/TeamPersmission';
import { Organisation } from './organisation.model';
import { User } from './user.model';

export interface Team {
  id?: number;
  name: string;
  description?: string;
  teamPermission?: TeamPermission;
  members?: User[];
  organisation?: Organisation;
}
