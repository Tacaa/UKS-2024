export interface OrganisationDTO {
  id?: number;
  name: string;
  description?: string;
  deactivated?: boolean;
  ownerId: number;
  image?: string | null;
  members: number[];
  teams: number[];
}
