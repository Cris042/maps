package com.api.maps.doman.dto;

import com.api.maps.doman.enums.TypeFinancialAssetsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialActiveDTO implements Serializable {

    private Integer id;

    @NotNull( message = "O campo name e obrigatorio!" )
    @NotBlank( message = "O campo name não pode ser nulo!")
    private String name;

    @NotNull( message = "O campo typeFinancialAssets e obrigatorio!" )
    private TypeFinancialAssetsEnum typeFinancialAssets;

    @NotNull( message = "O campo dateIssue e obrigatorio!" )
    private Date dateIssue;

    @NotNull( message = "O campo dateTerminus e obrigatorio!" )
    private Date dateTerminus;

}
