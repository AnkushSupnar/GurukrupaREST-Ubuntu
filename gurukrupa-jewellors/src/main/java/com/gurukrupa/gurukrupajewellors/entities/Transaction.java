package com.gurukrupa.gurukrupajewellors.entities;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="itemid",referencedColumnName = "id")
	Item item;
	double rate;
	double quantity;
	@ManyToOne
	@JoinColumn(name="billid",nullable = false)
	Bill bill;
	public Transaction() {
		super();
	}
	public Transaction(Item item, double rate, double quantity, Bill bill) {
		super();
		this.item = item;
		this.rate = rate;
		this.quantity = quantity;
		this.bill = bill;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", item=" + item + ", rate=" + rate + ", quantity=" + quantity + ", bill="
				+ bill + "]";
	}
	
}