export interface TagDTO {
  id: number;
  name: string;
  dockerPullCommand: string;
  pulled: string;
  pushed: string;
  author: string;
  repositoryId: number;
}
