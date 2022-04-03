package com.iiitb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import com.iiitb.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class FilterPageServlet extends HttpServlet{
	
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
		Utility util=new Utility();
		
		ArrayList<String> productNames=new ArrayList<>();
		productNames=util.getStringUniqueValues("PRODUCTNAME");
		HashSet<String> h_productNames=new HashSet<>(productNames);
		req.setAttribute("productNames", h_productNames);
		
		ArrayList<String> categories=new ArrayList<>();
		categories=util.getStringUniqueValues("CATEGORY");
		HashSet<String> h_categories=new HashSet<>(categories);
		req.setAttribute("categories", h_categories);
		System.out.println(h_categories);
		
		ArrayList<String> customerCities=new ArrayList<>();
		customerCities=util.getStringUniqueValues("CUSTOMERCITY");
		HashSet<String> h_customerCities=new HashSet<>(customerCities);
		req.setAttribute("customerCities", h_customerCities);
		
		ArrayList<String> customerTypes=new ArrayList<>();
		customerTypes=util.getStringUniqueValues("CUSTOMERTYPE");
		HashSet<String> h_customerTypes=new HashSet<>(customerTypes);
		req.setAttribute("customerTypes", h_customerTypes);
		
		ArrayList<String> salesPersonTypes=new ArrayList<>();
		salesPersonTypes=util.getStringUniqueValues("SALESPERSONTYPE");
		HashSet<String> h_salesPersonTypes=new HashSet<>(salesPersonTypes);
		req.setAttribute("salesPersonTypes", h_salesPersonTypes);
		
		String filterPage = "FilterPage.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(filterPage);
        dispatcher.forward(req, res);
	}

}
