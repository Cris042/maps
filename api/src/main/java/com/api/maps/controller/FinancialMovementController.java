package com.api.maps.controller;

import com.api.maps.doman.dto.FinancialMovementDTO;
import com.api.maps.doman.service.financialmovement.IFinancialMovementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/api/financial-movement")
public class FinancialMovementController {

    private final IFinancialMovementService financialMovementService;

    @PostMapping("/sale")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> sale(@Valid  @RequestBody FinancialMovementDTO financialMovementDTO) {
        financialMovementService.sale(financialMovementDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/purchase")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> purchase(@Valid @RequestBody FinancialMovementDTO financialMovementDTO) {
        financialMovementService.purchase(financialMovementDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<FinancialMovementDTO>> getAll(@RequestParam("idAccount") Integer idAccount) {
        List<FinancialMovementDTO> financialMovements = financialMovementService.getAll(idAccount);
        return ResponseEntity.ok().body(financialMovements);
    }
}
