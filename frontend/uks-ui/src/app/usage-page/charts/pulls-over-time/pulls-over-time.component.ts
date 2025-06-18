import { Component } from '@angular/core';

@Component({
  selector: 'app-pulls-over-time',
  templateUrl: './pulls-over-time.component.html',
  styleUrls: ['./pulls-over-time.component.css'],
})
export class PullsOverTimeComponent {
  pullsData = [
    {
      name: 'Repository Pulls',
      series: [
        // 2019
        { name: 'Jan 2019', value: 50 },
        { name: 'Mar 2019', value: 55 },
        { name: 'May 2019', value: 60 },
        { name: 'Jul 2019', value: 70 },
        { name: 'Sep 2019', value: 85 },
        { name: 'Nov 2019', value: 90 },

        // 2020
        { name: 'Jan 2020', value: 95 },
        { name: 'Mar 2020', value: 105 },
        { name: 'May 2020', value: 120 },
        { name: 'Jul 2020', value: 140 },
        { name: 'Sep 2020', value: 160 },
        { name: 'Nov 2020', value: 180 },

        // 2021 - Spike
        { name: 'Jan 2021', value: 220 },
        { name: 'Mar 2021', value: 300 },
        { name: 'May 2021', value: 420 },
        { name: 'Jul 2021', value: 530 },
        { name: 'Sep 2021', value: 610 },
        { name: 'Nov 2021', value: 700 },

        // 2022 - Stagnation
        { name: 'Jan 2022', value: 710 },
        { name: 'Mar 2022', value: 715 },
        { name: 'May 2022', value: 718 },
        { name: 'Jul 2022', value: 720 },
        { name: 'Sep 2022', value: 725 },
        { name: 'Nov 2022', value: 730 },

        // 2023 - Growth again
        { name: 'Jan 2023', value: 750 },
        { name: 'Mar 2023', value: 800 },
        { name: 'May 2023', value: 880 },
        { name: 'Jul 2023', value: 970 },
        { name: 'Sep 2023', value: 1100 },
        { name: 'Nov 2023', value: 1250 },
      ],
    },
  ];

  view: [number, number] = [700, 300];
  customColors = [
    {
      name: 'Repository Pulls',
      value: '#4271e7',
    },
  ];
}
