package com.api.maps.doman.dto;

import com.api.maps.data.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO implements Serializable {
    private Integer id;
    private Double balance;
    AccountDTO(Integer id) {
        this.id = id;
    }
}
