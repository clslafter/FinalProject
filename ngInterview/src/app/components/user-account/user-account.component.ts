import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-account',
  templateUrl: './user-account.component.html',
  styleUrls: ['./user-account.component.css']
})
export class UserAccountComponent implements OnInit {

  constructor(private userService: UserService, private auth: AuthService) { }

  ngOnInit(): void {
    this.loadUser()
  }

user: User = new User;
editUser: User | null = null;
selected: User | null = null;

  loadUser(): void {
    this.auth.getLoggedInUser().subscribe({
      next: (data) => {
        this.user = data;
      },
      error: (fail) => {
        console.error('UserComponent.reload: error getting user');
        console.error(fail);
      }
    });
  }

  setEditUser(): void {
    this.editUser = Object.assign({}, this.user);
  }

  clearEditUser(): void {
    this.editUser = null;
  }

  updateUser(user: User): void {
  this.userService.update(this.auth.getLoggedInUserId(), user).subscribe({
      next: (data: any) => {
          this.user = data;
          this.clearEditUser();
        },
      error: (fail: any) => {
        console.error(
          'UserListHttpComponent.updateUser(): error updating user:'
        );
        console.error(fail);
      },
    });
  }


}
