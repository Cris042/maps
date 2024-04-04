package com.api.maps.data.entity;

import com.api.maps.doman.enums.TypeFinancialAssetsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_position")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nameActive;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_active",nullable = false)
    private TypeFinancialAssetsEnum typeFinancialAssets;

    @Column(columnDefinition = "DEFAULT 0.0")
    private Double amountAvailable;

    @Column(columnDefinition = "DEFAULT 0.0")
    private Double valueMarketplace;

    @Column(columnDefinition = "DEFAULT 0.0")
    private Double valueYield;

    @Column(columnDefinition = "DEFAULT 0.0")
    private Double valueGain;

}
