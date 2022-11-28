import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  selectedUser: null | User = null;


  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {

    let routeId = this.route.snapshot.paramMap.get('id');
    console.log(routeId);
    if (!this.selectedUser && routeId) {
      let userId = Number.parseInt(routeId);
      if(isNaN(userId)) {
        this.router.navigateByUrl('invalidId');
      } else {
        this.userService.show(userId).subscribe({
          next: (data) => {
            this.selectedUser = data;
          },
          error: (fail) => {
            console.error('UserProfileComponent.ngOnInit: user not found');
            this.router.navigateByUrl('userNotFound');
          }
        });
      }
    }
  }

  }


