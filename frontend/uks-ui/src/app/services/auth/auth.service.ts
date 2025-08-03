import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Subject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { UserDTO } from 'src/app/shared/dto/user/user.dto';
import { RoleEnum } from 'src/app/shared/enum/RoleEnum';
import { UserRequest } from 'src/app/shared/models/user-register-request.model';
import { CurrentUser } from 'src/app/shared/models/user.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private superAdminInitialized = true;

  private tokenKey = 'authToken';
  private userIdKey = 'userId';
  private userRoleEnumKey = 'userRoleEnum';
  private apiUrl = 'http://localhost:8081/api/auth';
  private currentUserSubject = new BehaviorSubject<UserDTO | null>(null);
  currentUser$ = this.currentUserSubject.asObservable();
  private roleUpdated$ = new Subject<void>();

  constructor(private http: HttpClient, private router: Router) {
    this.clearAllStorageOnStartup();
  }

  public clearAllStorageOnStartup() {
    console.log('🧹 Clearing localStorage on application startup');
    localStorage.clear();
  }

  restoreUser() {
    const token = this.getToken();
    if (token) {
      this.http.get<UserDTO>(`${this.apiUrl}/current-user`).subscribe(
        (user) => {
          this.currentUserSubject.next(user);
          this.setUserData(user); // ✅ Store user ID & roleEnum in localStorage
        },
        (error) => {
          console.error('Failed to restore user:', error);
          this.logout();
        }
      );
    }
  }

  setToken(token: string) {
    localStorage.setItem(this.tokenKey, token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  setUserData(user: UserDTO) {
    if (user.id !== undefined && user.id !== null) {
      localStorage.setItem(this.userIdKey, user.id.toString());
    } else {
      console.warn('User ID is undefined or null.');
    }

    // Handle both 'role' and 'roleEnum' properties from backend
    const roleValue = user.roleEnum || (user as any).role;
    if (roleValue) {
      console.log('localStorage set item userRoleEnum:', roleValue);
      localStorage.setItem(this.userRoleEnumKey, roleValue);
    } else {
      console.warn('User roleEnum is undefined.');
    }
  }

  clearUserData() {
    localStorage.removeItem(this.userIdKey);
    localStorage.removeItem(this.userRoleEnumKey);
  }

  getUserId(): number | null {
    const userId = localStorage.getItem(this.userIdKey);
    return userId ? parseInt(userId, 10) : null;
  }

  getUserRole(): string | null {
    return localStorage.getItem(this.userRoleEnumKey);
  }

  getCurrentUser(): UserDTO | null {
    return this.currentUserSubject.value;
  }

  getCurrentUserRoleEnum(): RoleEnum | null {
    const role = this.getUserRole();
    return (role as RoleEnum) || null;
  }

  login(username: string, password: string) {
    this.http
      .post<{ accessToken: string }>(`${this.apiUrl}/login`, {
        username,
        password,
      })
      .subscribe((response) => {
        this.setToken(response.accessToken);
        this.restoreUser(); // ✅ Fetch and set user immediately after login
        this.router.navigate(['/dockerhub/repository']);
      });
  }

  firstAdminLogin(username: string, password: string, newPassword: string) {
    password = newPassword;
    return this.http
      .post<{ accessToken: string }>(`${this.apiUrl}/super-admin-login`, {
        username,
        password,
      })
      .pipe(
        tap((response) => {
          this.setToken(response.accessToken);
          this.restoreUser(); // ✅ Fetch and set user immediately after login
          this.router.navigate(['/dockerhub/repository']);
        })
      );
  }

  logout() {
    this.http.post(`${this.apiUrl}/logout`, {}).subscribe(() => {
      localStorage.removeItem(this.tokenKey);
      this.clearUserData();
      this.currentUserSubject.next(null);
      this.router.navigate(['/dockerhub/explore']);
    });
  }

  register(user: UserRequest) {
    this.http
      .post<{ accessToken: string }>(`${this.apiUrl}/register`, user)
      .subscribe((response) => {
        this.setToken(response.accessToken);
        this.restoreUser(); // ✅ Fetch and set user immediately after register
        this.router.navigate(['']);
      });
  }

  setSuperAdminInitialized(value: boolean): void {
    this.superAdminInitialized = value;
  }

  isSuperAdminInitialized(): boolean {
    return this.superAdminInitialized;
  }

  resetSuperAdminFlag(): void {
    this.superAdminInitialized = false;
  }

  getRoleUpdateTrigger() {
    return this.roleUpdated$.asObservable();
  }

  triggerRoleUpdate(): void {
    this.roleUpdated$.next();
  }

  registerAdmin(user: UserRequest) {
    return this.http.post<Response>(`${this.apiUrl}/register-admin`, user);
  }
}
