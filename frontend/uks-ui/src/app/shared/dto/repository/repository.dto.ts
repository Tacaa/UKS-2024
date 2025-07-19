import { Category } from '../../enum/Category';
import { Visibility } from '../../enum/Visibility';

import { OwnerDTO } from '../user/owner.dto';
import { RepositoryOrganisationDTO } from './repository-organisation.dto';

export interface RepositoryDTO {
  id: number;
  name: string;
  namespace?: string;
  description?: string;
  visibility: Visibility;
  created: Date;
  updated: Date;
  star: number;
  personal: boolean;
  categoryString?: string;
  category: Category;
  deleted: boolean;
  owner: OwnerDTO;
  organisation?: RepositoryOrganisationDTO;
}
