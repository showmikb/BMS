package com.bean;

public class Customer {
private long customer_Id;
private String customerName;
public Customer()
{
	
}
public Customer(long customer_Id) {
	super();
	this.customer_Id = customer_Id;
	this.customerName=customerName;
}
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public long getCustomer_Id() {
	return customer_Id;
}
public void setCustomer_Id(long customer_Id) {
	this.customer_Id = customer_Id;
}


}
