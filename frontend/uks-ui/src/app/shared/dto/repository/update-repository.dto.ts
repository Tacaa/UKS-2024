import { Category } from '../../enum/Category';
import { Visibility } from '../../enum/Visibility';

export interface UpdateRepositoryDTO {
  namespace?: string;
  description?: string;
  visibility?: Visibility;
  personal?: boolean;
  category?: Category;
}
