package com.iiitb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterFromDat extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
		String productname=req.getParameter("productname");
		String category=req.getParameter("category");
		String customercity=req.getParameter("customercity");
		String customertype=req.getParameter("customertype");
		String salespersontype=req.getParameter("salespersontype");
		Product product=new Product();
		Customer customer=new Customer();
		SalesPerson salesperson=new SalesPerson();
		product.setPRODUCTNAME(productname);
		product.setCATEGORY(category);
		customer.setCUSTOMERCITY(customercity);
		customer.setCUSTOMERTYPE(customertype);
		salesperson.setSALESPERSONTYPE(salespersontype);
		
		Utility util=new Utility(); 
		HashSet<String> productKeys=util.getProductForiegnKeys(product);
		HashSet<String> customerKeys=util.getCustomerForiegnKeys(customer);
		HashSet<String> personKeys=util.getSalesPersonForiegnKeys(salesperson);
		
		HashSet<String> productRowIds=util.getRowIdsFromSalesForForiegnKeys(productKeys, "PRODUCTKEY-fk");
		HashSet<String> customerRowIds=util.getRowIdsFromSalesForForiegnKeys(customerKeys,"CUSTOMERKEY-fk");
		HashSet<String> personRowIds=util.getRowIdsFromSalesForForiegnKeys(personKeys,"SALESPERSONKEY-fk");
		
		HashSet<String> rows=new HashSet<>();
		rows=productRowIds;
		rows.retainAll(customerRowIds);
		rows.retainAll(personRowIds);
		
		HashSet<Integer> salesUnit=new HashSet<Integer>();
		salesUnit=util.getIntegerAttributesForRowIds(rows, "SALE_UNITS");
		
		//System.out.println("hello");
		System.out.println(salesUnit.size());
		int totalSales=0;
		int count=0;
		for(int data:salesUnit)
		{
			totalSales+=data;
			count++;
		}	
		
		req.setAttribute("salesUnit", salesUnit);
		req.setAttribute("totalSales", totalSales);
		req.setAttribute("count", count);
		String filterPage = "Result.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(filterPage);
        dispatcher.forward(req, res);
		
	}
}
