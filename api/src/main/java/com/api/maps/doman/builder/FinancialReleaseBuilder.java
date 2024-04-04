package com.api.maps.doman.builder;

import com.api.maps.data.entity.FinancialRelease;
import com.api.maps.doman.dto.FinancialReleaseDTO;
import com.api.maps.doman.dto.ListFinancialReleaseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class FinancialReleaseBuilder {

    private final AccountBuilder accountBuilder;
    public FinancialRelease fromEntity(FinancialReleaseDTO financialReleaseDTO) {
        return new FinancialRelease(
                financialReleaseDTO.getId() != null ? financialReleaseDTO.getId() : null,
                financialReleaseDTO.getDescription(),
                financialReleaseDTO.getValueRelease(),
                financialReleaseDTO.getDateMovement(),
                financialReleaseDTO.isEntry(),
                financialReleaseDTO.getAccount() != null ? accountBuilder.fromEntity(financialReleaseDTO.getAccount()) : null
        );
    }

    public FinancialReleaseDTO fromDTO(FinancialRelease financialRelease) {
        return new FinancialReleaseDTO(
                financialRelease.getId() != null ? financialRelease.getId() : null,
                financialRelease.getDescription(),
                financialRelease.getValueRelease(),
                financialRelease.getDateMovement(),
                financialRelease.getAccount() != null ? accountBuilder.fromDTO(financialRelease.getAccount()) : null,
                financialRelease.isEntry()
        );
    }

    public ListFinancialReleaseDTO fromListFinancialReleaseDTO(FinancialRelease financialRelease) {
        return new ListFinancialReleaseDTO(
                financialRelease.getDescription(),
                financialRelease.getValueRelease(),
                financialRelease.getDateMovement(),
                financialRelease.isEntry()
        );
    }

    public List<ListFinancialReleaseDTO> fromListDTO(List<FinancialRelease> financialRelease){
        return financialRelease.stream().map(this::fromListFinancialReleaseDTO).collect(Collectors.toList());
    }

}
