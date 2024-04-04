package com.api.maps.doman.service.financialrelease;

import com.api.maps.doman.dto.FinancialReleaseDTO;
import com.api.maps.doman.dto.ListFinancialReleaseDTO;

import java.util.List;

public interface IFinancialReleaseService {

    void sale(FinancialReleaseDTO financialReleaseDTO);
    void purchase(FinancialReleaseDTO financialReleaseDTO);
    List<ListFinancialReleaseDTO> getAll(Integer idAccount);
}
