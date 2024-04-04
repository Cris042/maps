package com.api.maps.controller;

import com.api.maps.doman.dto.FinancialActiveDTO;
import com.api.maps.doman.service.financialActive.IFinancialActiveService;
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
@RequestMapping("/api/financial-active")
public class FinancialActiveController {

    private final IFinancialActiveService financialActiveService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<?> saveFinancialActive(@Valid @RequestBody FinancialActiveDTO financialActiveDTO) {
        boolean saved = financialActiveService.saveFinancialActive(financialActiveDTO);
        if (saved) {
            return ResponseEntity.ok("Financial Active saved!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<?> updateFinancialActive(@Valid @RequestBody FinancialActiveDTO financialActiveDTO,
                                                      @PathVariable("id") Integer idFinancialActive) {
        boolean updated = financialActiveService.updateFinancialActive(financialActiveDTO, idFinancialActive);
        if (updated) {
            return ResponseEntity.ok("Financial Active updated!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<?> deleteFinancialActive(@PathVariable("id") Integer idFinancialActive) {
        boolean deleted = financialActiveService.deleteFinancialActive(idFinancialActive);
        if (deleted) {
            return ResponseEntity.ok("Financial Active deleted!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<FinancialActiveDTO> getFinancialActive(@PathVariable("id") Integer idFinancialActive) {
        FinancialActiveDTO financialActiveDTO = financialActiveService.getFinancialActive(idFinancialActive);
        if (financialActiveDTO != null) {
            return ResponseEntity.ok().body(financialActiveDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADM')")
    public ResponseEntity<List<FinancialActiveDTO>> getAllFinancialActive() {
        List<FinancialActiveDTO> financialActiveDTOs = financialActiveService.getAllFinancialActive();
        return ResponseEntity.ok().body(financialActiveDTOs);
    }
}
