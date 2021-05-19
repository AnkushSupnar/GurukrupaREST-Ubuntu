package com.gurukrupa.gurukrupajewellors.controller;
import java.util.List;

import com.gurukrupa.gurukrupajewellors.entities.Item;
import com.gurukrupa.gurukrupajewellors.repositories.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
public class ItemController {
	@Autowired
	private ItemRepository repository;
	@GetMapping(value="/items",produces =MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity< List<Item>>getAllItems()
	{
		return new ResponseEntity<List<Item>>(repository.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(value="/items/byid/{id}",produces =MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Item> getItemById(@PathVariable("id") long id)
	{
		Item item = repository.findById(id).orElse(null);
		if(item!=null)
		{
			return new ResponseEntity<Item>(item,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping(value="/items/byname/{itemname}",produces=MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Item>getByName(@PathVariable("itemname") String name)
	{
		Item item = repository.getItemByName(name);
		if(item!=null)
		return ResponseEntity.ok().body(item);
		else
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
	}
	@GetMapping(value="/items/allnames",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<String>>getAllItemNames()
	{
		return new ResponseEntity<List<String>>(repository.getAllItemNames(),HttpStatus.OK);
	}
	@PostMapping(value="/items/save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Item>saveItem(@RequestBody Item item)
	{
		return new ResponseEntity<Item>(repository.save(item),HttpStatus.OK);
		
	}
	@PutMapping(value="/items/update",consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE )
	private ResponseEntity<Item>updateItem(@RequestBody Item item)
	{
		Item i =repository.findById(item.getId()).orElse(null);
		if(i==null)
		{
			return new ResponseEntity<Item>(i,HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<Item>(repository.save(i),HttpStatus.OK);
		}
	}
	
}
