package com.gurukrupa.gurukrupajewellors.controller;


import java.util.List;

import com.gurukrupa.gurukrupajewellors.entities.Login;
import com.gurukrupa.gurukrupajewellors.repositories.LoginRepository;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private LoginRepository repository;

	@GetMapping
	private ResponseEntity<String>checkConnection()
	{
		return new ResponseEntity<String>("Connected",HttpStatus.OK);
	}
	@GetMapping(value = "/logins", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<Login>> getAllLogin() {
		return new ResponseEntity<List<Login>>(repository.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/logins/byname/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Login> getLoginByName(@PathVariable("userName") String name) {
		Login login = repository.findByuserName(name);
		if (login != null)
			return new ResponseEntity<Login>(login, HttpStatus.OK);
		else
			return new ResponseEntity<Login>(HttpStatus.NOT_FOUND);
	}
	@GetMapping(value="/logins/byperson/", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Login>getLoginByPerson(@RequestParam String person)
	{
		
		Login login = repository.findByPerson(person);
		if(login!=null) {
			System.out.println("Logn in Api="+login);
			return new ResponseEntity<Login>(login,HttpStatus.OK);
		}
		else
			return new ResponseEntity<Login>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "/logins/byid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Login> getLoginById(@PathVariable("id") int id) {
		Login login = repository.findById(id).orElse(null);
		if (login != null)
			return new ResponseEntity<Login>(login, HttpStatus.OK);
		else
			return new ResponseEntity<Login>(HttpStatus.NOT_FOUND);
	}
	@GetMapping(value = "/logins/validate/{userName}/{password}")
	private ResponseEntity<String> validateLogin(@PathVariable("userName") String name,
			@PathVariable("password") String password) {
		Login login = new Login();
		login = repository.findByuserName(name);
		if (login == null) {
			return new ResponseEntity<String>("Enter Correct User Name!!!", HttpStatus.NOT_FOUND);
		}

		else if (!login.getPassword().equals(password)) {
			return new ResponseEntity<String>("Enter Correct Password!!!", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<String>("Login Success!!!", HttpStatus.OK);
		}
	}

	@PostMapping(value = "/logins/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Login> saveLogin(@RequestBody Login login) {
		return new ResponseEntity<Login>(repository.save(login), HttpStatus.OK);
	}
	@GetMapping(value="/logins/allusernames", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<String>>getAllUserNames()
	{
		return new ResponseEntity<List<String>>(repository.getAllLoginNames(),HttpStatus.OK);
	}
	@PutMapping(value="/logins/update",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Login>updateLogin(@RequestBody Login login)
	{
		try {
			Login l =repository.findByuserName(login.getUserName());
			if(l!=null)
			{
				l.setPassword(login.getPassword());
				l.setPerson(login.getPerson());
				l.setUserName(login.getUserName());				
				return new ResponseEntity<Login>(repository.save(l),HttpStatus.OK);
			}
			else
				return new ResponseEntity<Login>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
