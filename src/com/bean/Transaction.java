package com.bean;

public class Transaction {
String trans_id;
String op_type;
String amount;
String trans_date;
public String getTrans_id() {
	return trans_id;
}
public void setTrans_id(String trans_id) {
	this.trans_id = trans_id;
}
public String getOp_type() {
	return op_type;
}
public void setOp_type(String op_type) {
	this.op_type = op_type;
}
public String getAmount() {
	return amount;
}
public void setAmount(String amount) {
	this.amount = amount;
}
public String getTrans_date() {
	return trans_date;
}
public void setTrans_date(String trans_date) {
	this.trans_date = trans_date;
}
public Transaction(String trans_id, String op_type, String amount,
		String trans_date) {
	super();
	this.trans_id = trans_id;
	this.op_type = op_type;
	this.amount = amount;
	this.trans_date = trans_date;
}

public Transaction()
{
	
}


}
