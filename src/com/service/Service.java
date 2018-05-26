package com.service;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.json.JSONException;
import org.json.JSONObject;

import com.bean.Transaction;
import com.dao.*;

public class Service {
	public boolean create(String ssnid, String password, String cuname, String cuaddress, int cuage) throws ServletException, IOException
	{
		Dao d = new Dao();
		return (d.ccust(ssnid, password, cuname, cuaddress, cuage));
		
	}
	public String login(String username, String password)
	{
		Dao d = new Dao();
		return (d.chcus(username,password));
	}
	public String search(String ssnid, String custid) throws IOException
	{
		Dao d = new Dao();
		return (d.search(ssnid,custid));
	}
	public String getAccountStatus(String custid) throws IOException
	{
		Dao d = new Dao();
		return (d.getAccountStatus(custid));
		
	}
	public boolean update(String ssnid, String custid, String cuname, String cuaddress, int cuage) throws IOException
	{
		Dao d = new Dao();
		return (d.updatecustomer(ssnid, custid, cuname,cuaddress,cuage));
	}
	public boolean delete(String ssnid, String custid) throws IOException
	{
		Dao d = new Dao();
		return (d.delete(ssnid,custid));
	}
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public boolean create(String custid, String acctype, int balance,String crdt,String ltdt,int dur ) throws IOException
	{
		Dao d = new Dao();
		return (d.createAccount(custid,acctype,balance,crdt,ltdt,dur));
		
	}
/*	
public boolean search(String ssnid) throws IOException
	{
		Dao d = new Dao();
		return(d.search(ssnid));
	}*/
/*	public boolean updatecustomer(String ssnid, String cuname, String cuaddress, int cuage) throws IOException
	{
		Dao d = new Dao();
		return (d.updatecustomer(ssnid,cuname,cuaddress,cuage));
	}
	*/
	public boolean Del(String custid,String accid,String acctype) throws IOException
	{
		Dao d = new Dao();
		return(d.DelAccount(custid,accid,acctype));
	}
	public boolean Deposite(String custid,String acid,String actype,String lasttrdate,int depositeamt)
	{
		try{
		DaoTeller dt=new DaoTeller();
		if(dt.Deposite(custid, acid, actype, lasttrdate, depositeamt)==true)
		return(true);
		}
		catch(IOException e){
			
		}
		return false;
	}
	public boolean Withdraw(String custid,String acid,String actype,String lasttrdate,int withdrawamt) throws IOException
	{
		DaoTeller dt=new DaoTeller();
		System.out.println("in service withdraw");
		return(dt.WithdrawAmount(custid,acid, actype, lasttrdate, withdrawamt));
		
	}
public boolean Transfer(String custid,String acid,String sactype,String tactype,String lasttrdate,int transferamt) throws JSONException, IOException
	{
		DaoTeller dt=new DaoTeller();
		return  dt.Transfer(custid, acid, sactype, tactype, lasttrdate, transferamt);
	}
public ArrayList<Long> getAccountsForCustomer(String custid)
{
	DaoTeller dt=new DaoTeller();
	try {
		return dt.getAccountsForCustomer(custid);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public ArrayList<Transaction> getStatementBytrns(String acid,int numberofTrns)
{
	
	try {
		DaoTeller dt=new DaoTeller();
		return dt.getStatementBytrans(acid,numberofTrns);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
	public String GetAccountDetails(String a)
	{
		try{
			DaoTeller dt=new DaoTeller();
			System.out.println("in accdetails...");
			return dt.GetAccountDetail(a);
		}
		catch(IOException e){
			
		}
		return null;
	}
	public ArrayList<Transaction> miniStatement(String accid, String sDate,
			String eDate)  {
		
		DaoTeller dt=new DaoTeller();
		try {
			return dt.miniStatement(accid,sDate,eDate);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}
		return null;
	}
	
}
