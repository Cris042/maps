package com.api.maps.service;


import com.api.maps.data.entity.FinancialActive;
import com.api.maps.data.repository.IFinancialActiveRepository;

import com.api.maps.doman.enums.TypeFinancialAssetsEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class FinancialActiveRepositoryTest {

    @Autowired
    IFinancialActiveRepository financialActiveRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveFinancialActive() {

        Date dateIssue = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateIssue);
        calendar.add(Calendar.DAY_OF_MONTH, 30);

        Date dateTerminus = calendar.getTime();

        TypeFinancialAssetsEnum typeFinancialAssets = TypeFinancialAssetsEnum.RV;

        FinancialActive financialActive = new FinancialActive(
                null,
                "name",
                typeFinancialAssets,
                dateIssue,
                dateTerminus,
                true
        );

        financialActiveRepository.save(financialActive);

        List<FinancialActive> result = financialActiveRepository.findAllActive();

        assertEquals(129, result.size());
    }
    
    @Test
    void testUpdateFinancialActive() {
        
        TypeFinancialAssetsEnum typeFinancialAssets = TypeFinancialAssetsEnum.RF;
        
        FinancialActive financialActive = financialActiveRepository.findByName("ATIVO1").get();
        
        financialActive.setTypeFinancialAssets( typeFinancialAssets );

        financialActiveRepository.save(financialActive);

        FinancialActive financialActiveUpdate = financialActiveRepository.findByName("ATIVO1").get();

        assertEquals(typeFinancialAssets, financialActiveUpdate.getTypeFinancialAssets());
    }

    @Test
    void testDeleteFinancialActive() {

        FinancialActive financialActive = financialActiveRepository.findByName("ATIVO127").get();

        financialActiveRepository.delete( financialActive );

        boolean isDelet = financialActiveRepository.findByName("ATIVO127").isPresent();

        assertEquals(false, isDelet);
    }


}
