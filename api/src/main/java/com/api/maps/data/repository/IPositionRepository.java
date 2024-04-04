package com.api.maps.data.repository;

import com.api.maps.data.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Repository
public interface IPositionRepository extends JpaRepository<Position, Integer> {

    @Query("SELECT p FROM Position p WHERE p.nameActive = ?1 ")
    Position getPositionsByActive(String nameActive);

    @Query("SELECT COALESCE(SUM(CASE WHEN fm.isEntry = true THEN fm.amount ELSE - fm.amount END), 0) " +
            "FROM FinancialMovement fm WHERE fm.financialActive.name = ?1 AND fm.isEntry = true AND fm.dateMovement <= ?2")
    double getAmountAvailableByNameActive(String nameActive, Date dateMovement);

    @Query("SELECT COALESCE(SUM(CASE WHEN fm.isEntry = true THEN fm.valueOverall ELSE - fm.valueOverall END), 0) " +
            "FROM FinancialMovement fm WHERE fm.financialActive.name = ?1 AND fm.isEntry = true AND fm.dateMovement <= ?2")
    double getValueMarketplaceByNameActive(String nameActive, Date dateMovement);

    @Query("SELECT COALESCE(SUM(CASE WHEN fm.isEntry = true THEN fm.valueOverall ELSE - fm.valueOverall END), 0)  FROM FinancialMovement fm WHERE " +
            "fm.financialActive.name = ?1 AND fm.dateMovement <= ?2" )
    double getValueYieldByNameActive(String nameActive, Date dateMovement);
    @Query("SELECT COALESCE(SUM(CASE WHEN fm.isEntry = true THEN fm.valueOverall ELSE fm.valueOverall END), 1) / COALESCE(SUM(CASE WHEN fm.isEntry = true THEN fm.amount ELSE fm.amount END), 1) - " +
            "COALESCE(SUM(CASE WHEN fm.isEntry = true THEN fm.valueOverall ELSE 0 END), 1) / COALESCE(SUM(CASE WHEN fm.isEntry = true THEN fm.amount ELSE 0 END), 1) " +
            "FROM FinancialMovement fm WHERE fm.financialActive.name = ?1 AND fm.dateMovement <= ?2")
    double getValueGainByNameActive(String nameActive, Date dateMovement);

    @Modifying
    @Transactional
    @Query("UPDATE Position p SET p.valueMarketplace = (SELECT COALESCE(SUM(fm.valueOverall), 0) * ?2 FROM FinancialMovement fm WHERE " +
            "fm.financialActive.name = ?1 AND fm.isEntry = true) WHERE p.nameActive = ?1")
    void updateValueMarketplace(String nameActive, double amountAvailable);

    @Modifying
    @Transactional
    @Query("UPDATE Position p SET p.valueYield = " +
            "(SELECT COALESCE(SUM(CASE WHEN fm.isEntry = true THEN fm.valueOverall ELSE - fm.valueOverall END), 0)  FROM FinancialMovement fm WHERE " +
            "fm.financialActive.name = ?1) " +
            "WHERE p.nameActive = ?1")
    void updateValueYield(String nameActive);

    @Modifying
    @Transactional
    @Query("UPDATE Position p SET p.valueGain = " +
            "(SELECT COALESCE(SUM(CASE WHEN fm.isEntry = true THEN fm.valueOverall ELSE fm.valueOverall END), 1) / COALESCE(SUM(CASE WHEN fm.isEntry = true THEN fm.amount ELSE fm.amount END), 1) - " +
            "COALESCE(SUM(CASE WHEN fm.isEntry = true THEN fm.valueOverall ELSE 0 END), 0) / COALESCE(SUM(CASE WHEN fm.isEntry = true THEN fm.amount ELSE 0 END), 0) " +
            "FROM FinancialMovement fm WHERE fm.financialActive.name = ?1) WHERE p.nameActive = ?1")
    void updateValueGain(String nameActive);

    @Transactional
    @Modifying
    @Query("UPDATE Position p SET p.amountAvailable = p.amountAvailable + ?2 WHERE p.id = ?1")
    void addEntryValue(Integer idPosition, double amountAvailable);

    @Transactional
    @Modifying
    @Query("UPDATE Position p SET p.amountAvailable = p.amountAvailable - ?2 WHERE p.id = ?1")
    void addExitValue(Integer idPosition, double amountAvailable);





}
