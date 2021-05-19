package com.gurukrupa.gurukrupajewellors.controller;
import java.util.List;

import com.gurukrupa.gurukrupajewellors.entities.Customer;
import com.gurukrupa.gurukrupajewellors.repositories.CustomerRepositiry;

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
public class CustomerController {

	@Autowired
	private CustomerRepositiry repository;

	@GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<Customer>> getAllCustomer() {
		return new ResponseEntity<List<Customer>>(repository.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/customers/allnames")
	private ResponseEntity<List<String>> getAllCustomerNames() {
		return new ResponseEntity<List<String>>(repository.getAllNames(), HttpStatus.OK);
	}

	@GetMapping(value = "/customers/byid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id) {
		Customer c = repository.findById(id).orElse(null);
		if (c != null)
			return new ResponseEntity<Customer>(c, HttpStatus.OK);
		else
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/customers/bycode/{code}",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Customer> findByCode(@PathVariable("code") String code) {
		Customer c = repository.findByCode(code);
		if (c != null)
			return new ResponseEntity<Customer>(repository.findByCode(code), HttpStatus.OK);
		else
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
	@GetMapping(value="/customers/bycontact/{contact}",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Customer>findByContact(@PathVariable String contact)
	{
		return new ResponseEntity<Customer>(repository.findByContact(contact),HttpStatus.OK);
	}
	
	@GetMapping(value="/customers/byfullname/{fname}/{mname}/{lname}",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Customer>findByFullName(@PathVariable("fname") String fname,@PathVariable("mname") String mname,@PathVariable("lname") String lname)
	{
		return new ResponseEntity<Customer>(repository.findByFullName(fname, fname, fname),HttpStatus.OK);
	}
	@GetMapping(value="/customers/byname/{fname}",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Customer>findByName(@PathVariable("fname") String fname)
	{
		System.out.println("Got in REST="+fname);
		String name[] = fname.split(" ");
		return new ResponseEntity<Customer>(repository.findByFullName(name[0], name[1], name[2]),HttpStatus.OK);
	}
	
	@PostMapping(value="/customers/save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Customer>saveCustomer(@RequestBody Customer customer)
	{
		return new ResponseEntity<Customer>(repository.save(customer),HttpStatus.OK);
	}
	@PutMapping(value="/customers/update")
	private ResponseEntity<Customer>updateCustomer(@RequestBody Customer customer)
	{
		Customer c = repository.findById(customer.getId()).orElse(null);
		if(c!=null)
			return new ResponseEntity<Customer>(repository.save(customer),HttpStatus.OK);
		else
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
	
}
