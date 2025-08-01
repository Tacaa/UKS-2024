import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';
import { RoleEnum } from 'src/app/shared/enum/RoleEnum';
import { UserRequest } from 'src/app/shared/models/user-register-request.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'], // Ignore styling for now
})
export class RegisterComponent {
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
        roleEnum: RoleEnum.USER,
      };

      this.authService.register(userRequest);
    } else {
      console.log('Form is invalid');
    }
  }
}
