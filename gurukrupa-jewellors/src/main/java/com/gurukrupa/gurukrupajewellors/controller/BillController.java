package com.gurukrupa.gurukrupajewellors.controller;


import java.time.LocalDate;
import java.util.List;


import com.gurukrupa.gurukrupajewellors.entities.Bill;
import com.gurukrupa.gurukrupajewellors.entities.Transaction;
import com.gurukrupa.gurukrupajewellors.repositories.BillRepository;
import com.gurukrupa.gurukrupajewellors.repositories.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class BillController {

	@Autowired
	BillRepository repository;
	@Autowired
	TransactionRepository transactionRepository;
	@GetMapping(value="/bills")
	public ResponseEntity<List<Bill>>getAllBills()
	{
		return new ResponseEntity<List<Bill>>(repository.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(value="/bills/bybillno/{billno}")
	public ResponseEntity<Bill> getBillByBillno(@PathVariable("billno") Long billno)
	{
		Bill bill = repository.findById(billno).orElse(null);
		if(bill!=null)
		return new ResponseEntity<Bill>(bill,HttpStatus.OK);
		else
		return new ResponseEntity<Bill>(HttpStatus.NOT_FOUND);
	}
	@GetMapping(value="/bills/bydate/{date}")
	public ResponseEntity<List<Bill>>getBillByDate(@PathVariable("date")LocalDate date)
	{
		return new ResponseEntity<List<Bill>>(repository.getBillByDate(date),HttpStatus.OK);
	}
	@GetMapping(value="/bill/bycustomer/{customerid}")
	public ResponseEntity<List<Bill>>getBillByCustomer(@PathVariable("customerid")long customerid)
	{
		return new ResponseEntity<List<Bill>>(repository.getBillByCustomer(customerid),HttpStatus.OK);
	}
	@GetMapping(value="/bill/bylogin/{customerid}")
	public ResponseEntity<List<Bill>>getBillByLogin(@PathVariable("loginid")int loginid)
	{
		return new ResponseEntity<List<Bill>>(repository.getBillByLogin(loginid),HttpStatus.OK);
	}
	@GetMapping(value="/bills/bydateperiod/{satrt}/{end}")
	public ResponseEntity<List<Bill>>getDatePeriodBill(@PathVariable("start")LocalDate start,@PathVariable("end")LocalDate end)
	{
		return new ResponseEntity<List<Bill>>(repository.getDatePeriodBill(start, end),HttpStatus.OK);
	}
	@PostMapping(value="/bills/save")
	public ResponseEntity<Bill>saveBill(@RequestBody Bill bill)
	{
		List<Transaction>trList = bill.getTransaction();
		for(int index=0;index<bill.getTransaction().size();index++)
		{
			bill.getTransaction().get(index).setBill(bill);
		}
		//bill.setTransaction();
		
		System.out.println("Got to Save===="+bill.getBillno()+"\n Transaction is==\n");
		for(Transaction tr:trList)
		{
			System.out.println(tr);
		}
		//return null;
		Bill b = repository.save(bill);
		System.out.println("Saved==> "+b);
		return new ResponseEntity<Bill>(b,HttpStatus.OK);
	}
	@PutMapping(value="/bills/update")
	public ResponseEntity<Bill>updateBill(@RequestBody Bill bill)
	{
		Bill b = repository.findById(bill.getBillno()).orElse(null);
		if(b!=null){
			//repository.delete(b);
			transactionRepository.deleteTransactionByBillNo(bill.getBillno());
			return new ResponseEntity<Bill>(repository.save(bill),HttpStatus.OK);
		}
		else
		return new ResponseEntity<Bill>(HttpStatus.BAD_REQUEST);
	}
}
