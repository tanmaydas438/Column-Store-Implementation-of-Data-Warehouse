package com.iiitb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList;

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
		ArrayList<String> productKeys=util.getProductForiegnKeys(product);
		ArrayList<String> customerKeys=util.getCustomerForiegnKeys(customer);
		ArrayList<String> personKeys=util.getSalesPersonForiegnKeys(salesperson);
		
		ArrayList<String> productRowIds=util.getRowIdsFromSalesForForiegnKeys(productKeys, "PRODUCTKEY-fk");
		ArrayList<String> customerRowIds=util.getRowIdsFromSalesForForiegnKeys(customerKeys,"CUSTOMERKEY-fk");
		ArrayList<String> personRowIds=util.getRowIdsFromSalesForForiegnKeys(personKeys,"SALESPERSONKEY-fk");
		
		ArrayList<String> rows=new ArrayList<>();
		
		rows=productRowIds;
		if(rows.size()==0)
			rows=customerRowIds;
		else if(customerRowIds.size()>0)
			rows.retainAll(customerRowIds);
		if(rows.size()==0)
			rows=personRowIds;
		else if(personRowIds.size()>0)
			rows.retainAll(personRowIds);
		
		
		System.out.println(rows);
		
		/*
		 ArrayList<Integer> salesUnit=new ArrayList<Integer>();
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
		//System.out.println("product:  "+util.getProduct(rows.get(0)).getPRODUCTNAME());
		//getting all the object for rows
		ArrayList<Product> productList=new ArrayList<>();
		ArrayList<Customer> customerList=new ArrayList<>();
		ArrayList<SalesPerson> salesPersonList=new ArrayList<>();
		
		for(int i=0;i<rows.size();i++)
		{
			Product p=util.getProduct(rows.get(i));
			Customer c=util.getCustomer(rows.get(i));
			SalesPerson s=util.getSalesPerson(rows.get(i));
			productList.add(i,p);
			customerList.add(i,c);
			salesPersonList.add(i, s);
		}
		req.setAttribute("productList", productList);
		req.setAttribute("customerList", customerList);
		req.setAttribute("salesPersonList", salesPersonList);
		req.setAttribute("salesUnit", salesUnit);
		req.setAttribute("totalSales", totalSales);
		req.setAttribute("count", count);
		*/
		ArrayList<Sales> salesList=new ArrayList<>();
		for(int i=0;i<rows.size();i++)
		{
			Product p=util.getProduct(rows.get(i));
			Customer c=util.getCustomer(rows.get(i));
			SalesPerson s=util.getSalesPerson(rows.get(i));
			int salesUnit=util.getIntegerAttributeForParticularRowId(rows.get(i), "SALE_UNITS");
			Sales sale=new Sales();
			sale.setProduct(p);
			sale.setCustomer(c);
			sale.setSalesperson(s);
			sale.setSlaesUnit(salesUnit);
			salesList.add(i, sale);
		}
		req.setAttribute("salesList", salesList);
		String filterPage = "Result.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(filterPage);
        dispatcher.forward(req, res);
		
	}
}
