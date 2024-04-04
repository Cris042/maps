package com.api.maps.doman.service.auth;


import com.api.maps.data.entity.User;
import com.api.maps.doman.dto.CreateUserDTO;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IAuthService {


    Boolean existsByUsername(String username);

    ResponseEntity<?> signin(CreateUserDTO createUserDTO);

    void createPredefinedUsers();

    void signup(CreateUserDTO createUserDTO);
}
