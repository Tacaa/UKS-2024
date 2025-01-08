import { Component, OnInit } from '@angular/core';
import { RepositoryService } from '../services/repository/repository.service';
import { FormControl } from '@angular/forms';

interface namespaceGroup {
  name: string;
  namespace: string[];
}

@Component({
  selector: 'app-usage-page',
  templateUrl: './usage-page.component.html',
  styleUrls: ['./usage-page.component.css'],
})
export class UsagePageComponent implements OnInit {
  formControl = new FormControl('');
  organizationNamespaces: string[] = [];
  selected: string = '';
  namespaceGroups?: namespaceGroup[];

  constructor(private repositoryService: RepositoryService) {}

  onSelectionChange(event: any): void {
    this.selected = event.value; // 'event.value' contains the selected namespace
  }

  ngOnInit(): void {
    this.organizationNamespaces = this.repositoryService.getAllNamespaces();
    this.namespaceGroups = [
      {
        name: 'Personal',
        namespace: ['userName'],
      },
      {
        name: 'Organizations',
        namespace: this.repositoryService.getAllNamespaces(),
      },
    ];
  }
}
