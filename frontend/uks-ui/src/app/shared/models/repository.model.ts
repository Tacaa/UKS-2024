import { Category } from '../enum/Category';
import { Visibility } from '../enum/Visibility';
import { Organisation } from './organisation.model';
import { Tag } from './tag.model';
import { User } from './user.model';

export interface Repository {
  id: number;
  name: string;
  namespace?: string;
  description?: string;
  visibility: Visibility;
  created: Date;
  updated: Date;
  star: number;
  personal: boolean;
  category: Category;
  deleted: boolean;
  owner: User;
  tags: Tag[];
  organisation?: Organisation;
}
