package com.api.maps.data.repository;

import com.api.maps.data.entity.FinancialMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFinancialMovementRepository extends JpaRepository<FinancialMovement, Integer> {
    @Query("SELECT fm FROM FinancialMovement fm WHERE fm.account.id = ?1")
    List<FinancialMovement> getAll(Integer idAccount);
}
