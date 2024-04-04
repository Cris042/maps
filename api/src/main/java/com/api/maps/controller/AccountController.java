package com.api.maps.controller;

import com.api.maps.doman.service.account.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final IAccountService accountService;

    @GetMapping("/balance/{accountId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Double> getBalance(@PathVariable("accountId") Integer accountId,
                                             @RequestParam("date") String date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date dateConvert = dateFormat.parse(date);
            double balance = accountService.getBalance(accountId, dateConvert);
            return ResponseEntity.ok().body(balance);
        } catch (ParseException e) {

            return ResponseEntity.badRequest().build();
        }

    }
}
