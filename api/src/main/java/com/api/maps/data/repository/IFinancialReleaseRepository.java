package com.api.maps.data.repository;

import com.api.maps.data.entity.FinancialRelease;
import com.api.maps.doman.dto.FinancialReleaseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFinancialReleaseRepository extends JpaRepository<FinancialRelease, Integer> {
    @Query("SELECT fr FROM FinancialRelease fr WHERE fr.account.id = ?1")
    List<FinancialRelease> getAll(Integer idAccount);
}
