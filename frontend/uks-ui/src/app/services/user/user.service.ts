import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from 'src/app/shared/models/user.model';
import { UpdateUserDTO } from 'src/app/shared/dto/user/update-user.dto';
import { UserDTO } from 'src/app/shared/dto/user/user.dto';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUrl = 'http://localhost:8081/api/users';

  constructor(private http: HttpClient) {}

  // Fetch all users
  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/all`);
  }

  searchUsers(params: any): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/search`, { params });
  }

  // Fetch user by ID
  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/${id}`);
  }

  updateUserBadge(id: number, badge: { userBadge: string }): Observable<User> {
    return this.http.put<User>(`${this.baseUrl}/badge/${id}`, badge);
  }

  updateUser(
    id: number,
    updateUserDTO: UpdateUserDTO
  ): Observable<{ message: string | null; data: UserDTO | null }> {
    return this.http.put<{ message: string | null; data: UserDTO | null }>(
      `${this.baseUrl}/${id}`,
      updateUserDTO
    );
  }
}
