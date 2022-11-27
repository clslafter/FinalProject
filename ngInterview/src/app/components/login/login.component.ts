import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginUser = new User();


  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  login(user: User) {
    console.log('Logging user:');
    console.log(user);

    this.auth.login(user.username, user.password).subscribe({
      next: (loggedInUser) => {
        console.log(loggedInUser);
            this.router.navigateByUrl('/home');
          },
      error: (fail) => {
        console.error('LoginComponent.login(): Error logging in account');
        console.error(fail);
      }
    });
  }

}
