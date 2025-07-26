import { Injectable } from '@angular/core';
import { Role } from 'src/app/shared/enum/Role';
import { UserBadge } from 'src/app/shared/enum/UserBadge';
import { currentUser } from 'src/app/shared/models/user.model';

@Injectable({
  providedIn: 'root',
})

//?? THIS IS ONLY A MOCK SERVICE FOR NOW IN ORDER TO MAKE DEVELOPMENT MORE STREAMLINE
//?? WILL BE UPDATED WHEN THE AUTH GET PUSHED FOR THE BACKEND
//?? IT IS USING REAL USER DATA THAT IS CURRENTLY IN THE DATABASE!

//! IN ORDER TO USE CHANGE THE mockUserId VALUE BELOW, 1 = USER, 3= ADMIN , 4= SUPER_ADMIN FOR ITS ROLES
export class AuthService {
  constructor() {
    this.mockLogin(); // Call this during service initialization
  }

  private mockLogin(): void {
    //! ⬇️ Change this number to 1, 3, or 4 to simulate different users
    const mockUserId: number = 3;
    //! ⬆️ Change this number to 1, 3, or 4 to simulate different users

    let mockUser: currentUser;

    switch (mockUserId) {
      case 1:
        mockUser = {
          id: 1,
          firstName: 'John',
          lastName: 'Doe',
          username: 'johndoe',
          email: 'johndoe@example.com',
          joinedDate: new Date('2024-12-01T08:00:00'),
          role: Role.USER,
          passwordChanged: false,
          userBadge: UserBadge.VERIFIED_PUBLISHER,
        };
        break;
      case 3:
        mockUser = {
          id: 3,
          firstName: 'Alice',
          lastName: 'Brown',
          username: 'alicebrown',
          email: 'alicebrown@example.com',
          joinedDate: new Date('2024-12-03T10:30:15'),
          role: Role.ADMIN,
          passwordChanged: false,
          userBadge: UserBadge.VERIFIED_PUBLISHER,
        };
        break;
      case 4:
        mockUser = {
          id: 4,
          firstName: 'Bob',
          lastName: 'White',
          username: 'bobwhite',
          email: 'bobwhite@example.com',
          joinedDate: new Date('2024-12-04T11:45:45'),
          role: Role.SUPER_ADMIN,
          passwordChanged: false,
          userBadge: UserBadge.NONE,
        };
        break;
      default:
        throw new Error('Invalid mock user ID selected.');
    }

    localStorage.setItem('currentUser', JSON.stringify(mockUser));
  }

  //?? MOCK METHOD - Will be replaced with actual API call
  getCurrentUser(): currentUser | null {
    const userJson = localStorage.getItem('currentUser');
    return userJson ? JSON.parse(userJson) : null;
  }

  //?? MOCK METHOD - Will be replaced with actual API call
  getCurrentUserRole(): Role | null {
    const currentUser = this.getCurrentUser();
    return currentUser ? currentUser.role : null;
  }

  logout(): void {
    localStorage.removeItem('currentUser');
  }
}
