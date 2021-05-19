package com.gurukrupa.gurukrupajewellors.repositories;

import java.util.List;

import com.gurukrupa.gurukrupajewellors.entities.Counter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends JpaRepository<Counter, Integer> {

	@Query("from Counter where counterName=:name")
	public Counter getCounterByName(String name);
	
	@Query("select counterName from Counter")
	public List<String>getAllCounterNames();
	
	@Query("select billinitial from Counter where id=:i")
	public char getCounterBillInitial(@Param("i") int id);
	
	public Counter findByPerson(String person);
}