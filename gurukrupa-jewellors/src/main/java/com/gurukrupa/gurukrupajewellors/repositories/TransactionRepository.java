package com.gurukrupa.gurukrupajewellors.repositories;

import com.gurukrupa.gurukrupajewellors.entities.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TransactionRepository extends JpaRepository<Transaction,Double> {

    @Transactional
    @Modifying
    @Query("delete from Transaction where billid=:billid")
    public void deleteTransactionByBillNo(@Param("billid")long billid);

    
}
