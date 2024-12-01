import { Injectable } from '@angular/core';
import { Repository } from 'src/app/shared/models/Repository';

@Injectable({
  providedIn: 'root',
})
export class RepositoryService {
  getAllNamespaces(): string[] {
    const namespaces = this.getAll().map((repo) => repo.namespace);
    return Array.from(new Set(namespaces));
  }

  getAll(): Repository[] {
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
      },
      {
        id: 4,
        name: 'DeltaDocs',
        namespace: 'docs_delta',
        description: 'Documentation for Delta systems',
        visibility: true,
        created: new Date('2023-03-01'),
        updated: new Date('2024-09-30'),
        star: 5,
        presonal: true,
        public: true,
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
      },
    ];
  }

  constructor() {}
}
