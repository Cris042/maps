package com.api.maps.service;

import com.api.maps.data.entity.*;
import com.api.maps.data.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class SaleAndPurchaseTest {


    @Autowired
    IAccountRepository accountRepository;

    @Autowired
    IFinancialActiveRepository financialActiveRepository;

    @Autowired
    IPositionRepository positionRepository;

    @Autowired
    IFinancialReleaseRepository financialReleaseRepository;

    @Autowired
    IFinancialMovementRepository financialMovementRepository;

    @Autowired
    EntityManager entityManager;


    @Test
    void saleAndPurchase() {

        Account account = new Account(null, 0.0);
        FinancialActive financialActive = financialActiveRepository.findById(1).get();

        Date dateIssue = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateIssue);
        calendar.add(Calendar.DAY_OF_MONTH, - 30);
        Date dateTerminus = calendar.getTime();

        FinancialRelease financialReleasePurchase = new FinancialRelease(
                null,
                "teste venda",
                10.0,
                dateTerminus,
                false,
                account
        );

        FinancialRelease financialRelease = new FinancialRelease(
                null,
                "teste venda 2",
                60.0,
                dateTerminus,
                true,
                account
        );

        FinancialMovement financialMovement = new FinancialMovement(
                null,
                4.0,
                60.0,
                true,
                financialActive,
                dateTerminus,
                account
        );

        FinancialMovement financialMovementPurchase = new FinancialMovement(
                null,
                2.0,
                10.0,
                false,
                financialActive,
                dateTerminus,
                account
        );

        Position position = positionRepository.getPositionsByActive(financialActive.getName());

        entityManager.persist(account);
        entityManager.persist(financialRelease);
        entityManager.persist(financialReleasePurchase);
        entityManager.persist(financialMovement);
        entityManager.persist(financialMovementPurchase);

        positionRepository.addEntryValue(position.getId(), financialMovement.getAmount());
        positionRepository.updateValueGain(financialActive.getName());
        positionRepository.updateValueYield(financialActive.getName());

        entityManager.flush();

        double result = (accountRepository.getBalanceSaleFinancialMoviment( account.getId(), new Date() ) + accountRepository.getBalanceSaleFinancialRelease( account.getId(), new Date() ) ) -
                ( accountRepository.getBalancePurchaseFinancialRelease( account.getId(), new Date()) + accountRepository.getBalancePurchaseFinancialMoviment( account.getId(), new Date() ) );

        assertEquals(100.0,result);
    }
}
