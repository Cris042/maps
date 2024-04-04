package com.api.maps.data.entity;

import com.api.maps.doman.enums.TypeFinancialAssetsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_financial_active")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinancialActive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type",nullable = false)
    private TypeFinancialAssetsEnum typeFinancialAssets;

    @Column(nullable = false)
    private Date dateIssue;

    @Column(nullable = false)
    private Date dateTerminus;

    private boolean active;


}
