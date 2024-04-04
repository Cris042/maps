package com.api.maps.doman.service.account;

import com.api.maps.data.entity.Account;
import com.api.maps.data.repository.IAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService{

    private final IAccountRepository accountRepository;
    @Override
    @Transactional(readOnly = true)
    public synchronized double getBalance(Integer accountId, Date date) {
        accountRepository.findById(accountId).orElseThrow(() ->
                new EntityNotFoundException("Account does not exist!"));

        double resultSale =
                accountRepository.getBalanceSaleFinancialMoviment(accountId, date) +
                accountRepository.getBalanceSaleFinancialRelease(accountId, date);

        double resultPurchase =
                accountRepository.getBalancePurchaseFinancialRelease(accountId, date) +
                accountRepository.getBalancePurchaseFinancialMoviment(accountId, date);


        double result = resultSale - resultPurchase;

        return result;
    }
}
