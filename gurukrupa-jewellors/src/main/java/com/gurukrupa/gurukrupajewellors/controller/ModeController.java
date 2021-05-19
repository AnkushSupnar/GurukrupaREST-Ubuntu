package com.gurukrupa.gurukrupajewellors.controller;
import java.time.LocalDate;
import java.util.List;

import com.gurukrupa.gurukrupajewellors.entities.Mode;
import com.gurukrupa.gurukrupajewellors.repositories.ModeRepository;

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
public class ModeController {
	@Autowired
	ModeRepository repository;
	@GetMapping(value="/modes/byid/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Mode> getModeById(@PathVariable("id") long id)
	{
		Mode mode = repository.findById(id).orElse(null);
		if(mode!=null)
		return new ResponseEntity<Mode>(mode,HttpStatus.OK);
		else
			return new ResponseEntity<Mode>(HttpStatus.NOT_FOUND);
	}
	@GetMapping(value="/modes",produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Mode>>getAllModes()
	{
		return new ResponseEntity<List<Mode>>(repository.findAll(),HttpStatus.OK);
	}
	@GetMapping(value="/modes/bycustomer/{cid}",produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Mode>>getModeByCustomer(@PathVariable("cid") long customerid)
	{
		return new ResponseEntity<List<Mode>>(repository.getModeByCustomer(customerid),HttpStatus.OK);
	}
	@GetMapping(value="/modes/bylogin/{loginid}",produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Mode>>getModeByLogin(@PathVariable("loginid")int loginid)
	{
		return new ResponseEntity<List<Mode>>(repository.getModesByLogin(loginid),HttpStatus.OK);
	}
	@GetMapping(value="/modes/bydate/{date}")
	ResponseEntity<List<Mode>>getDateWiseModes(@PathVariable("date") LocalDate date)
	{
		return new ResponseEntity<List<Mode>>(repository.getModesByDate(date),HttpStatus.OK);
	}
	@PostMapping(value="/modes/save",consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Mode>saveMode(@RequestBody Mode mode)
	{
		return new ResponseEntity<Mode>(repository.save(mode),HttpStatus.OK);
	}
	@PutMapping(value="/modes/update",consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Mode>updateMode(@RequestBody Mode mode)
	{		
		if (repository.findById(mode.getId()) == null) {
			return new ResponseEntity<Mode>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Mode>(repository.save(mode), HttpStatus.OK);
		}
	}
	
}
