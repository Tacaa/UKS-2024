import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user/user.service';
import { UpdateUserDTO } from 'src/app/shared/dto/user/update-user.dto';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-account-settings',
  templateUrl: './account-settings.component.html',
  styleUrls: ['./account-settings.component.css'],
})
export class AccountSettingsComponent implements OnInit {
  accountForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private authService: AuthService
  ) {
    // Initialize the form group with default values and validators
    this.accountForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
    });
  }

  ngOnInit(): void {
    const currentUser = this.authService.getCurrentUser();
    const userId = currentUser?.id;

    if (!userId) {
      alert('User not found. Redirecting...');
      return;
    }

    this.userService.getUserById(userId).subscribe({
      next: (user: UpdateUserDTO) => {
        this.accountForm.patchValue(user);
      },
      error: (err: HttpErrorResponse) => {
        console.error('Failed to load user:', err);
      },
    });
  }

  onSubmit(): void {
    if (this.accountForm.valid) {
      const formData: UpdateUserDTO = this.accountForm.value;
      console.log('Sending to backend:', formData);
      const currentUser = this.authService.getCurrentUser();
      const userId = currentUser?.id;

      if (!userId) {
        alert('User not found. Redirecting...');
        return;
      }

      this.userService.updateUser(userId, formData).subscribe({
        next: (response) => {
          const updatedUser = response.data;
          console.log('User updated:', updatedUser);
          alert(response.message || 'Profile updated successfully!');
        },
        error: (err: HttpErrorResponse) => {
          console.error('Error:', err);
          alert('Failed to update user.');
        },
      });
    } else {
      console.error('Form is invalid');
    }
  }
}
