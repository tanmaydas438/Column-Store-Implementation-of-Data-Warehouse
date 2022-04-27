package com.iiitb;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NodeCreation1 extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
		String attr1=req.getParameter("productname");
		String attr2=req.getParameter("customercity");
		String attr3=req.getParameter("salespersontype");
		String attr4=req.getParameter("category");
		String attr5=req.getParameter("customertype");
		ArrayList<String> attrList=new ArrayList<>();
		if(attr1!=null)
			attrList.add(attr1);
		if(attr2!=null)
			attrList.add(attr2);
		if(attr3!=null)
			attrList.add(attr3);
		if(attr4!=null)
			attrList.add(attr4);
		if(attr5!=null)
			attrList.add(attr5);
		
		Utility util=new Utility();
		ArrayList<ArrayList<String>> nodes=util.getAllCombinations(attrList);
		req.setAttribute("nodes", nodes);
		RequestDispatcher dispatcher2 = req.getRequestDispatcher("/exportNodes1");
		dispatcher2.include(req, res);
		
		String latticePage = "LatticeOfCuboid1.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(latticePage);
        dispatcher.forward(req, res);
		
	}
}
