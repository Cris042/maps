import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8088';
  private loggedIn = false; 

  constructor(private http: HttpClient, private router: Router) 
  {
    this.checkAuthentication(); 
  }

  login(username: string, password: string): Observable<any> 
  {
    const body = { password, username  };
    return this.http.post<any>(`${this.apiUrl}/api/auth/signin`, body)
      .pipe(
        tap(() => 
        {
          this.loggedIn = true;
          localStorage.setItem('isLoggedIn', 'true'); 
        })
      );
  }

  logout(): void 
  {
    this.loggedIn = false;
    localStorage.removeItem('isLoggedIn'); 
    this.router.navigate(['/']);
  }

  register(username: string, password: string): Observable<any> 
  {
    const body = {  username, password };
    return this.http.post<any>(`${this.apiUrl}/api/auth/signup`, body);
  }
  

  private checkAuthentication(): void 
  {
    const isLoggedIn = localStorage.getItem('isLoggedIn');
    this.loggedIn = isLoggedIn === 'true';
  }

  isLoggedIn(): boolean 
  {
    return this.loggedIn; 
  }
}
