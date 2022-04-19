package com.iiitb;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NodeCreation extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
		String attr1=req.getParameter("product");
		String attr2=req.getParameter("customer");
		String attr3=req.getParameter("salesperson");
		ArrayList<String> attrList=new ArrayList<>();
		if(attr1!=null)
			attrList.add(attr1);
		if(attr2!=null)
			attrList.add(attr2);
		if(attr3!=null)
			attrList.add(attr3);
		Utility util=new Utility();
		ArrayList<ArrayList<String>> nodes=util.getAllCombinations(attrList);
		req.setAttribute("nodes", nodes);
		
		RequestDispatcher dispatcher2 = req.getRequestDispatcher("/exportNodes");
		dispatcher2.include(req, res);
		
		String latticePage = "LatticeOfCuboid.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(latticePage);
        dispatcher.forward(req, res);
		
	}
}
