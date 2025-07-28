import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'timeAgo',
  pure: true, // Set to false if you want the pipe to be re-evaluated on every change detection cycle
})
export class TimeAgoPipe implements PipeTransform {
  transform(date?: string | Date): unknown {
    if (!date) return '';
    const now = new Date();
    const past = new Date(date);
    const seconds = Math.floor((now.getTime() - past.getTime()) / 1000);

    if (isNaN(past.getTime())) return 'Invalid date';

    const units = [
      { name: 'year', seconds: 365 * 24 * 60 * 60 },
      { name: 'month', seconds: 30 * 24 * 60 * 60 },
      { name: 'week', seconds: 7 * 24 * 60 * 60 },
      { name: 'day', seconds: 24 * 60 * 60 },
      { name: 'hour', seconds: 60 * 60 },
      { name: 'minute', seconds: 60 },
      { name: 'second', seconds: 1 },
    ];

    for (const unit of units) {
      const value = Math.floor(seconds / unit.seconds);
      if (value > 0) {
        return `${value} ${unit.name}${value > 1 ? 's' : ''} ago`;
      }
    }

    return 'just now';
  }
}
