import { Owner } from "./owner";
import { RepositoryOrganisation } from "./repositoryOrganization";

export enum Visibility{
    PUBLIC,
    PRIVATE
}

export enum Category{
    API_MANAGMENT,
    CONTENT_MANAGMENT_SYSTEM,
    DATA_SCIENCE,
    DATABASE_AND_STORAGE,
    DEVOPS,
    DOCKER_EXTENSIONS,
    LANGUAGE_AND_FRAMEWORKS,
    INTEGRATION_AND_DELIVERY,
    FRONTEND,
    INTERNET_OF_THINGS,
    MACHINE_LEARNING_AND_AI,
    MESSAGE_QUEUES,
    MENTORING_AND_OBSERVABILITY,
    NETWORKING,
    OPERATING_SYSTEM,
    OBSERVABILITY,
    SECURITY,
    WEB_SERVERS,
    NONE
}

export class Repository{
    public id!: number;
    public name?: string;
    public namespace?: string;
    public description?: string;
    public visibility!: Visibility;
    public created?: Date ;
    public updated?: Date;
    public star?: number; 
    public personal? : boolean;
    public categoryString?: string;
    public category?: Category ;
    public deleted?: boolean;
    public owner?: Owner ;
    public organisation?: RepositoryOrganisation;
}