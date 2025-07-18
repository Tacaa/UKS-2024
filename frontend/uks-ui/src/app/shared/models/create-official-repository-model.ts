import { Category } from 'src/app/model/repository';

export interface CreateRepositoryDTO {
  name: string;
  namespace: string;
  description: string;
  visibility: string;
  personal: boolean;
  ownerId: number;
  organisationId: number;
  category: Category;
}

export interface CreateOfficialRepositoryDTO {
  createRepositoryDTO: CreateRepositoryDTO;
  prefix: string;
}
