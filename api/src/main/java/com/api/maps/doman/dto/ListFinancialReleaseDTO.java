package com.api.maps.doman.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListFinancialReleaseDTO implements Serializable {

    private String description;
    private Double valueRelease;
    private Date dateMovement;
    private boolean isEntry;
}
