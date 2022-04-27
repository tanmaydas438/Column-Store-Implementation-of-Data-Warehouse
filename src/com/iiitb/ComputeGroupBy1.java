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

public class ComputeGroupBy1 extends HttpServlet{

	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
		Utility util=new Utility();
		String node=req.getParameter("comb");
		//System.out.println(node.length());
		node=node.substring(1, node.length()-1);
		String[] attrs=node.split(",");
		String attr1="";
		String attr2="";
		String attr3="";
		String attr4="";
		String attr5="";
		for(int i=0;i<attrs.length;i++)
		{
			attrs[i]=attrs[i].trim();
			String dimName=util.getDimensionName(attrs[i]);
			//System.out.println(dimName);
			if(dimName.equals("Product"))
			{
				if(attr1.equals(""))
				{
					attr1+=attrs[i];
				}	
				else
					attr4+=attrs[i];
			}
			else if(dimName.equals("Customer"))
			{
				if(attr2.equals(""))
					attr2+=attrs[i];
				else
					attr5+=attrs[i];
			}
				
			else
				attr3+=attrs[i];
		}
		HashMap<Lattice1,Integer> hm=util.getGroupBy1(attr1, attr2, attr3, attr4, attr5);
		Set<Lattice1> hs=new HashSet<>();
		hs=hm.keySet();
		ArrayList<NodeValue1> nodeValueList=new ArrayList<>();
		HashSet<String> uniqueAttr1=new HashSet<>();
		HashSet<String> uniqueAttr2=new HashSet<>();
		HashSet<String> uniqueAttr3=new HashSet<>();
		HashSet<String> uniqueAttr4=new HashSet<>();
		HashSet<String> uniqueAttr5=new HashSet<>();
		for(Lattice1 l:hs)
		{
			NodeValue1 n=new NodeValue1();
			n.setAttr1(l.getAttr1());
			n.setAttr2(l.getAttr2());
			n.setAttr3(l.getAttr3());
			n.setAttr4(l.getAttr4());
			n.setAttr5(l.getAttr5());
			uniqueAttr1.add(l.getAttr1());
			uniqueAttr2.add(l.getAttr2());
			uniqueAttr3.add(l.getAttr3());
			uniqueAttr4.add(l.getAttr4());
			uniqueAttr5.add(l.getAttr5());
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
		req.setAttribute("attr4", attr4);
		req.setAttribute("attr5", attr5);
		
		
		req.setAttribute("uniqueAttr1List", uniqueAttr1);
		req.setAttribute("uniqueAttr2List", uniqueAttr2);
		req.setAttribute("uniqueAttr3List", uniqueAttr3);
		req.setAttribute("uniqueAttr4List", uniqueAttr4);
		req.setAttribute("uniqueAttr5List", uniqueAttr5);
		
		
		String groupbyPage = "GroupBy1.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(groupbyPage);
        dispatcher.forward(req, res);
		
	}
}
