package com.api.maps.data.repository;

import com.api.maps.data.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface IAccountRepository  extends JpaRepository<Account, Integer> {

    @Query("SELECT " +
            "COALESCE(SUM(CASE WHEN fm.isEntry = false THEN fm.valueOverall ELSE 0 END), 0) AS totalEntryMovement, " +
            "(COALESCE(SUM(CASE WHEN fm.isEntry = false THEN fm.valueOverall ELSE 0 END), 0) ) AS totalBalance " +
            "FROM FinancialMovement fm " +
            "WHERE fm.account.id = ?1 AND fm.dateMovement <= ?2")
    Double getBalancePurchaseFinancialMoviment(Integer idAccount, Date dateMovement);

    @Query("SELECT " +
            "COALESCE(SUM(CASE WHEN fm.isEntry = true THEN fm.valueOverall ELSE 0 END), 0) AS totalEntryMovement, " +
            "(COALESCE(SUM(CASE WHEN fm.isEntry = true THEN fm.valueOverall ELSE 0 END), 0) ) AS totalBalance " +
            "FROM FinancialMovement fm " +
            "WHERE fm.account.id = ?1 AND fm.dateMovement <= ?2")
    Double getBalanceSaleFinancialMoviment(Integer idAccount, Date dateMovement);

    @Query("SELECT " +
            "COALESCE(SUM(CASE WHEN fr.isEntry = true THEN fr.valueRelease ELSE 0 END), 0) AS totalEntryRelease, " +
            "(COALESCE(SUM(CASE WHEN fr.isEntry = true THEN fr.valueRelease ELSE 0 END), 0) ) AS totalBalance " +
            "FROM FinancialRelease fr " +
            "WHERE fr.account.id = ?1 AND fr.dateMovement <= ?2")
    Double getBalanceSaleFinancialRelease(Integer idAccount, Date dateMovement);

    @Query("SELECT " +
            "COALESCE(SUM(CASE WHEN fr.isEntry = false THEN fr.valueRelease ELSE 0 END), 0) AS totalEntryRelease, " +
            "(COALESCE(SUM(CASE WHEN fr.isEntry = false THEN fr.valueRelease ELSE 0 END), 0) ) AS totalBalance " +
            "FROM FinancialRelease fr " +
            "WHERE fr.account.id = ?1 AND fr.dateMovement <= ?2")
    Double getBalancePurchaseFinancialRelease(Integer idAccount, Date dateMovement);



}
