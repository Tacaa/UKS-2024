import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';

@Component({
  selector: 'app-create-administrator',
  templateUrl: './create-administrator.component.html',
  styleUrls: ['./create-administrator.component.css'],
})
export class CreateAdministratorComponent {
  adminForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.adminForm = this.formBuilder.group({
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      username: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    });
  }

  onSubmit() {
    if (this.adminForm.valid) {
      const adminData = this.adminForm.value;
      console.log('Admin data:', adminData);

      // TODO Here you would typically call a service to create the administrator
      // this.adminService.createAdmin(adminData).subscribe(...)

      // For now, just log the data and reset the form
      alert('Administrator created successfully!');
      this.adminForm.reset();
    } else {
      // Mark all fields as touched to show validation errors
      Object.keys(this.adminForm.controls).forEach((key) => {
        this.adminForm.get(key)?.markAsTouched();
      });
    }
  }
}
