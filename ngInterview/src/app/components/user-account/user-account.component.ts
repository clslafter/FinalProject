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
}
