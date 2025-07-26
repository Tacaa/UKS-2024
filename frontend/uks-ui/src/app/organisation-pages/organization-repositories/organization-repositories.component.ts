import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-organization-repositories',
  templateUrl: './organization-repositories.component.html',
  styleUrls: ['./organization-repositories.component.css'],
})
export class OrganizationRepositoriesComponent implements OnInit {
  orgId = 0;

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.parent?.paramMap.subscribe((params) => {
      const orgId = Number(params.get('id'));
      this.orgId = orgId;
    });
  }
}
