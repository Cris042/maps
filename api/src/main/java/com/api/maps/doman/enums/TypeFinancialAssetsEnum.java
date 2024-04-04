package com.api.maps.doman.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeFinancialAssetsEnum {
    RV(1,"Renda Variável"),
    RF(2,"Renda Fixa"),
    FUNDO(3,"Fundo de Investimento");

    private final Integer id;
    private final String description;
}
