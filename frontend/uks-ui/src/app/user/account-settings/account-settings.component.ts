import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-account-settings',
  templateUrl: './account-settings.component.html',
  styleUrls: ['./account-settings.component.css']
})

export class AccountSettingsComponent {
  accountForm: FormGroup;

  constructor(private fb: FormBuilder) {
    // Initialize the form group with default values and validators
    this.accountForm = this.fb.group({
      name: ['', ],                  // Name is required
      company: ['', ],             // Last Name is required
      location: [''],                                // Organization is optional
      password: ['', [Validators.minLength(6)]] // Password validation
    });
  }

  onSubmit(): void {
    if (this.accountForm.valid) {
      console.log('Form Submitted:', this.accountForm.value);
      // Handle the form submission logic here, e.g., call an API to update user info
    } else {
      console.error('Form is invalid');
    }
  }
}
