package com.api.maps.doman.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponseDTO implements Serializable {

    private String username;
    private Collection<? extends GrantedAuthority> roles;

}
