import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-repository-page',
  templateUrl: './repository-page.component.html',
  styleUrls: ['./repository-page.component.css'],
})
export class RepositoryPageComponent {
  constructor(private router: Router) {}

  isActive(route: string): boolean {
    return this.router.url === route;
  }
}
