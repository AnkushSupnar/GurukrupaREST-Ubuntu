package com.gurukrupa.gurukrupajewellors.repositories;

import java.util.List;

import com.gurukrupa.gurukrupajewellors.entities.Bank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {

		
	@Query("select bankname from Bank")
	public List<String>getAllBankNames();
	
	@Query("select balance from Bank where id=:id")
	public double getBankBalance(@Param("id") int id);

	@Query("from Bank where bankname=:bankname")
	public Bank getBankByBankName(@Param("bankname")String bankname);
	
	//@Query("update Bank set balance=balance+:balance where id=:id")
	//public int addBankBalance(@Param("id") int id,@Param("balance")double balance);
	
//	@Query("update Bank set balance=balance-:balance where id=:id")
//	public int resuceBankBalance(@Param("id")int id,@Param("balance") double balance);
	
	default int addBankBalance(int id,double balance)
	{
		Bank b = findById(id).orElse(null);
		if(b!=null)
		{
			b.setBalance(b.getBalance()+balance);
			save(b);
			return 1;
		}
		else
		{
			return 0;
		}
	}

	default int reduceBankBalance(int id,double balance)
	{
		Bank b = findById(id).orElse(null);
		if(b!=null)
		{
			b.setBalance(b.getBalance()-balance);
			save(b);
			return 1;
		}
		else
		{
			return 0;
		}
	}

}