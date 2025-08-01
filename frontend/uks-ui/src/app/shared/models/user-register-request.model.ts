import { RoleEnum } from '../enum/RoleEnum';

export interface UserRequest {
  username: string;
  password: string;
  firstname: string;
  lastname: string;
  email: string;
  roleEnumEnum: RoleEnum;
}
