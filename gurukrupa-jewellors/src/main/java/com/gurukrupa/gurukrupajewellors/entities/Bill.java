package com.gurukrupa.gurukrupajewellors.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Bill {
	@Id
	long billno;
	LocalDate date;
	double amount;
	double paidamount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="customerid",referencedColumnName = "id")	
	Customer customer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="bankid",referencedColumnName = "id")
	Bank bank;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="loginid",referencedColumnName = "id")
	Login login;
	
	@OneToMany(mappedBy = "bill")
	List<Transaction>transaction = new ArrayList<>();

	public Bill() {
		super();
	}

	public Bill(long billno,LocalDate date,  double amount, double paidamount, Customer customer, Bank bank, Login login,
			List<Transaction> transaction) {
		super();
		this.billno = billno;
		this.date = date;		
		this.amount = amount;
		this.paidamount = paidamount;
		this.customer = customer;
		this.bank = bank;
		this.login = login;
		this.transaction = transaction;
	}

	public long getBillno() {
		return billno;
	}

	public void setId(long billno) {
		this.billno = billno;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPaidamount() {
		return paidamount;
	}

	public void setPaidamount(double paidamount) {
		this.paidamount = paidamount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "Bill [billno=" + billno+ ", date=" + date + ", amount=" + amount + ", paidamount="
				+ paidamount + ", customer=" + customer + ", bank=" + bank + ", login=" + login + ", transaction="
				+ transaction + "]";
	}

	
	
	
}