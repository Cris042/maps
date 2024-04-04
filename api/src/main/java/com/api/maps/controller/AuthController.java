package com.api.maps.controller;

import com.api.maps.doman.dto.CreateUserDTO;
import com.api.maps.doman.service.auth.IAuthService;
import com.api.maps.utils.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {


    private final IAuthService userService;

    private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody CreateUserDTO loginRequest)
    {
        return userService.signin(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody CreateUserDTO signupRequest)
    {
        if (userService.existsByUsername( signupRequest.getUsername()) )
        {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        userService.signup(signupRequest);

        return ResponseEntity.ok("Usuario Salvo" );

    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser()
    {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("You've been signed out!");
    }
}
