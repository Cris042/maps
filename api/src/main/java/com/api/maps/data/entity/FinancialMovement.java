package com.api.maps.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tb_financial_movement")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinancialMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(columnDefinition = "DECIMAL(10, 2)",nullable = false)
    private Double amount;

    @Column(columnDefinition = "DECIMAL(10, 8)",nullable = false)
    private Double valueOverall;

    @Column()
    private boolean isEntry;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private FinancialActive financialActive;

    @Column(nullable = false)
    private Date dateMovement;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;
}
