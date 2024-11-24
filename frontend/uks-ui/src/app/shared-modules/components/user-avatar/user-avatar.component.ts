import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-user-avatar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './user-avatar.component.html',
  styleUrls: ['./user-avatar.component.css']
})
export class UserAvatarComponent {
  @Input()
  width: number = 32;

  @Input()
  imgUrl: string = './../../../../../assets/svg/user_placeholder.svg'

  @Input()
  disabled: boolean = false;

  @Output()
  onAvatarClick = new EventEmitter<void>();
}
