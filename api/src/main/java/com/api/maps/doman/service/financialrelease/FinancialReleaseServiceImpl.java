package com.api.maps.doman.service.financialrelease;

import com.api.maps.data.entity.Account;
import com.api.maps.data.entity.FinancialRelease;
import com.api.maps.data.repository.IAccountRepository;
import com.api.maps.data.repository.IFinancialReleaseRepository;
import com.api.maps.doman.builder.FinancialReleaseBuilder;
import com.api.maps.doman.dto.FinancialReleaseDTO;
import com.api.maps.doman.dto.ListFinancialReleaseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class FinancialReleaseServiceImpl implements IFinancialReleaseService {

    private final IFinancialReleaseRepository financialReleaseRepository;
    private final IAccountRepository accountRepository;
    private final FinancialReleaseBuilder financialReleaseBuilder;

    @Override
    @Transactional
    public synchronized void sale(FinancialReleaseDTO financialReleaseDTO) {

        accountRepository.findById(financialReleaseDTO.getAccount().getId()).orElseThrow(() ->
                new EntityNotFoundException("Account does not exist!"));

        if( financialReleaseDTO.getValueRelease() == 0){
            throw new RuntimeException("The value must be greater than zero");
        }

        financialReleaseDTO.setEntry(true);
        FinancialRelease financialRelease = financialReleaseBuilder.fromEntity(financialReleaseDTO);
        financialReleaseRepository.save(financialRelease);

    }

    @Override
    @Transactional
    public synchronized void purchase(FinancialReleaseDTO financialReleaseDTO) {

        accountRepository.findById(financialReleaseDTO.getAccount().getId()).orElseThrow(() ->
                new EntityNotFoundException("Account does not exist!"));

        double balance = (accountRepository.getBalanceSaleFinancialMoviment( financialReleaseDTO.getAccount().getId(), financialReleaseDTO.getDateMovement() ) + accountRepository.getBalanceSaleFinancialRelease( financialReleaseDTO.getAccount().getId(), financialReleaseDTO.getDateMovement() ) ) -
                ( accountRepository.getBalancePurchaseFinancialRelease( financialReleaseDTO.getAccount().getId(), financialReleaseDTO.getDateMovement() ) + accountRepository.getBalancePurchaseFinancialMoviment( financialReleaseDTO.getAccount().getId(), financialReleaseDTO.getDateMovement() ));

        if (balance < financialReleaseDTO.getValueRelease()) {
            throw new RuntimeException("Insufficient balance");
        }

        if( financialReleaseDTO.getValueRelease() == 0){
            throw new RuntimeException("The value must be greater than zero");
        }

        financialReleaseDTO.setEntry(false);
        FinancialRelease financialRelease = financialReleaseBuilder.fromEntity(financialReleaseDTO);
        financialReleaseRepository.save(financialRelease);


    }
    @Override
    @Transactional(readOnly = true)
    public synchronized List<ListFinancialReleaseDTO> getAll(Integer idAccount) {

        accountRepository.findById(idAccount).orElseThrow(() ->
                new EntityNotFoundException("Account does not exist!"));

        return financialReleaseBuilder.fromListDTO(financialReleaseRepository.getAll(idAccount));
    }
}
