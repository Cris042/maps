package com.api.maps.doman.service.financialmovement;

import com.api.maps.doman.dto.FinancialMovementDTO;

import java.util.List;

public interface IFinancialMovementService {

    void sale(FinancialMovementDTO financialMovementDTO);
    void purchase(FinancialMovementDTO financialMovementDTO);
    List<FinancialMovementDTO> getAll(Integer idAccount);
}
