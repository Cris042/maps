package com.api.maps.controller;

import com.api.maps.doman.service.position.IPositionService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/api/position")
public class PositionController {

    private final IPositionService positionService;


    @GetMapping("/{nameActive}")
    @PreAuthorize("hasRole('ADM') or hasRole('USER')")
    public ResponseEntity<Object> getPosition(@RequestParam("data") String date, @PathVariable("nameActive") String nameActive) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date dateConvert = dateFormat.parse(date);
            long executionId = positionService.findPositionByDate(dateConvert,nameActive);
            return ResponseEntity.ok().body(executionId);

        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/findById/{id}")
    @PreAuthorize("hasRole('ADM') or hasRole('USER')")
    public ResponseEntity<Object> getPositionResult(@PathVariable("id") long id) {
        Object result = positionService.getPositionResult(id);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.IM_USED).build();
        } else {
            return ResponseEntity.ok().body(result);
        }
    }


}
