package com.api.maps.data.repository;

import com.api.maps.data.entity.FinancialActive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFinancialActiveRepository extends JpaRepository<FinancialActive, Integer> {

    @Query("SELECT f FROM FinancialActive f WHERE f.name = ?1")
    Optional<FinancialActive> findByName(String name);

    @Query("SELECT f FROM FinancialActive f WHERE f.active = true")
    List<FinancialActive> findAllActive();
}
