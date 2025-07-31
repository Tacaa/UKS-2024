import { Role } from '../../enum/Role';
import { UserBadge } from '../../enum/UserBadge';

export interface UserDTO {
  id: number;
  firstName: string;
  lastName: string;
  username: string;
  email: string;
  joinedDate: Date;
  role: Role;
  userBadge: UserBadge;
}
