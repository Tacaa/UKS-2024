export class Repository {
  id!: number;
  name!: string;
  namespace!: string;
  description?: string;
  visibility!: boolean;
  created!: Date;
  updated?: Date;
  star: number = 0;
  presonal?: boolean;
  public?: boolean;
  image?: string;
}
