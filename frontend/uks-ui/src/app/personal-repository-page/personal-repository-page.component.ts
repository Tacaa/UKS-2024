import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-personal-repository-page',
  templateUrl: './personal-repository-page.component.html',
  styleUrls: ['./personal-repository-page.component.css'],
})
export class PersonalRepositoryPageComponent {
  constructor(private router: Router) {}

  isActive(route: string): boolean {
    return this.router.url === route;
  }
}
