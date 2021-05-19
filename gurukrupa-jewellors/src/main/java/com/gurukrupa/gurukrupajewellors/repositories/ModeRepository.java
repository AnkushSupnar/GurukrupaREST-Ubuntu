package com.gurukrupa.gurukrupajewellors.repositories;

import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

import com.gurukrupa.gurukrupajewellors.entities.Mode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
@Repository
public interface ModeRepository extends JpaRepository<Mode,Long>{

	@Query("from Mode where cucustomerid=:id")
	public List<Mode> getModeByCustomer(@Param("id") long customerId);
	
	@Query("from Mode where loginid=:loginid")
	public List<Mode> getModesByLogin(@Param("loginid") int loginid);
	
	@Query("from Mode where date=:date")
	public List<Mode>getModesByDate(@Param("date") LocalDate date);
	
	
}