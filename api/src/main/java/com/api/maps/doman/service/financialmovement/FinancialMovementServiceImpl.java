package com.api.maps.doman.service.financialmovement;

import com.api.maps.data.entity.FinancialActive;
import com.api.maps.data.entity.Position;
import com.api.maps.data.repository.IAccountRepository;
import com.api.maps.data.repository.IFinancialActiveRepository;
import com.api.maps.data.repository.IFinancialMovementRepository;
import com.api.maps.data.repository.IPositionRepository;
import com.api.maps.doman.builder.FinancialActiveBuilder;
import com.api.maps.doman.builder.FinancialMovementBuilder;
import com.api.maps.doman.dto.FinancialActiveDTO;
import com.api.maps.doman.dto.FinancialMovementDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class FinancialMovementServiceImpl implements IFinancialMovementService {

    private final IAccountRepository accountRepository;
    private final IFinancialMovementRepository financialMovementRepository;
    private final IFinancialActiveRepository finacialActiveRepository;
    private final IPositionRepository positionRepository;
    private final FinancialMovementBuilder financialMovementBuilder;

    @Override
    @Transactional
    public synchronized void sale(FinancialMovementDTO financialMovementDTO) {

        accountRepository.findById(financialMovementDTO.getAccount().getId()).orElseThrow(() ->
                new EntityNotFoundException("Account does not exist!"));

        FinancialActive financialActive = finacialActiveRepository.findByName(financialMovementDTO.getNameFinancialActive()).orElseThrow(() ->
                new EntityNotFoundException("Financial Active does not exist!"));

        if (financialMovementDTO.getDateMovement().compareTo(financialActive.getDateTerminus()) > 0) {
            throw new RuntimeException("Date invalid! Date Movement is greater than date terminus!");
        }

        if(financialMovementDTO.getDateMovement().compareTo(financialActive.getDateIssue()) < 0){
            throw new RuntimeException("Date invalid! Date Movement is smaller than date issue!");
        }

        if(!financialActive.isActive()){
            throw new RuntimeException("Financial Active is not active!");
        }

        if( financialMovementDTO.getAmount() == 0 && financialMovementDTO.getValueOverall() == 0){
            throw new RuntimeException("The value and amount must be greater than zero");
        }

        Position position = positionRepository.getPositionsByActive(financialActive.getName());

        double amountAvailable =
                positionRepository.getAmountAvailableByNameActive(financialActive.getName(), financialMovementDTO.getDateMovement());

        System.out.println( amountAvailable );

        if(financialMovementDTO.getAmount() > amountAvailable){
            throw new RuntimeException("The amount must be less than or equal to the amount of the financial asset!");
        }

        financialMovementDTO.setEntry(true);
        financialMovementRepository.save(financialMovementBuilder.fromEntity(financialMovementDTO,financialActive));

        positionRepository.addEntryValue(position.getId(), financialMovementDTO.getAmount());
//        positionRepository.updateValueGain(financialActive.getName());
        positionRepository.updateValueMarketplace(financialActive.getName(), financialMovementDTO.getAmount());
        positionRepository.updateValueYield(financialActive.getName());


    }

    @Override
    @Transactional
    public synchronized void purchase(FinancialMovementDTO financialMovementDTO) {

        accountRepository.findById(financialMovementDTO.getAccount().getId()).orElseThrow(() ->
                new EntityNotFoundException("Account does not exist!"));

        FinancialActive financialActive = finacialActiveRepository.findByName(financialMovementDTO.getNameFinancialActive()).orElseThrow(() ->
                new EntityNotFoundException("Financial Active does not exist!"));

        if (financialMovementDTO.getDateMovement().compareTo(financialActive.getDateTerminus()) > 0) {
            throw new RuntimeException("Date invalid! Date Movement is greater than date terminus!");
        }

        if(financialMovementDTO.getDateMovement().compareTo(financialActive.getDateIssue()) < 0){
            throw new RuntimeException("Date invalid! Date Movement is greater than date issue!");
        }

        if(!financialActive.isActive()){
            throw new RuntimeException("Financial Active is not active!");
        }

        if( financialMovementDTO.getAmount() == 0 && financialMovementDTO.getValueOverall() == 0){
            throw new RuntimeException("The value and amount must be greater than zero");
        }

        double resultSale =
                accountRepository.getBalanceSaleFinancialMoviment(financialMovementDTO.getAccount().getId(), financialMovementDTO.getDateMovement()) +
                        accountRepository.getBalanceSaleFinancialRelease(financialMovementDTO.getAccount().getId(),financialMovementDTO.getDateMovement());

        double resultPurchase =
                accountRepository.getBalancePurchaseFinancialRelease(financialMovementDTO.getAccount().getId(), financialMovementDTO.getDateMovement()) +
                        accountRepository.getBalancePurchaseFinancialMoviment(financialMovementDTO.getAccount().getId(), financialMovementDTO.getDateMovement());

        double balance = resultSale - resultPurchase;

        if( balance < financialMovementDTO.getAmount() ){
            throw new RuntimeException("Insufficient funds!");
        }

        Position position = positionRepository.getPositionsByActive(financialActive.getName());

        financialMovementDTO.setEntry(false);
        financialMovementRepository.save(financialMovementBuilder.fromEntity(financialMovementDTO,financialActive));

        positionRepository.addExitValue(position.getId(), financialMovementDTO.getAmount());
//        positionRepository.updateValueGain(financialActive.getName());
        positionRepository.updateValueMarketplace(financialActive.getName(), financialMovementDTO.getAmount());
        positionRepository.updateValueYield(financialActive.getName());


    }

    @Override
    @Transactional(readOnly = true)
    public synchronized List<FinancialMovementDTO> getAll(Integer idAccount) {

        accountRepository.findById(idAccount).orElseThrow(() ->
                new EntityNotFoundException("Account does not exist!"));

        return financialMovementBuilder.fromListDTO(financialMovementRepository.getAll(idAccount));

    }
}
