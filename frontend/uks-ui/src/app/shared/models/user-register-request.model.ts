import { Role } from '../enum/Role';

export interface UserRequest {
  username: string;
  password: string;
  firstname: string;
  lastname: string;
  email: string;
  roleEnum: Role;
}
