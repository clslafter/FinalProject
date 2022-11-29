import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Address } from 'src/app/models/address';
import { User } from 'src/app/models/user';
import { AddressService } from 'src/app/services/address.service';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-user-account',
  templateUrl: './user-account.component.html',
  styleUrls: ['./user-account.component.css']
})
export class UserAccountComponent implements OnInit {

  constructor(private userService: UserService, private addressService: AddressService, private auth: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.loadUser()
  }

user: User = new User;
editUser: User | null = null;
selected: User | null = null;
addAddress = new Address();
newAddress: Address | null = null
isAdmin: boolean = false;

  loadUser(): void {
    this.auth.getLoggedInUser().subscribe({
      next: (data) => {
        this.user = data;
        if(this.user.role === "ADMIN"){
          this.isAdmin = true;
        }
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
  this.userService.update(user).subscribe({
      next: (data: any) => {
          this.user = data;
          this.clearEditUser();
        },
      error: (fail: any) => {
        console.error(
          'UserAccountComponent.updateUser(): error updating user:'
        );
        console.error(fail);
      },
    });
  }


//  Make address service - make create address function.
//  Also in user service - make put function with controller mapping to add address to user
//  Make addNewAddress function here in component.ts
//   Then get address id after it has been added inside the next.
//   Make a newAddress field and set it to data from the addAddress function inside the next.
//   Then set editUser.address to newAddress inside the next.
//   Then call updateUser(editUser) (inside the next?)
// Will need address service for a create and a show method. Create address controller in Boot project

addressAdd(address: Address) {
  this.addressService.create(address).subscribe({
    next: (data) => {
      this.loadUser()
      this.newAddress = data;
      let aid = data.id;
      this.userService.addAddress(aid).subscribe({

          next: (userData) => {
            this.user = userData;
            this.clearEditUser();
            this.newAddress = null;
          },

          error: (fail) => {
            console.error('UserAccountComponent.reload: error adding address to user');
      console.error(fail);
          }


      })
      this.addAddress = new Address();
     // this.selected = null;

    },
    error: (fail) => {
      console.error('UserAccountComponent.reload: error creating address');
      console.error(fail);
    }
  });

}

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
