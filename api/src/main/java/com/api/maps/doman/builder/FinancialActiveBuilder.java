package com.api.maps.doman.builder;

import com.api.maps.data.entity.FinancialActive;
import com.api.maps.doman.dto.FinancialActiveDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class FinancialActiveBuilder {

    public FinancialActive fromEntity(FinancialActiveDTO financialActiveDTO) {
        return new FinancialActive(
                financialActiveDTO.getId() != null ? financialActiveDTO.getId() : null,
                financialActiveDTO.getName(),
                financialActiveDTO.getTypeFinancialAssets(),
                financialActiveDTO.getDateIssue(),
                financialActiveDTO.getDateTerminus(),
                true
        );
    }

    public FinancialActiveDTO fromDTO(FinancialActive financialActive) {
        return new FinancialActiveDTO(
                financialActive.getId() != null ? financialActive.getId() : null,
                financialActive.getName(),
                financialActive.getTypeFinancialAssets(),
                financialActive.getDateIssue(),
                financialActive.getDateTerminus()
        );
    }

    public List<FinancialActiveDTO> fromListDTO(List<FinancialActive> financialActive){
        return financialActive.stream().map(this::fromDTO).collect(Collectors.toList());
    }
}
