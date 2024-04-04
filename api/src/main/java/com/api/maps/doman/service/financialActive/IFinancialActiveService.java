package com.api.maps.doman.service.financialActive;

import com.api.maps.doman.dto.FinancialActiveDTO;

import java.util.List;

public interface IFinancialActiveService {
    boolean saveFinancialActive( FinancialActiveDTO financialActiveDTO );
    boolean updateFinancialActive(FinancialActiveDTO financialActiveDTO, Integer idFinancialActive);
    boolean deleteFinancialActive(Integer idFinancialActive);
    FinancialActiveDTO getFinancialActive( Integer idFinancialActive );
    List<FinancialActiveDTO> getAllFinancialActive();
}
