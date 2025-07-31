import { Badge } from '../enum/Badge';
import { Repository } from './repository.model';

export interface OfficialRepository extends Repository {
  prefix?: string;
  badge?: Badge;
}
