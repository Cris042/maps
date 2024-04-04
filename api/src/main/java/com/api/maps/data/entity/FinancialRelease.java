package com.api.maps.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tb_financial_release")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinancialRelease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false)
    private String description;

    @Column(columnDefinition = "DECIMAL(10, 8)",nullable = false)
    private Double valueRelease;

    @Column(nullable = false)
    private Date dateMovement;

    @Column()
    private boolean isEntry;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;
}
