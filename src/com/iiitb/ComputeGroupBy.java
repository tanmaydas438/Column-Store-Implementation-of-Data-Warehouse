package com.iiitb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ComputeGroupBy extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
		Utility util=new Utility();
		String node=req.getParameter("comb");
		//System.out.println(node.length());
		node=node.substring(1, node.length()-1);
		String[] attrs=node.split(",");
		String attr1="";
		String attr2="";
		String attr3="";
		for(int i=0;i<attrs.length;i++)
		{
			attrs[i]=attrs[i].trim();
			String dimName=util.getDimensionName(attrs[i]);
			//System.out.println(dimName);
			if(dimName.equals("Product"))
				attr1+=attrs[i];
			else if(dimName.equals("Customer"))
				attr2+=attrs[i];
			else
				attr3+=attrs[i];
		}
		//System.out.println("attr1"+attr1);
		//System.out.println("attr2"+attr2);
		//System.out.println("----------attr3"+attr3);
		HashMap<Lattice,Integer> hm=util.getGroupBy(attr1, attr2, attr3);
		Set<Lattice> hs=new HashSet<>();
		hs=hm.keySet();
		ArrayList<NodeValue> nodeValueList=new ArrayList<>();
		for(Lattice l:hs)
		{
			NodeValue n=new NodeValue();
			n.setAttr1(l.getAttr1());
			n.setAttr2(l.getAttr2());
			n.setAttr3(l.getAttr3());
			n.setValue(hm.get(l));
			nodeValueList.add(n);
			//System.out.println("n..attr1"+n.getAttr1());
			//System.out.println("n..attr2"+n.getAttr2());
			//System.out.println("n..attr3"+n.getAttr3());
		}
		req.setAttribute("nodeValueList", nodeValueList);
		req.setAttribute("attr1", attr1);
		req.setAttribute("attr2", attr2);
		req.setAttribute("attr3", attr3);
		String groupbyPage = "GroupBy.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(groupbyPage);
        dispatcher.forward(req, res);
		
	}
}
