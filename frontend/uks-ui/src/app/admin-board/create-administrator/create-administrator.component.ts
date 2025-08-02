import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
  FormControl,
} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';
import { RoleEnum } from 'src/app/shared/enum/RoleEnum';
import { UserRequest } from 'src/app/shared/models/user-register-request.model';

@Component({
  selector: 'app-create-administrator',
  templateUrl: './create-administrator.component.html',
  styleUrls: ['./create-administrator.component.css'],
})
export class CreateAdministratorComponent {
  registerForm = new FormGroup({
    firstname: new FormControl('', Validators.required),
    lastname: new FormControl('', Validators.required),
    username: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', Validators.required),
  });

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    if (this.registerForm.valid) {
      const formValues = this.registerForm.value;

      const userRequest: UserRequest = {
        username: formValues.username!,
        password: formValues.password!,
        firstname: formValues.firstname!,
        lastname: formValues.lastname!,
        email: formValues.email!,
        roleEnum: RoleEnum.ADMIN,
      };

      this.authService.registerAdmin(userRequest).subscribe({
        next: (response) => {
          console.log('Admin registered successfully:', response);
          alert('Admin registered successfully:');
        },
        error: (error) => {
          console.error('Error registering admin:', error);
          alert(
            "'Error registering admin:'" +
              error.error.error +
              ': User with that username already exists'
          );
        },
      });
    } else {
      console.log('Form is invalid');
    }
  }
}
