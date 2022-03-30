package com.iiitb;

import java.io.IOException;
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
		
		HashSet<String> productNames=new HashSet<>();
		productNames=util.getStringUniqueValues("PRODUCTNAME");
		req.setAttribute("productNames", productNames);
		
		HashSet<String> categories=new HashSet<>();
		categories=util.getStringUniqueValues("CATEGORY");
		req.setAttribute("categories", categories);
		
		HashSet<String> customerCities=new HashSet<>();
		customerCities=util.getStringUniqueValues("CUSTOMERCITY");
		req.setAttribute("customerCities", customerCities);
		
		HashSet<String> customerTypes=new HashSet<>();
		customerTypes=util.getStringUniqueValues("CUSTOMERTYPE");
		req.setAttribute("customerTypes", customerTypes);
		
		HashSet<String> salesPersonTypes=new HashSet<>();
		salesPersonTypes=util.getStringUniqueValues("SALESPERSONTYPE");
		req.setAttribute("salesPersonTypes", salesPersonTypes);
		
		String filterPage = "FilterPage.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(filterPage);
        dispatcher.forward(req, res);
	}

}
