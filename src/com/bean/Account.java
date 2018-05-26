package com.bean;

public class Account {
private long customer_Id;
private long account_Id;
private String account_Type;
private long account_bal;
private String lasttr_date;
public Account()
{
	
}

public Account(long customer_Id, long account_Id, String account_Type,
		long account_bal, String lasttr_date) {
	super();
	this.customer_Id = customer_Id;
	this.account_Id = account_Id;
	this.account_Type = account_Type;
	this.account_bal = account_bal;
	this.lasttr_date = lasttr_date;
}

public String getLasttr_date() {
	return lasttr_date;
}

public void setLasttr_date(String lasttr_date) {
	this.lasttr_date = lasttr_date;
}

public long getCustomer_Id() {
	return customer_Id;
}
public void setCustomer_Id(long customer_Id) {
	this.customer_Id = customer_Id;
}
public long getAccount_Id() {
	return account_Id;
}
public void setAccount_Id(long account_Id) {
	this.account_Id = account_Id;
}
public String getAccount_Type() {
	return account_Type;
}
public void setAccount_Type(String account_Type) {
	this.account_Type = account_Type;
}
public long getAccount_bal() {
	return account_bal;
}
public void setAccount_bal(long account_bal) {
	this.account_bal = account_bal;
}

	
}
