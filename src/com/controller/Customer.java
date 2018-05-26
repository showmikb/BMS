package com.controller;

import com.dao.*;
import com.service.*;


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

/**
 * Servlet implementation class Customer
 */
@WebServlet("/Customer")
public class Customer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Customer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rd;
		
		
		String page = request.getParameter("page");
		System.out.println(page);
		if(page == null)
			{
			rd =  request.getRequestDispatcher("500.html");
			rd.forward(request, response);
			}
		else
		{
			System.out.println("inside else page ="+page);
			if(page.equals("custgetprofile"))
			{
				 System.out.println("inside fetch customer");
				String ssnid = (request.getParameter("ssnid"));
				String custid = (request.getParameter("custid"));
				System.out.println("ssnid = "+ssnid+" custid = "+custid);
				Service serv = new Service();
				String j = serv.search(ssnid, custid);
				if(j != null)
				{
					
					request.setAttribute("json", j);
					rd =  request.getRequestDispatcher("customerviewprofile.jsp");
					rd.forward(request, response);
				}
				else
				{
					rd =  request.getRequestDispatcher("500.html");
					rd.forward(request, response);	
				}
				
			}

			if(page.equals("custstatfetchcustomer"))
			{
				 System.out.println("inside fetch customer");
				String ssnid = (request.getParameter("ssnid"));
				String custid = (request.getParameter("custid"));
				System.out.println("ssnid = "+ssnid+" custid = "+custid);
				Service serv = new Service();
				String j = serv.search(ssnid, custid);
				if(j != null)
				{
					
					request.setAttribute("json", j);
					rd =  request.getRequestDispatcher("customerviewprofile.jsp");
					rd.forward(request, response);
				}
				else
				{
					rd =  request.getRequestDispatcher("500.html");
					rd.forward(request, response);	
				}
				
			}
			if(page.equals("deletefetchcustomer"))
			{
				 System.out.println("inside fetch customer");
				String ssnid = (request.getParameter("ssnid"));
				String custid = (request.getParameter("custid"));
				System.out.println("ssnid = "+ssnid+" custid = "+custid);
				Service serv = new Service();
				String j = serv.search(ssnid, custid);
				if(j != null)
				{
					
					request.setAttribute("json", j);
					rd =  request.getRequestDispatcher("deletecustomerfinal.jsp");
					rd.forward(request, response);
				}
				else
				{
					rd =  request.getRequestDispatcher("500.html");
					rd.forward(request, response);	
				}
				
			}
			if(page.equals("deletecustomerfinal"))
			{
				System.out.println("inside fetch customer");
				String ssnid = (request.getParameter("ssnid"));
				String custid = (request.getParameter("custid"));
				System.out.println("ssnid = "+ssnid+" custid = "+custid);
				Service serv = new Service();
				
				if(serv.delete(ssnid, custid))
				{
					
					
					rd =  request.getRequestDispatcher("bankprofile.jsp");
					rd.forward(request, response);
				}
				else
				{
					rd =  request.getRequestDispatcher("500.html");
					rd.forward(request, response);	
				}
			}
			if(page.equals("update"))
			{
				System.out.println("IN UPDATE>");
				String s = (String) request.getAttribute("json");
				request.setAttribute("json",s);
				rd =  request.getRequestDispatcher("updatecustomerfinal.jsp");
				rd.forward(request, response);	
			}
			if(page.equals("updatecustomerfinal"))
			{
				String ssnid = (request.getParameter("ssnid"));
				String custid = (request.getParameter("custid"));
				String cuname = (request.getParameter("name"));
				String cuaddress = (request.getParameter("addr"));
				int cuage = Integer.parseInt(request.getParameter("age"));
				Service serv = new Service();
					if(serv.update(ssnid, custid, cuname,cuaddress,cuage))
					{
						rd =  request.getRequestDispatcher("bankprofile.jsp");
						rd.forward(request, response);	
					}
					else
					{
						rd =  request.getRequestDispatcher("500.html");
						rd.forward(request, response);	
					}
					
					
				
			}
			if(page.equals("fetchcustomer"))
			{
				 System.out.println("inside fetch customer");
				String ssnid = (request.getParameter("ssnid"));
				String custid = (request.getParameter("custid"));
				System.out.println("ssnid = "+ssnid+" custid = "+custid);
				Service serv = new Service();
				String j = serv.search(ssnid, custid);
				if(j != null)
				{
					
					request.setAttribute("json", j);
					rd =  request.getRequestDispatcher("updatecustomerfinal.jsp");
					rd.forward(request, response);
				}
				else
				{
					rd =  request.getRequestDispatcher("500.html");
					rd.forward(request, response);	
				}
				
			}
			if(page.equals("login"))
			{
				System.out.println(page);
				String username = (request.getParameter("uname"));
				String password = request.getParameter("password");
				Service serv = new Service();
				String role = serv.login(username,password);
				if(role != null)
				{
					System.out.println("Login successful");
					System.out.println("role :: "+role);
						if(role.equals("bankemp"))
						{
							HttpSession session = request.getSession();
							session.setAttribute("role",role);
							session.setAttribute("ssnid", username);
							rd =  request.getRequestDispatcher("bankprofile.jsp");
							rd.forward(request, response);
						}
						else if(role.equals("cashier"))
						{
							HttpSession session = request.getSession();
							session.setAttribute("role",role);
							session.setAttribute("ssnid", username);
							rd =  request.getRequestDispatcher("cashierprofile.jsp");
							rd.forward(request, response);
						}
						
						else if(role.equals("customer"))
						{
							HttpSession session = request.getSession();
							session.setAttribute("role",role);
							session.setAttribute("ssnid", username);
							rd =  request.getRequestDispatcher("customerprofile.jsp");
							rd.forward(request, response);
						}	
						else
						{
							rd =  request.getRequestDispatcher("500.html");
							rd.forward(request, response);	
						}
						
					
					
				}
				else
				{
					request.setAttribute("message","Incorrect Credentials. Try Again.");
					rd =  request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
				}
			}
			 if(page.equals("createcustomer"))
			{
				String password = request.getParameter("password");
				String ssnid = (request.getParameter("userName"));
				String cuname = request.getParameter("name");
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");
				int cuage = Integer.parseInt(request.getParameter("age"));
				//address1=address1.concat(";");
				String cuaddress = address1.concat(address2);
				Service serv = new Service();
				if(serv.create(ssnid, password, cuname, cuaddress, cuage) == true)
				{
					rd =  request.getRequestDispatcher("bankprofile.jsp");
					rd.forward(request, response);
				}	

				  
			}
			
			 

		}
				
	}

}
