import { Repository } from './repository.model';

export interface Tag {
  id?: number;
  name: string;
  dockerPullCommand: string;
  repository?: Repository;
}
