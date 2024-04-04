package com.api.maps.doman.builder;

import com.api.maps.data.entity.Account;
import com.api.maps.data.entity.Role;
import com.api.maps.data.entity.User;
import com.api.maps.doman.dto.CreateUserDTO;
import com.api.maps.doman.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserBuilder {

    private final AccountBuilder accountBuilder;

    public User fromAuthEntity(CreateUserDTO createUserDTO, Role role, Account account) {
        return new User(
                null,
                createUserDTO.getUsername(),
                createUserDTO.getPassword(),
                role,
                account,
                true
        );
    }
}
