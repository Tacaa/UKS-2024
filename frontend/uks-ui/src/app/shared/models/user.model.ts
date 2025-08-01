import { RoleEnum } from '../enum/RoleEnum';
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
  roleEnum: RoleEnum;
  passwordChanged?: boolean;
  userBadge?: UserBadge;
  repositories?: Repository[];
  organisations?: Organisation[];
  organisationsMember?: Organisation[];
  teamMember?: Organisation[];
}

export interface CurrentUser {
  id?: number;
  firstName: string;
  lastName: string;
  username: string;
  email: string;
  joinedDate: Date;
  roleEnumEnum: RoleEnum;
  passwordChanged?: boolean;
  userBadge?: UserBadge;
}
