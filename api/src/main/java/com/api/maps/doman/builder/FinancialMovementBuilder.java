package com.api.maps.doman.builder;

import com.api.maps.data.entity.FinancialActive;
import com.api.maps.data.entity.FinancialMovement;
import com.api.maps.doman.dto.FinancialMovementDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class FinancialMovementBuilder {

    private final AccountBuilder accountBuilder;

    public FinancialMovement fromEntity(FinancialMovementDTO financialMovementDTO, FinancialActive financialActive ) {
        return new FinancialMovement(
                financialMovementDTO.getId() != null ? financialMovementDTO.getId() : null,
                financialMovementDTO.getAmount(),
                financialMovementDTO.getValueOverall(),
                financialMovementDTO.isEntry(),
                financialActive,
                financialMovementDTO.getDateMovement(),
                financialMovementDTO.getAccount() != null ? accountBuilder.fromEntity(financialMovementDTO.getAccount()) : null
        );
    }

    public FinancialMovementDTO fromDTO(FinancialMovement financialMovement) {
        return new FinancialMovementDTO(
                financialMovement.getId() != null ? financialMovement.getId() : null,
                financialMovement.getAmount(),
                financialMovement.getValueOverall(),
                financialMovement.getFinancialActive() != null ? financialMovement.getFinancialActive().getName() : null,
                financialMovement.getDateMovement(),
                financialMovement.getAccount() != null ? accountBuilder.fromDTO(financialMovement.getAccount()) : null,
                financialMovement.isEntry()

        );
    }

    public List<FinancialMovementDTO> fromListDTO(List<FinancialMovement> financialMovement){
        return financialMovement.stream().map(this::fromDTO).collect(Collectors.toList());
    }
}
