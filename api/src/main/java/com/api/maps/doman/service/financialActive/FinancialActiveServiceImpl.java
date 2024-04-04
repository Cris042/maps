package com.api.maps.doman.service.financialActive;

import com.api.maps.data.entity.FinancialActive;
import com.api.maps.data.entity.Position;
import com.api.maps.data.repository.IFinancialActiveRepository;
import com.api.maps.data.repository.IPositionRepository;
import com.api.maps.doman.builder.FinancialActiveBuilder;
import com.api.maps.doman.dto.FinancialActiveDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FinancialActiveServiceImpl implements IFinancialActiveService{

    private final IFinancialActiveRepository financialActiveRepository;
    private final FinancialActiveBuilder financialActiveBuilder;
    private final IPositionRepository positionRepository;


    @Override
    @Transactional
    public synchronized boolean saveFinancialActive( FinancialActiveDTO financialActiveDTO ) {

        if( financialActiveRepository.findByName(financialActiveDTO.getName()).isPresent() ){
            throw new RuntimeException("Financial Active already exists");
        }

        if (financialActiveDTO.getDateIssue().compareTo(financialActiveDTO.getDateTerminus()) >= 0) {
            throw new RuntimeException("Date invalid! Date issue is greater than date terminus!");
        }

        FinancialActive financialActive = financialActiveBuilder.fromEntity(financialActiveDTO);

        financialActiveRepository.save(financialActive);

        Position position = new Position(
                null,
                financialActive.getName(),
                financialActive.getTypeFinancialAssets(),
                0.0,
                0.0,
                0.0,
                0.0
        );

        positionRepository.save(position);

        return true;
    }

    @Override
    @Transactional
    public synchronized boolean updateFinancialActive( FinancialActiveDTO financialActiveDTO, Integer idFinancialActive ) {

        FinancialActive financialActive = financialActiveRepository.findById(idFinancialActive).orElseThrow
                (() -> new RuntimeException("Financial Active not found"));

        if( !financialActive.getName().equals( financialActiveDTO.getName() ) ){
            if( financialActiveRepository.findByName(financialActiveDTO.getName()).isPresent() ){
                throw new RuntimeException("Financial Active already exists");
            }
        }

        if (financialActiveDTO.getDateIssue().compareTo(financialActiveDTO.getDateTerminus()) >= 0) {
            throw new RuntimeException("Date invalid! Date issue is greater than date terminus!");
        }

        financialActive.setName(financialActiveDTO.getName());
        financialActive.setTypeFinancialAssets(financialActiveDTO.getTypeFinancialAssets());
        financialActive.setDateIssue(financialActiveDTO.getDateIssue());
        financialActiveDTO.setDateTerminus(financialActiveDTO.getDateTerminus());

        return true;

    }

    @Override
    @Transactional
    public synchronized boolean deleteFinancialActive( Integer idFinancialActive ) {

         FinancialActive financialActive = financialActiveRepository.findById(idFinancialActive).orElseThrow
                 (() -> new RuntimeException("Financial Active not found"));

         financialActive.setActive(false);

         financialActiveRepository.save(financialActive);

        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public synchronized FinancialActiveDTO getFinancialActive( Integer idFinancialActive ) {

        FinancialActive financialActive = financialActiveRepository.findById(idFinancialActive).orElseThrow
                (() -> new RuntimeException("Financial Active not found"));

        return financialActiveBuilder.fromDTO(financialActive);
    }

    @Override
    @Transactional(readOnly = true)
    public synchronized List<FinancialActiveDTO> getAllFinancialActive() {

        List<FinancialActive> financialActives = financialActiveRepository.findAllActive();

        return financialActiveBuilder.fromListDTO(financialActives);
    }
}
