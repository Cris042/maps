import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { AuthService } from '../../services/auth.service';
;

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit 
{
  isLoggedIn: boolean = false; 

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() 
  {
    this.isLoggedIn = this.checkIfLoggedIn();
  }

  logout(): void 
  {
    this.authService.logout();
  }

  private checkIfLoggedIn(): boolean 
  {
    return false;
  }
}
