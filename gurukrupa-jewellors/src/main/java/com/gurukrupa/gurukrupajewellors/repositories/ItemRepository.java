package com.gurukrupa.gurukrupajewellors.repositories;

import java.util.List;

import com.gurukrupa.gurukrupajewellors.entities.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface ItemRepository extends JpaRepository<Item, Long> {

	@Query("from Item where itemname=:name")
	public Item getItemByName(@Param("name") String name);
	
	@Query("select itemname from Item")
	public List<String>getAllItemNames();

	
	
}