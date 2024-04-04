package com.api.maps.doman.dto;

import com.api.maps.doman.enums.TypeFinancialAssetsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionDTO implements Serializable {

    private Integer id;
    private String nameActive;
    private TypeFinancialAssetsEnum typeFinancialAssets;
    private Double amountAvailable;
    private Double valueMarketplace;
    private Double valueYield;
    private Double valueGain;

    PositionDTO(Integer id) {
        this.id = id;
    }

}
