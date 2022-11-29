import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(private userService: UserService) { }

users: User[] = [];

  ngOnInit(): void {
    this.populateUsers()
  }
populateUsers(){
  this.userService.index().subscribe({
    next: (data) => {
      this.users = data;
    },
    error: (fail) => {
      console.error('AdminComponent.loadUsers: error getting users');
      console.error(fail);
    }
  })
}
}
