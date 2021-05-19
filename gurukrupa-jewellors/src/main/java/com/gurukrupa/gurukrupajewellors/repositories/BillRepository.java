package com.gurukrupa.gurukrupajewellors.repositories;

import java.time.LocalDate;
import java.util.List;

import com.gurukrupa.gurukrupajewellors.entities.Bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

	@Query("from Bill where billno=:billno")
	public Bill getBillByBillno(@Param("billno") String billno);
	
	@Query("from Bill where date=:date")
	public List<Bill>getBillByDate(@Param("date") LocalDate date);
	
	@Query("from Bill where customerid=:customerid")
	public List<Bill>getBillByCustomer(@Param("customerid") long customerid);
	
	@Query("from Bill where loginid=:loginid")
	public List<Bill>getBillByLogin(@Param("loginid") int loginid);
	
	@Query("from Bill where date BETWEEN :startdate AND :enddate")
	public List<Bill>getDatePeriodBill(@Param("startdate")LocalDate start,@Param("enddate")LocalDate end);
	
}