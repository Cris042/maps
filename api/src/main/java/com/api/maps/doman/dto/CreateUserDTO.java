package com.api.maps.doman.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO implements Serializable {

    @NotNull( message = "O campo username e obrigatorio!" )
    @NotBlank( message = "O campo username não pode ser nulo!")
    @Size(min = 3, max = 120, message ="O campo username deve ter entre 3 e 120 caractres")
    private String username;

    @NotNull( message = "O campo password e obrigatorio!" )
    @NotBlank( message = "O campo password não pode ser nulo!")
    @Size(min = 3, max = 120, message ="O campo password deve ter entre 3 e 120 caractres")
    private String password;

}
