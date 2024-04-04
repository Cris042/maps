import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component
({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent 
{
  newUser = {
    username: '',
    password: '',
  };

  constructor
  (
    private router: Router, 
    private authService: AuthService,
  ) {}
  
  ngOnInit( ): void { }

  buildForm() {}

  register() 
  {
  
    this.authService.register
    (
        this.newUser.username,
        this.newUser.password,
  
    ).subscribe();

    
    this.router.navigate(['/login']);
  }
}
