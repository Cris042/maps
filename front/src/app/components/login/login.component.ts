import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent 
{
  username: string = ''; 
  password: string = ''; 

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit(): void 
  {
    this.authService.login(this.username, this.password).subscribe
    (
      response => 
      {
        console.log('Login realizado com sucesso:', response);
        this.router.navigate(['/dashboard']);
      },
      error => 
      {
        console.error('Erro ao fazer login:', error);
      }
    );
  }
}