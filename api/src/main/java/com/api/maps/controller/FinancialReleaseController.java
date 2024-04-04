package com.api.maps.controller;

import com.api.maps.doman.dto.FinancialReleaseDTO;
import com.api.maps.doman.dto.ListFinancialReleaseDTO;
import com.api.maps.doman.service.financialrelease.IFinancialReleaseService;
import com.api.maps.utils.jwt.JwtUtils;
import com.api.maps.utils.services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/api/financial-release")
public class FinancialReleaseController {

    private final IFinancialReleaseService financialReleaseService;

    @PostMapping("/sale")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> sale(@Valid @RequestBody FinancialReleaseDTO financialReleaseDTO) {
        financialReleaseService.sale(financialReleaseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/purchase")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> purchase(@RequestBody FinancialReleaseDTO financialReleaseDTO) {
        financialReleaseService.purchase(financialReleaseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ListFinancialReleaseDTO>> getAll(@RequestParam("idAccount") Integer idAccount) {
        List<ListFinancialReleaseDTO> financialReleases = financialReleaseService.getAll(idAccount);
        return ResponseEntity.ok().body(financialReleases);
    }
}
