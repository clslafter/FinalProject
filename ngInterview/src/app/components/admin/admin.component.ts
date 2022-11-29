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

disableUser(uid: number){
  if(confirm("Are you sure to disable this account?")){
    this.userService.disable(uid).subscribe({
      next: (data: any) => {
          //this.user = data;
          this.populateUsers();
        },
      error: (fail: any) => {
        console.error(
          'AdminComponent.disableUser(): error disabling user:'
        );
        console.error(fail);
      },
    });
  }
  }

  enableUser(uid: number){
      this.userService.enable(uid).subscribe({
        next: (data: any) => {
            //this.user = data;
            this.populateUsers();
          },
        error: (fail: any) => {
          console.error(
            'AdminComponent.enableUser(): error enabling user:'
          );
          console.error(fail);
        },
      });
    }
}
