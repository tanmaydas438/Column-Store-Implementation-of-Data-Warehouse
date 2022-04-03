package com.iiitb;

public class Sales {
	private Product product=null;
	private Customer customer=null;
	private SalesPerson salesperson=null;
	private int slaesUnit=0;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public SalesPerson getSalesperson() {
		return salesperson;
	}
	public void setSalesperson(SalesPerson salesperson) {
		this.salesperson = salesperson;
	}
	public int getSlaesUnit() {
		return slaesUnit;
	}
	public void setSlaesUnit(int slaesUnit) {
		this.slaesUnit = slaesUnit;
	}
	
}
