package com.gurukrupa.gurukrupajewellors.repositories;

import java.util.List;

import com.gurukrupa.gurukrupajewellors.entities.Login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoginRepository extends JpaRepository<Login, Integer>{

	@Query("select userName from Login")
	public List<String>getAllLoginNames();
	
	public Login findByuserName(String userName);
	
	public Login findByPerson(String person);

}