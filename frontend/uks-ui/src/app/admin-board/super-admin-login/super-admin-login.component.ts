import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';

export interface AdminFitstLoginRequest {
  username: string;
  password: string;
  newPassword: string;
}

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

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
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
      const loginData: AdminFitstLoginRequest = {
        username: this.loginForm.value.username,
        password: this.loginForm.value.generatedPassword,
        newPassword: this.loginForm.value.newPassword,
      };

      console.log('First superadmin login data:', loginData);

      //!! ⬇⬇⬇ .firstAdminLogin() SHIFT CLICK --- PROCITAJ U SERVISU STA PISE!! ⬇⬇⬇
      this.authService
        .firstAdminLogin(
          this.loginForm.get('username')?.value as string,
          this.loginForm.get('generatedPassword')?.value as string,
          this.loginForm.get('newPassword')?.value as string
        )
        //!! .subscribe OSTAJE, ON MENJA FLEG U SERVISU DA MOZE DA SE KORISTI OSTATAK APLIKACIJE
        .subscribe({
          next: (response) => {
            // Login successful, set the superadmin flag in the service
            this.authService.setSuperAdminInitialized(true);
            alert(
              'Setup completed successfully! You can now login with your new password.'
            );

            this.router.navigate(['/dockerhub']);
          },
          error: (error) => {
            console.error('Superadmin login failed:', error);
            alert('Login failed. Please check your credentials.');
          },
        });
    } else {
      Object.keys(this.loginForm.controls).forEach((key) => {
        this.loginForm.get(key)?.markAsTouched();
      });
    }
  }
}
