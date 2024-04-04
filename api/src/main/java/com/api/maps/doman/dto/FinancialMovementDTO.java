package com.api.maps.doman.dto;

import com.api.maps.data.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialMovementDTO implements Serializable {

    private Integer id;

    @NotNull( message = "O campo amount e obrigatorio!" )
    @Positive(message = "O campo amount deve ser positivo!")
    @DecimalMax(value = "99.9", message = "O campo valueRelease deve ser menor ou igual a 99.9")
    private Double amount;

    @NotNull( message = "O campo valueOverall e obrigatorio!" )
    @Positive(message = "O campo valueOverall deve ser positivo!")
    @DecimalMax(value = "99.9", message = "O campo valueRelease deve ser menor ou igual a 99.9")
    private Double valueOverall;

    @NotNull( message = "O campo nameFinancialActive e obrigatorio!" )
    @NotBlank( message = "O campo nameFinancialActive não pode ser nulo!")
    private String nameFinancialActive;

    @NotNull( message = "O campo dateMovement e obrigatorio!" )
    private Date dateMovement;

    @NotNull( message = "O campo date account e obrigatorio!" )
    private AccountDTO account;

    private boolean isEntry;

}
