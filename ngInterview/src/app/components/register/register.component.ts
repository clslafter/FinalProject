import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser = new User();

  errorMessage = '';

  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  register(user: User): void {
    console.log('Registering user:');
    console.log(user);
    this.newUser.enabled = true;
    this.newUser.role = 'USER';

    this.errorMessage = '';
    console.log('Logging user:');
    console.log(user);
    if(!user.username) {
      this.errorMessage += '*Username is Required* ';
    }
    if(!user.password) {
      this.errorMessage += ' *Password is Required*';
    }
    if(this.errorMessage) {
      return;
    }

    this.auth.register(user).subscribe({
      next: (registeredUser) => {
        this.auth.login(user.username, user.password).subscribe({
          next: (loggedInUser) => {
            this.router.navigateByUrl('/questions');
            this.errorMessage = '';
          },
          error: (problem) => {
            console.error('RegisterComponent.register(): Error logging in user:');
            console.error(problem);
            this.errorMessage = '*Invalid username or password*';
          }
        });
        this.errorMessage = '';
      },
      error: (fail) => {
        console.error('RegisterComponent.register(): Error registering account');
        console.error(fail);
      }
    });
  }

}
