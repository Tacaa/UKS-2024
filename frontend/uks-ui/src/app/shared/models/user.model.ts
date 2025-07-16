import { Role } from '../enum/Role';
import { UserBadge } from '../enum/UserBadge';
import { Organisation } from './organisation.model';
import { Repository } from './repository.model';

export interface User {
  id?: number;
  firstName: string;
  lastName: string;
  username: string;
  password: string;
  email: string;
  joinedDate: Date;
  role: Role;
  passwordChanged?: boolean;
  userBadge?: UserBadge;
  repositories?: Repository[];
  organisations?: Organisation[];
  organisationsMember?: Organisation[];
  teamMember?: Organisation[];
}
