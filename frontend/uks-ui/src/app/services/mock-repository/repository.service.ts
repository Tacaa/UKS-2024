import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Repository } from 'src/app/shared/models/mock.repository.model';

@Injectable({
  providedIn: 'root',
})
export class RepositoryService {
  getAllNamespaces(): string[] {
    const namespaces = this.getAllRepositories().map((repo) => repo.namespace);
    return Array.from(new Set(namespaces));
  }

  getAllCategories(): string[] {
    return [
      'API Management',
      'Content Management System',
      'Data Science',
      'Databases & Storage',
      'Languages & Frameworks',
      'Integration & Delivery',
      'Internet of Things',
      'Machine Learning & AI',
      'Message Queues',
      'Monitoring & Observability',
      'Networking',
      'Operating Systems',
      'Security',
      'Web Servers',
      'Developer Tools',
      'Web Analytics',
    ];
  }

  getAllRepositories(): Repository[] {
    return [
      {
        id: 1,
        name: 'ProjectAlpha',
        namespace: 'team_alpha',
        description: 'Main project for Alpha team',
        visibility: true,
        created: new Date('2023-08-15'),
        updated: new Date('2024-01-20'),
        star: 42,
        presonal: false,
        public: true,
        image: '../../assets/images/logo_crni.png',
      },
      {
        id: 2,
        name: 'BetaTools',
        namespace: 'team_alpha',
        description: 'Utility tools for Beta developers',
        visibility: false,
        created: new Date('2022-12-10'),
        updated: undefined,
        star: 15,
        presonal: true,
        public: false,
        image: '../../assets/images/logo_crni.png',
      },
      {
        id: 3,
        name: 'GammaLib',
        namespace: 'team_alpha',
        description: 'A library for Gamma functionality',
        visibility: true,
        created: new Date('2021-07-25'),
        updated: new Date('2024-06-10'),
        star: 88,
        presonal: false,
        public: true,
        image: '../../assets/images/logo_crni.png',
      },
      {
        id: 4,
        name: 'DeltaDocs',
        namespace: 'docs_delta',
        description:
          'Documentation for Delta systems and functionalities od delta integration woth other facilities and numbers of coleegues',
        visibility: true,
        created: new Date('2023-03-01'),
        updated: new Date('2024-09-30'),
        star: 5,
        presonal: true,
        public: true,
        image: '../../assets/images/logo_crni.png',
      },
      {
        id: 5,
        name: 'EpsilonEngine',
        namespace: 'docs_delta',
        description: 'Core engine for Epsilon applications',
        visibility: false,
        created: new Date('2022-06-15'),
        updated: new Date('2023-11-11'),
        star: 73,
        presonal: false,
        public: false,
        image: '../../assets/images/logo_crni.png',
      },
      {
        id: 6,
        name: 'ZetaUI',
        namespace: 'docs_delta',
        description: 'Frontend components for Zeta',
        visibility: true,
        created: new Date('2021-02-20'),
        updated: undefined,
        star: 31,
        presonal: true,
        public: true,
        image: '../../assets/images/logo_crni.png',
      },
      {
        id: 7,
        name: 'EtaData',
        namespace: 'docs_delta',
        description: 'Data models and analysis tools for Eta',
        visibility: true,
        created: new Date('2020-05-15'),
        updated: new Date('2024-07-01'),
        star: 60,
        presonal: false,
        public: true,
        image: '../../assets/images/logo_crni.png',
      },
      {
        id: 8,
        name: 'ThetaAI',
        namespace: 'ai_theta',
        description: 'AI-based solutions for Theta',
        visibility: false,
        created: new Date('2023-01-10'),
        updated: new Date('2024-03-25'),
        star: 95,
        presonal: false,
        public: false,
        image: '../../assets/images/logo_crni.png',
      },
    ];
  }

  constructor(private http: HttpClient) {}

  public getRepository(id: number) {
    return this.http.get('http://localhost:8081/api/repositories/' + id);
  }
}
