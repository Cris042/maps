package com.api.maps.doman.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialReleaseDTO implements Serializable {

    private Integer id;

    @NotNull( message = "O campo description e obrigatorio!" )
    @NotBlank( message = "O campo description não pode ser nulo!")
    private String description;

    @NotNull( message = "O campo valueRelease e obrigatorio!" )
    @Positive(message = "O campo valueRelease deve ser positivo!")
    @DecimalMax(value = "99.9", message = "O campo valueRelease deve ser menor ou igual a 99.9")
    private Double valueRelease;

    @NotNull( message = "O campo dateMovement e obrigatorio!" )
    private Date dateMovement;

    @NotNull( message = "O campo date account e obrigatorio!" )
    private AccountDTO account;

    private boolean isEntry;

}
