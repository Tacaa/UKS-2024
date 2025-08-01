import { Component } from '@angular/core';
import { LogDocument, LogService } from '../services/log/log.service';

@Component({
  selector: 'app-log-search',
  templateUrl: './log-search.component.html',
  styleUrls: ['./log-search.component.css'],
})
export class LogSearchComponent {
  query: string = '';
  results: LogDocument[] = [];
  searched: boolean = false;

  constructor(private logService: LogService) {}

  onSearch(): void {
    this.logService.searchLogs(this.query).subscribe({
      next: (data) => {
        this.results = data;
        this.searched = true;
      },
      error: (error) => {
        console.log('Error during log search', error);
        this.results = [];
        this.searched = true;
      },
    });
  }
}
