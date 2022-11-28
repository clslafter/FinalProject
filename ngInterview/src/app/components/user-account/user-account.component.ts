import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-account',
  templateUrl: './user-account.component.html',
  styleUrls: ['./user-account.component.css']
})
export class UserAccountComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.loadUser()
  }

user: User = new User;
editUser: User | null = null;
selected: User | null = null;

  loadUser(): void {
    this.userService.show(1).subscribe({  //hardcoded #1 until we figure out how to grab current user id
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

  updateUser(id: number, user: User): void {
    this.userService.update(1, user).subscribe({
      next: (data: any) => {
          this.user = data;
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
