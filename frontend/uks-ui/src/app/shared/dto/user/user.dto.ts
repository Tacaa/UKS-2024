import { RoleEnum } from '../../enum/RoleEnum';
import { UserBadge } from '../../enum/UserBadge';

export interface UserDTO {
  id: number;
  firstName: string;
  lastName: string;
  username: string;
  email: string;
  joinedDate: Date;
  roleEnum: RoleEnum;
  userBadge: UserBadge;
}
