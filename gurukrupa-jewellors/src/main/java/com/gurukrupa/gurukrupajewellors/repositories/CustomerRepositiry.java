package com.gurukrupa.gurukrupajewellors.repositories;

import java.util.List;

import com.gurukrupa.gurukrupajewellors.entities.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface CustomerRepositiry extends JpaRepository<Customer, Long> {
	
	@Query("from Customer where fname=:fname and mname=:mname and lname=:lname")
	public Customer findByFullName(@Param("fname") String fname,@Param("mname")String mname,@Param("lname")String lname);

	@Query("from Customer where code=:code")
	public Customer findByCode(@Param("code") String code);
	
	@Query("select CONCAT(fname,' ',mname,' ',lname) from Customer order by fname")
	public List<String>getAllNames();
	
	@Query("From Customer where contact=:contact")
	public Customer findByContact(@Param("contact") String contact);
}