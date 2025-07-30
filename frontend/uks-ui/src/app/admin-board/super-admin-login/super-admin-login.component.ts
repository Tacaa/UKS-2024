import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-super-admin-login',
  templateUrl: './super-admin-login.component.html',
  styleUrls: ['./super-admin-login.component.css'],
})
export class SuperAdminLoginComponent {
  loginForm: FormGroup;
  showGeneratedPassword: boolean = false;
  showNewPassword: boolean = false;
  showConfirmPassword: boolean = false;

  constructor(private formBuilder: FormBuilder) {
    this.loginForm = this.formBuilder.group(
      {
        username: ['', [Validators.required]],
        generatedPassword: ['', [Validators.required]],
        newPassword: ['', [Validators.required]],
        confirmPassword: ['', [Validators.required]],
      },
      { validators: this.passwordMatchValidator }
    );
  }

  // Custom validator to check if passwords match
  passwordMatchValidator(form: FormGroup) {
    const newPassword = form.get('newPassword');
    const confirmPassword = form.get('confirmPassword');

    if (
      newPassword &&
      confirmPassword &&
      newPassword.value !== confirmPassword.value
    ) {
      confirmPassword.setErrors({ passwordMismatch: true });
      return { passwordMismatch: true };
    } else {
      if (confirmPassword?.errors?.['passwordMismatch']) {
        delete confirmPassword.errors['passwordMismatch'];
        if (Object.keys(confirmPassword.errors).length === 0) {
          confirmPassword.setErrors(null);
        }
      }
    }
    return null;
  }

  toggleGeneratedPasswordVisibility() {
    this.showGeneratedPassword = !this.showGeneratedPassword;
  }

  toggleNewPasswordVisibility() {
    this.showNewPassword = !this.showNewPassword;
  }

  toggleConfirmPasswordVisibility() {
    this.showConfirmPassword = !this.showConfirmPassword;
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const loginData = {
        username: this.loginForm.value.username,
        generatedPassword: this.loginForm.value.generatedPassword,
        newPassword: this.loginForm.value.newPassword,
      };

      console.log('First superadmin login data:', loginData);

      // TODO Here you would typically call a service to handle the first-time login
      // this.authService.firstSuperadminLogin(loginData).subscribe(...)

      alert(
        'Setup completed successfully! You can now login with your new password.'
      );
    } else {
      // Mark all fields as touched to show validation errors
      Object.keys(this.loginForm.controls).forEach((key) => {
        this.loginForm.get(key)?.markAsTouched();
      });
    }
  }
}
