package com.gurukrupa.gurukrupajewellors.controller;
import java.util.List;

import com.gurukrupa.gurukrupajewellors.entities.Counter;
import com.gurukrupa.gurukrupajewellors.repositories.CounterRepository;

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
public class CounterController {

	@Autowired
	private CounterRepository repository;

	@GetMapping(value="/counters",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<Counter>>getAllCounters()
	{
		return new ResponseEntity<List<Counter>>(repository.findAll(),HttpStatus.OK);
	}
	@GetMapping(value = "/counters/byid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Counter> getCounterById(@PathVariable("id") int id) {
		Counter counter = repository.findById(id).orElse(null);
		if (counter != null)
			return new ResponseEntity<Counter>(counter, HttpStatus.OK);
		else
			return new ResponseEntity<Counter>(HttpStatus.BAD_REQUEST);
	}
	@GetMapping(value="/counters/byname/{counterName}",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Counter>getCounterByName(@PathVariable("counterName")String name)
	{
		Counter counter = repository.getCounterByName(name);
		if(counter!=null)
			return new ResponseEntity<Counter>(counter,HttpStatus.OK);
		else
			return new ResponseEntity<Counter>(HttpStatus.BAD_REQUEST);
	}
	@GetMapping(value="/counters/allnames",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<String>>getAllCounterNames()
	{
		return new ResponseEntity<List<String>>(repository.getAllCounterNames(),HttpStatus.OK);
	}
	@PostMapping(value="/counters/save",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Counter> saveCounter(@RequestBody Counter counter)
	{
		return new ResponseEntity<Counter>(repository.save(counter),HttpStatus.OK);
	}
	@PutMapping(value="/counters/update",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Counter>updateCounter(@RequestBody Counter counter)
	{
		if(repository.findById(counter.getId())==null)
			return new ResponseEntity<Counter>(new Counter(),HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<Counter>(repository.save(counter),HttpStatus.OK);
	}
	@GetMapping(value="/counters/getbillinitial/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Character>getCounterBillInitial(@PathVariable("id") int id)
	{
		return new ResponseEntity<Character>(repository.getCounterBillInitial(id),HttpStatus.OK);		
	}
	@GetMapping(value="/counters/byperson/{person}",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Counter>getCounterByPerson(@PathVariable("person") String person)
	{
		return new ResponseEntity<Counter>(repository.findByPerson(person),HttpStatus.OK);
	}
	
}
