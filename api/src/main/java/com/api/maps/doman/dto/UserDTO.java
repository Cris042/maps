package com.api.maps.doman.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private Integer id;
    private String username;
    private AccountDTO account;
    UserDTO(Integer id) {
        this.id = id;
    }
}
