import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Address } from 'src/app/models/address';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-user-account',
  templateUrl: './user-account.component.html',
  styleUrls: ['./user-account.component.css']
})
export class UserAccountComponent implements OnInit {

  constructor(private userService: UserService, private auth: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.loadUser()
  }

user: User = new User;
editUser: User | null = null;
selected: User | null = null;
addAddress = new Address();
newAddress: Address | null = null

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

//  Create Address form with field addAddress.
//   Then get address id after it has been added.
//   Make a newAddress field and set it to data from the addAddress function.
//   Then set editUser.address to newAddress.
//   Then call updateUser(editUser)
// Will need address service for a create and a show method. Create address controller in Boot project
//Create API endpoint to add an address to a user that takes a userId and request body address. Handle that on the back end


  deleteUserAccount(){
    if(confirm("Are you sure to delete your account?")){
    this.userService.disable(this.auth.getLoggedInUserId()).subscribe({
      next: (data: any) => {
          this.user = data;
          // this.clearEditUser();
         this.auth.logout();
         this.router.navigateByUrl('/welcome');
        },
      error: (fail: any) => {
        console.error(
          'UserListHttpComponent.disableUser(): error disabling user:'
        );
        console.error(fail);
      },
    });
  }
  }
}
